package co.com.meli.coupon.api.controller.coupon;

import co.com.meli.coupon.api.resource.coupon.CouponRequestResource;
import co.com.meli.coupon.api.resource.coupon.CouponResponseResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Api(value = "coupon-api")
public interface CouponController {

  @ApiOperation(value = "Returns the items that a customer can buy with an amount")
  ResponseEntity<CouponResponseResource> calculateCoupon(@ApiParam(value = "The Parameter object") @RequestBody CouponRequestResource couponRequestResource);

}
