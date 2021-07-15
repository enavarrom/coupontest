package co.com.meli.coupon.service.item.impl;

import co.com.meli.coupon.model.item.Item;
import co.com.meli.coupon.service.integration.ItemFeignClientService;
import co.com.meli.coupon.service.item.ItemService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

  @Autowired
  private ItemFeignClientService itemFeignClientService;

  @Override
  public Map<String, Float> getMapItemsPrices(List<String> itemsIds) {
    Map<String, Float> mapItemPrices = new HashMap<>();
    itemsIds.stream().parallel()
        .forEach(s ->
          mapItemPrices.put(s,
              findById(s)
              .flatMap(item -> Optional.ofNullable(item.getPrice()))
              .orElse(0f)
              ));
    return mapItemPrices;
  }

  @Override
  public Optional<Item> findById(String id) {
      return Optional.ofNullable(itemFeignClientService.getItem(id));
  }
}
