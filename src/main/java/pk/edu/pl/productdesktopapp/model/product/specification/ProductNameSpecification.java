package pk.edu.pl.productdesktopapp.model.product.specification;

import lombok.RequiredArgsConstructor;
import pk.edu.pl.productdesktopapp.tools.specification.Specification;

@RequiredArgsConstructor
public class ProductNameSpecification implements Specification<String> {

  private final String productName;

  @Override
  public boolean isSatisfiedBy() {

    return productName != null && !productName.isBlank() && productName.matches("^[a-zA-Z ]+$");
  }

  @Override
  public String getErrorMessage() {
    return "Pole \"Nazwa produktu\" nie może:\n\t- być puste\n\t- zawierać znaków innych niż litery i spacje";
  }
}
