package co.com.meli.coupon.service.integration;

import co.com.meli.coupon.model.item.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = ItemFeignClientService.FEIGN_NAME, url = "${CO_COM_MELI_COUPON_ITEM_CLIENT_URL}")
public interface ItemFeignClientService {

  String FEIGN_NAME = "ItemClient";

  @GetMapping(value ="/items/{item_id}")
  Item getItem(@PathVariable("item_id") String item_id);

}
