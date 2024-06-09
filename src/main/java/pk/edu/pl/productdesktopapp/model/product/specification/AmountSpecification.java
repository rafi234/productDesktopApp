package pk.edu.pl.productdesktopapp.model.product.specification;

import lombok.RequiredArgsConstructor;
import pk.edu.pl.productdesktopapp.tools.specification.Specification;

@RequiredArgsConstructor
public final class AmountSpecification implements Specification<String> {

  private final String rowAmount;

  private static final Long MAX_AMOUNT = 1_000_000_000L;

  @Override
  public boolean isSatisfiedBy() {
    if (rowAmount == null) return false;
    try {
      long amount = Long.parseLong(rowAmount);
      return amount <= MAX_AMOUNT && amount >= 0L;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  @Override
  public String getErrorMessage() {
    return "Pole ilość nie może:\n\t- być puste\n\t- przekroczyć 1 000 000 000\n\t- być mniejsze od 0";
  }
}
