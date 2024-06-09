package pk.edu.pl.productdesktopapp.core;

import static pk.edu.pl.productdesktopapp.core.View.MENU;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import pk.edu.pl.productdesktopapp.view.menu.MenuController;
import pk.edu.pl.productdesktopapp.view.mode.Mode;
import pk.edu.pl.productdesktopapp.view.product.ProductController;
import pk.edu.pl.productdesktopapp.viewmodel.ViewModelFactory;

@RequiredArgsConstructor
@Component
public final class ViewHandler {
  private static final String resourceUrl = "/pk/edu/pl/productdesktopapp/%s-view.fxml";

  private final ViewModelFactory viewModelFactory;

  @Getter @Setter
  private static Stage stage;

  public void start() {
    openView(MENU);
  }

  public void openView(View viewToOpen) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader();
      fxmlLoader.setLocation(getClass().getResource(resourceUrl.formatted(viewToOpen.getFileName())));
      
      Parent root = fxmlLoader.load();
      switch (viewToOpen) {
        case PRODUCT:
          ProductController productController = fxmlLoader.getController();
          productController.init(viewModelFactory.getProductViewModel().init(), Mode.VIEW);
          break;
        case MENU:
          MenuController menuController = fxmlLoader.getController();
          menuController.init(viewModelFactory.getMenuViewModel().init(), Mode.VIEW);
          break;
      }
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle(viewToOpen.getLabel());
      stage.show();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
