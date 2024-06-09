package pk.edu.pl.productdesktopapp.model.product;


import pk.edu.pl.productdesktopapp.model.CrudModel;

public interface ProductModel extends CrudModel<Product> {
    Product findByName(String name);
}
