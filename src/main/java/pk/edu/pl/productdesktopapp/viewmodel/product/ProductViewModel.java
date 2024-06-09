package pk.edu.pl.productdesktopapp.viewmodel.product;

import java.util.stream.Collectors;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.springframework.stereotype.Component;
import pk.edu.pl.productdesktopapp.model.product.Product;
import pk.edu.pl.productdesktopapp.model.product.ProductModel;
import pk.edu.pl.productdesktopapp.tools.errorHandling.ErrorHandling;
import pk.edu.pl.productdesktopapp.tools.specification.InputParametersException;
import pk.edu.pl.productdesktopapp.viewmodel.DefaultViewModel;

@Component
@Getter
public class ProductViewModel extends DefaultViewModel<ProductModel> {

  private ObservableList<StringProperty> productsNames;
  private StringProperty amount;
  private StringProperty price;
  private StringProperty enabled;
  private StringProperty newProductName;

  public ProductViewModel(ProductModel model) {
    super(model);
  }

  @Override
  public ProductViewModel init() {
    amount = new SimpleStringProperty();
    price = new SimpleStringProperty();
    enabled = new SimpleStringProperty();
    newProductName = new SimpleStringProperty();
    loadProducts();
    return this;
  }

  public void updateViewModel(StringProperty selectedName) {
    if (productsNames.isEmpty()) {
      return;
    }
    if (selectedName == null) {
      selectedName = productsNames.get(0);
    }
    Product product = getSelectedItem(selectedName);
    amount.setValue(String.valueOf(product.getAmount()));
    price.setValue(String.valueOf(product.getPrice()));
    enabled.setValue(product.getEnabled() ? "Tak" : "Nie");
  }

  public void updateProduct(StringProperty selectedName) {
    Product product = getSelectedItem(selectedName);
    product.setAmount(amount.get());
    product.setPrice(price.getValue());
    model.update(product);
    loadProducts();
  }

  public void deleteProduct(StringProperty selectedName) {
    if (selectedName == null) {
      return;
    }
    Product product = model.findByName(selectedName.get());
    model.delete(product);
    productsNames.remove(selectedName);
    price.setValue(null);
    amount.setValue(null);
  }

  private Product getSelectedItem(StringProperty selectedItem) {
    if (selectedItem == null) {
      ErrorHandling.showWarningWithMessage("Brak wybranego produktu!");
      throw new InputParametersException("Brak wybranego produktu!");
    }
    return model.findByName(selectedItem.get());
  }

  private void loadProducts() {
    ObservableList<StringProperty> names =
        model.getAll(true).stream()
            .map(Product::getNameFromHttp)
            .map(SimpleStringProperty::new)
            .collect(
                Collectors.collectingAndThen(
                    Collectors.toList(), FXCollections::observableArrayList));
    productsNames = new SimpleListProperty<>(names);
  }

  public void getEmptyForm() {
    price.setValue(null);
    amount.setValue(null);
  }

  public void addProduct() {
    Product newProduct = Product.create(newProductName.get(), price.get(), amount.get());

    model.addOne(newProduct);
    loadProducts();
    getEmptyForm();
  }
}
