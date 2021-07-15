package co.com.meli.coupon.api.controller.coupon.impl;

import co.com.meli.coupon.api.controller.coupon.CouponController;
import co.com.meli.coupon.api.resource.coupon.CouponRequestResource;
import co.com.meli.coupon.api.resource.coupon.CouponResponseResource;
import co.com.meli.coupon.model.item.Item;
import co.com.meli.coupon.service.coupon.CouponService;
import co.com.meli.coupon.service.integration.ItemFeignClientService;
import co.com.meli.coupon.service.item.ItemService;
import com.google.gson.Gson;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest({CouponController.class, CouponService.class, ItemService.class})
class CouponControllerImplTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ItemFeignClientService itemFeignClientService;

  @Test
  public void shouldReturnCouponResultTest() throws Exception {
    String item1 = "MLA1";
    String item2 = "MLA2";
    String item3 = "MLA3";
    String item4 = "MLA4";
    String item5 = "MLA5";

    Float amount = 500f;

    Mockito.doReturn(Item.builder()
        .id(item1)
        .price(100f)
        .build())
        .when(itemFeignClientService)
        .getItem(item1);

    Mockito.doReturn(Item.builder()
        .id(item2)
        .price(210f)
        .build())
        .when(itemFeignClientService)
        .getItem(item2);

    Mockito.doReturn(Item.builder()
        .id(item3)
        .price(260f)
        .build())
        .when(itemFeignClientService)
        .getItem(item3);

    Mockito.doReturn(Item.builder()
        .id(item4)
        .price(80f)
        .build())
        .when(itemFeignClientService)
        .getItem(item4);

    Mockito.doReturn(Item.builder()
        .id(item5)
        .price(90f)
        .build())
        .when(itemFeignClientService)
        .getItem(item5);

    CouponRequestResource couponRequestResource = CouponRequestResource.builder()
        .amount(amount)
        .item_ids(Lists.newArrayList(item1, item2, item3, item4, item5))
        .build();

    Gson gson = new Gson();
    String inputJson = gson.toJson(couponRequestResource);

    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/coupon/")
        .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

    String content = mvcResult.getResponse().getContentAsString();
    CouponResponseResource couponResponseResource = gson.fromJson(content, CouponResponseResource.class);

    Assertions.assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    Assertions.assertThat(couponResponseResource).isNotNull();
    Assertions.assertThat(couponResponseResource.getItem_ids()).hasSize(4);
    Assertions.assertThat(couponResponseResource.getItem_ids().contains("MLA1")).isTrue();
    Assertions.assertThat(couponResponseResource.getItem_ids().contains("MLA2")).isTrue();
    Assertions.assertThat(couponResponseResource.getItem_ids().contains("MLA4")).isTrue();
    Assertions.assertThat(couponResponseResource.getItem_ids().contains("MLA5")).isTrue();

    Assertions.assertThat(couponResponseResource.getTotal()).isEqualTo(480f);
  }

  @Test
  public void shouldReturnStatus404WhenAmountIsLessThanItemsCouponResultTest() throws Exception {
    String item1 = "MLA1";
    String item2 = "MLA2";
    String item3 = "MLA3";
    String item4 = "MLA4";
    String item5 = "MLA5";

    Float amount = 50f;

    Mockito.doReturn(Item.builder()
        .id(item1)
        .price(100f)
        .build())
        .when(itemFeignClientService)
        .getItem(item1);

    Mockito.doReturn(Item.builder()
        .id(item2)
        .price(210f)
        .build())
        .when(itemFeignClientService)
        .getItem(item2);

    Mockito.doReturn(Item.builder()
        .id(item3)
        .price(260f)
        .build())
        .when(itemFeignClientService)
        .getItem(item3);

    Mockito.doReturn(Item.builder()
        .id(item4)
        .price(80f)
        .build())
        .when(itemFeignClientService)
        .getItem(item4);

    Mockito.doReturn(Item.builder()
        .id(item5)
        .price(90f)
        .build())
        .when(itemFeignClientService)
        .getItem(item5);

    CouponRequestResource couponRequestResource = CouponRequestResource.builder()
        .amount(amount)
        .item_ids(Lists.newArrayList(item1, item2, item3, item4, item5))
        .build();

    Gson gson = new Gson();
    String inputJson = gson.toJson(couponRequestResource);

    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/coupon/")
        .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

    String content = mvcResult.getResponse().getContentAsString();
    CouponResponseResource couponResponseResource = gson.fromJson(content, CouponResponseResource.class);

    Assertions.assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    Assertions.assertThat(couponResponseResource).isNull();
  }



}