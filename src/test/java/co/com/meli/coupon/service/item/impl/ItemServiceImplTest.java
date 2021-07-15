package co.com.meli.coupon.service.item.impl;

import co.com.meli.coupon.model.item.Item;
import co.com.meli.coupon.service.integration.ItemFeignClientService;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class ItemServiceImplTest {

  @InjectMocks
  private ItemServiceImpl itemService;

  @Mock
  private ItemFeignClientService itemFeignClientService;

  @BeforeEach
  public void init() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void getMapItemsPricesWhenExistItemIdsTest() {
    String item1 = "MLA1";
    String item2 = "MLA2";
    List<String> items = Lists.newArrayList(item1, item2);

    Mockito.doReturn(Item.builder()
        .id(item1)
        .title(item1)
        .price(100f)
        .build())
        .when(itemFeignClientService)
        .getItem(item1);

    Mockito.doReturn(Item.builder()
        .id(item2)
        .title(item2)
        .price(200f)
        .build())
        .when(itemFeignClientService)
        .getItem(item2);

    Map<String, Float> mapResult = itemService.getMapItemsPrices(items);

    Assertions.assertThat(mapResult).isNotEmpty();
    Assertions.assertThat(mapResult.get(item1)).isEqualTo(100f);
    Assertions.assertThat(mapResult.get(item2)).isEqualTo(200f);

    Mockito.verify(itemFeignClientService, Mockito.times(1))
        .getItem(item1);

    Mockito.verify(itemFeignClientService, Mockito.times(1))
        .getItem(item2);
  }

  @Test
  public void getEmptyMapItemsPricesWhenNotExistItemIdsTest() {
    List<String> items = Lists.newArrayList();
    Map<String, Float> mapResult = itemService.getMapItemsPrices(items);
    Assertions.assertThat(mapResult).isEmpty();
  }


}