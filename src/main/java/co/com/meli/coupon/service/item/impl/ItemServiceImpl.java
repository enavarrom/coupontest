package co.com.meli.coupon.service.item.impl;

import co.com.meli.coupon.model.item.Item;
import co.com.meli.coupon.service.integration.ItemFeignClientService;
import co.com.meli.coupon.service.item.ItemService;
import feign.FeignException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

  @Autowired
  private ItemFeignClientService itemFeignClientService;

  @Override
  public Map<String, Float> getMapItemsPrices(List<String> itemsIds) {
    Map<String, Float> mapItemPrices = new HashMap<>();
    itemsIds.stream().parallel()
        .forEach(itemId -> {
                Optional<Item> item = findById(itemId);
                if (item.isPresent()) {
                  mapItemPrices.put(itemId, item.get().getPrice());
                }
            });
    return mapItemPrices;
  }

  @Override
  public Optional<Item> findById(String id) {
    try {
      return Optional.ofNullable(itemFeignClientService.getItem(id));
    }
    catch (FeignException exception) {
      if (HttpStatus.NOT_FOUND.value() == exception.status()) {
        return Optional.empty();
      }
    }
    catch (Exception e) {
      throw e;
    }
    return Optional.empty();
  }
}
