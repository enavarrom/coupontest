package co.com.meli.coupon.service.coupon.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class CouponServiceImplTest {

  @InjectMocks
  private CouponServiceImpl couponService;

  @BeforeEach
  public void initTest() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void calculateCouponWhenExistItemsTest() {
    Map<String, Float> items = new HashMap<>();
    items.put("MLA1",100f);
    items.put("MLA2",210f);
    items.put("MLA3",260f);
    items.put("MLA4",80f);
    items.put("MLA5",90f);

    Float amount = 500f;

    List<String> result = couponService.calculate(items, amount);

    Assertions.assertThat(result).isNotEmpty();
    Assertions.assertThat(result).hasSize(4);
    Assertions.assertThat(result.contains("MLA1")).isTrue();
    Assertions.assertThat(result.contains("MLA2")).isTrue();
    Assertions.assertThat(result.contains("MLA4")).isTrue();
    Assertions.assertThat(result.contains("MLA5")).isTrue();
  }

  @Test
  public void calculateCouponWhenExistItemsCase2Test() {
    Map<String, Float> items = new HashMap<>();
    items.put("MLA1",850f);
    items.put("MLA2",900f);
    items.put("MLA3",100f);
    items.put("MLA4",2000f);
    items.put("MLA5",1200f);
    items.put("MLA6",1300f);
    items.put("MLA7",160f);

    Float amount = 3500f;

    List<String> result = couponService.calculate(items, amount);

    Assertions.assertThat(result).isNotEmpty();
    Assertions.assertThat(result).hasSize(4);
    Assertions.assertThat(result.contains("MLA2")).isTrue();
    Assertions.assertThat(result.contains("MLA3")).isTrue();
    Assertions.assertThat(result.contains("MLA5")).isTrue();
    Assertions.assertThat(result.contains("MLA6")).isTrue();
  }

  @Test
  public void calculateCouponWhenAmountIsLessThanItemsValuesTest() {
    Map<String, Float> items = new HashMap<>();
    items.put("MLA1",850f);
    items.put("MLA2",900f);
    items.put("MLA3",100f);
    items.put("MLA4",2000f);
    items.put("MLA5",1200f);
    items.put("MLA6",1300f);
    items.put("MLA7",160f);

    Float amount = 50f;

    List<String> result = couponService.calculate(items, amount);

    Assertions.assertThat(result).isEmpty();
  }

  @Test
  public void calculateCouponWhenDontHaveItemsTest() {
    Map<String, Float> items = new HashMap<>();

    Float amount = 50f;

    List<String> result = couponService.calculate(items, amount);

    Assertions.assertThat(result).isEmpty();
  }


}