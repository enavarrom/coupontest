package co.com.meli.coupon.api.resource.coupon;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Setter
@Getter
@SuppressWarnings("squid:S116")
public class CouponRequestResource {

  private List<String> item_ids;

  private Float amount;

}
