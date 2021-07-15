package co.com.meli.coupon.api.resource.coupon;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
@Getter
@SuppressWarnings("squid:S116")
public class CouponResponseResource {

  private List<String> item_ids;

  private Float total;

}
