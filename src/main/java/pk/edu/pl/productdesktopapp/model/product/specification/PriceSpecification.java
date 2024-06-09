package pk.edu.pl.productdesktopapp.model.product.specification;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import pk.edu.pl.productdesktopapp.tools.specification.Specification;

@RequiredArgsConstructor
public final class PriceSpecification implements Specification<String> {

  private final String rowPrice;

  @Override
  public boolean isSatisfiedBy() {
    if (rowPrice == null) return false;
    try {
      BigDecimal price = new BigDecimal(rowPrice);
      return price.compareTo(BigDecimal.ZERO) > 0 && price.scale() <= 2;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  @Override
  public String getErrorMessage() {
    return "Pole cena nie może:\n\t- być puste\n\t- być mniejsze od 0\n\t- mieć wiecej niż 2 cyfry po przecinku";
  }
}
