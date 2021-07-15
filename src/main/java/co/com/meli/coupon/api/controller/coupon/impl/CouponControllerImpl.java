package co.com.meli.coupon.api.controller.coupon.impl;

import co.com.meli.coupon.api.controller.coupon.CouponController;
import co.com.meli.coupon.api.resource.coupon.CouponRequestResource;
import co.com.meli.coupon.api.resource.coupon.CouponResponseResource;
import co.com.meli.coupon.service.coupon.CouponService;
import co.com.meli.coupon.service.item.ItemService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/coupon", produces = "application/hal+json")
public class CouponControllerImpl implements CouponController {

  @Autowired
  private CouponService couponService;

  @Autowired
  private ItemService itemService;

  @Override
  @PostMapping(value = "/")
  public ResponseEntity<CouponResponseResource> calculateCoupon(
      CouponRequestResource couponRequestResource) {
    Map<String, Float> mapItemsPrices = itemService
        .getMapItemsPrices(couponRequestResource.getItem_ids());
    List<String> itemsCoupon = couponService.calculate(mapItemsPrices,
        couponRequestResource.getAmount());

    if (!CollectionUtils.isEmpty(itemsCoupon)) {
      Float total = mapItemsPrices.entrySet().stream()
          .filter(entry -> itemsCoupon.contains(entry.getKey()))
          .map(entry -> entry.getValue())
          .reduce(0f, Float::sum);

      return ResponseEntity.ok(
          CouponResponseResource
          .builder()
              .item_ids(itemsCoupon)
              .total(total)
              .build()
      );
    }

    return ResponseEntity.notFound().build();
  }
}
