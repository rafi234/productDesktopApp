package pk.edu.pl.productdesktopapp.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import pk.edu.pl.productdesktopapp.ProductApplication;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SpringContext {

  @Getter private static ConfigurableApplicationContext context;

  public static void init() {
    if (context == null) {
      context = new SpringApplicationBuilder(ProductApplication.class).run();
      context.start();
    }
  }

  public static void close() {
    if (context != null) {
      context.close();
    }
  }
}
