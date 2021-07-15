package co.com.meli.coupon.model.item;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
@Getter
@SuppressWarnings("squid:S116")
public class Item {

  private String id;

  private String title;

  private Float price;

  private String site_id;

}
