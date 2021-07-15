package co.com.meli.coupon.service.item;

import co.com.meli.coupon.model.item.Item;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ItemService {

  Map<String, Float> getMapItemsPrices(List<String> itemsIds);

  Optional<Item> findById(String id);

}
