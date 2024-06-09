package pk.edu.pl.productdesktopapp.model;

import pk.edu.pl.productdesktopapp.model.product.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CrudModel<T> extends DefaultModel<T> {
    List<Map<String, String>> getAll(boolean active);

    Optional<T> getOne(String id);

    void delete(T model);

    void update(T model);

    Product addOne(T model);
}
