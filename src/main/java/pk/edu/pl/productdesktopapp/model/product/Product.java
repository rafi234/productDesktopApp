package pk.edu.pl.productdesktopapp.model.product;

import static pk.edu.pl.productdesktopapp.tools.specification.SpecificationTools.checkRule;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Component;
import pk.edu.pl.productdesktopapp.config.SpringContext;
import pk.edu.pl.productdesktopapp.model.history.History;
import pk.edu.pl.productdesktopapp.model.product.specification.AmountSpecification;
import pk.edu.pl.productdesktopapp.model.product.specification.PriceSpecification;
import pk.edu.pl.productdesktopapp.model.product.specification.ProductNameSpecification;
import pk.edu.pl.productdesktopapp.service.product.ProductService;
import pk.edu.pl.productdesktopapp.service.util.HttpUtils;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
@FieldNameConstants
@JsonSerialize
@EqualsAndHashCode(callSuper = false, of = "id")
public class Product extends History<Product> implements ProductModel {

  @Transient
  @JsonIgnore
  @Getter(lazy = true)
  private final ProductService productService =
      SpringContext.getContext().getBean(ProductService.class);

  private String id;
  private String productName;
  private Long amount;
  private BigDecimal price;
  private Boolean enabled;

  public static Product fromHttp(Map<String, String> response) {
    return Product.builder()
        .id(response.get(Fields.id))
        .productName(response.get(Fields.productName))
        .amount(HttpUtils.getLong(response.getOrDefault(Fields.amount, "0")))
        .price(HttpUtils.getBigDecimal(response.getOrDefault(Fields.price, "0")))
        .enabled(HttpUtils.getBoolean(response.get(Fields.enabled)))
        .build();
  }

  public static Product create(String productName, String price, String amount) {
    checkRule(new AmountSpecification(amount));
    checkRule(new ProductNameSpecification(productName));
    checkRule(new PriceSpecification(price));

    Product product =
        Product.builder()
            .productName(productName)
            .price(new BigDecimal(price))
            .amount(Long.parseLong(amount))
            .build();
    return product;
  }

  public static String getNameFromHttp(Map<String, String> response) {
    return response.get(Fields.productName);
  }

  @Override
  public List<Map<String, String>> getAll(boolean active) {
    return getProductService().getAll(active);
  }

  @Override
  public Optional<Product> getOne(String id) {
    return Optional.ofNullable(getProductService().getOne(id));
  }

  @Override
  public void delete(Product product) {
    getProductService().delete(product.getId());
  }

  @Override
  public void update(Product product) {
    getProductService().updateOne(product, product.getId());
  }

  @Override
  public Product addOne(Product product) {
    return getProductService().addOne(product);
  }

  @Override
  public Product findByName(String name) {
    return getProductService().getOneByName(name);
  }

  public void setAmount(String rowAmount) {
    checkRule(new AmountSpecification(rowAmount));
    this.amount = Long.parseLong(rowAmount);
  }

  public void setPrice(String rowPrice) {
    checkRule(new PriceSpecification(rowPrice));
    this.price = new BigDecimal(rowPrice);
  }
}
