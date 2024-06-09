package pk.edu.pl.productdesktopapp.tools.specification;

public interface Specification<T> {
    boolean isSatisfiedBy();

    String getErrorMessage();
}
