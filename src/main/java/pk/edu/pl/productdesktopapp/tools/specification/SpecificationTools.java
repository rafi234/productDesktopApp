package pk.edu.pl.productdesktopapp.tools.specification;

import static pk.edu.pl.productdesktopapp.tools.errorHandling.ErrorHandling.showWarningWithMessage;

public class SpecificationTools {

    public static <T> void checkRule(Specification<T> specification) {
        if (specification.isSatisfiedBy()) {
            return;
        }
        showWarningWithMessage(specification.getErrorMessage());
        throw new InputParametersException(specification.getErrorMessage());
    }
}
