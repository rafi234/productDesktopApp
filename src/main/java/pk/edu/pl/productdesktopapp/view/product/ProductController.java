package pk.edu.pl.productdesktopapp.view.product;

import javafx.beans.Observable;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pk.edu.pl.productdesktopapp.config.SpringContext;
import pk.edu.pl.productdesktopapp.view.DefaultController;
import pk.edu.pl.productdesktopapp.view.converter.StringStringPropertyConverter;
import pk.edu.pl.productdesktopapp.view.mode.Mode;
import pk.edu.pl.productdesktopapp.viewmodel.product.ProductViewModel;

@Component
@NoArgsConstructor
public class ProductController extends DefaultController<ProductViewModel> {

  /*
    Services
  */
  @Getter(lazy = true)
  private final StringStringPropertyConverter stringConverter =
      SpringContext.getContext().getBean(StringStringPropertyConverter.class);

  /*
    UI
  */
  @FXML private ComboBox<StringProperty> productNamesField;
  @FXML private TextField priceField;
  @FXML private TextField amountField;
  @FXML private Label enabled;
  @FXML private TextField productNameField;

  @FXML private Button editButton;
  @FXML private Button updateButton;
  @FXML private Button cancelButton;
  @FXML private Button deleteButton;
  @FXML private Button addButton;

  @Override
  public void init(ProductViewModel productViewModel, Mode mode) {
    super.init(productViewModel, mode);
  }

  @Override
  protected void configureFields() {
    productNamesField.setItems(viewModel.getProductsNames());
    productNamesField.setConverter(getStringConverter());
    bindEditable();
    enabled.textProperty().bind(viewModel.getEnabled());
  }

  private void bindEditable() {
    priceField.textProperty().bindBidirectional(viewModel.getPrice());
    amountField.textProperty().bindBidirectional(viewModel.getAmount());
    productNameField.textProperty().bindBidirectional(viewModel.getNewProductName());
  }

  @Override
  protected void addListeners() {
    productNamesField.valueProperty().addListener(this::onProductNameChange);
  }

  public void onProductNameChange(Observable event) {
    viewModel.updateViewModel(getSelectedValue());
  }

  public void onEditButton() {
    setModeEdit(true);
  }

  public void onUpdateButton() {
    viewModel.updateProduct(getSelectedValue());
    setModeEdit(false);
    configureFields();
  }

  public void onDeleteButton() {
    viewModel.deleteProduct(getSelectedValue());
    setModeEdit(false);
    configureFields();
  }

  public void onHistoryButton() {}

  public void onCancelButton() {
    if (mode == Mode.ADD) {
      setModeAdd(false);
    } else {
      setModeEdit(false);
    }
    bindEditable();
  }

  public void onAddButton() {
    if (mode == Mode.ADD) {
      viewModel.addProduct();
      setModeAdd(false);
    } else {
      setModeAdd(true);
    }
    configureFields();
  }

  private StringProperty getSelectedValue() {
    return productNamesField.getSelectionModel().getSelectedItem();
  }


  @Override
  protected void setModeEdit(boolean edit) {
    super.setModeEdit(edit);
    cancelButton.setVisible(edit);
    deleteButton.setVisible(edit);
    updateButton.setVisible(edit);
    editButton.setVisible(!edit);
    productNamesField.setDisable(edit);
    amountField.setEditable(edit);
    priceField.setEditable(edit);
    addButton.setVisible(!edit);
  }

  @Override
  protected void setModeAdd(boolean add) {
    super.setModeAdd(add);
    cancelButton.setVisible(add);
    productNameField.setVisible(add);
    productNamesField.setVisible(!add);
    amountField.setEditable(add);
    priceField.setEditable(add);
    editButton.setVisible(!add);
    if (add) {
      viewModel.getEmptyForm();
    }
  }
}
