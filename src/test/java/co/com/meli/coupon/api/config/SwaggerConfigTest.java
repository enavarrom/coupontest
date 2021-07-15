package co.com.meli.coupon.api.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springfox.documentation.spring.web.plugins.Docket;

class SwaggerConfigTest {

  private SwaggerConfig swaggerConfig;

  @BeforeEach
  public void setUp() {
    swaggerConfig = new SwaggerConfig();
  }

  @Test
  public void docketTest() {
    Docket docket = swaggerConfig.docket();
    Assertions.assertThat(docket).isNotNull();
  }

}