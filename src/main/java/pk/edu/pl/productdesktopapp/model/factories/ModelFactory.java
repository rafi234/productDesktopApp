package pk.edu.pl.productdesktopapp.model.factories;

import org.springframework.stereotype.Component;
import pk.edu.pl.productdesktopapp.model.menu.Menu;
import pk.edu.pl.productdesktopapp.model.menu.MenuModel;
import pk.edu.pl.productdesktopapp.model.product.Product;
import pk.edu.pl.productdesktopapp.model.product.ProductModel;

@Component
public final class ModelFactory {

    private MenuModel menuModel;
    private ProductModel productModel;

    public ProductModel getProductModel() {
        if (productModel == null) {
            productModel = new Product();
        }
        return productModel;
    }

    public MenuModel getMenuModel() {
        if (menuModel == null) {
            menuModel = new Menu();
        }
        return menuModel;
    }
}
