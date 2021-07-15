package co.com.meli.coupon.service.coupon.impl;


import co.com.meli.coupon.service.coupon.CouponService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class CouponServiceImpl implements CouponService {

  private static final Float ZERO_VALUE = 0f;

  @Override
  public List<String> calculate(Map<String, Float> items, Float amount) {
    if (!CollectionUtils.isEmpty(items)) {
      Map<String, Float> itemsMap = getOrderedMap(items);

      Map<String, Float> resultMap = new HashMap<>();
      Float result = ZERO_VALUE;

      while (!itemsMap.isEmpty()) {
        Map<String, Float> resultMapTemp = getResultMap(itemsMap, amount);
        Float resultTemp = getTotalValueMap(resultMapTemp);
        if (resultTemp > result) {
          resultMap = resultMapTemp;
          result = resultTemp;
        }
      }

      return resultMap.keySet().stream().collect(Collectors.toList());
    }

    return Collections.emptyList();
  }

  private Map<String, Float> getOrderedMap(Map<String, Float> items) {
    return items.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .collect(Collectors.toMap(
                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
  }

  private Float getTotalValueMap(Map<String, Float> items) {
    return items.values().stream().reduce(ZERO_VALUE, Float::sum);
  }

  private Map<String, Float> getResultMap(Map<String, Float> items, Float amount) {
    Map<String, Float> result = new HashMap<>();
    Float total = ZERO_VALUE;
    List<String> keyToRemove = new ArrayList<>();
    for (Map.Entry<String, Float> entry : items.entrySet()) {
      if (total.equals(ZERO_VALUE)) {
        keyToRemove.add(entry.getKey());
      }

      Float intermediateValue = total + entry.getValue();

      if (intermediateValue <= amount) {
        total = intermediateValue;
        result.put(entry.getKey(), entry.getValue());
      }

      if (intermediateValue.equals(amount)) {
        break;
      }
    }

    keyToRemove.stream().forEach(items::remove);

    return result;
  }

}
