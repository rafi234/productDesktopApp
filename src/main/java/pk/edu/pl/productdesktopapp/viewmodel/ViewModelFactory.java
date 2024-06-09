package pk.edu.pl.productdesktopapp.viewmodel;

import lombok.Getter;
import org.springframework.stereotype.Component;
import pk.edu.pl.productdesktopapp.model.factories.ModelFactory;
import pk.edu.pl.productdesktopapp.viewmodel.menu.MenuViewModel;
import pk.edu.pl.productdesktopapp.viewmodel.product.ProductViewModel;

@Component
@Getter
public class ViewModelFactory {

  private final ModelFactory modelFactory;

  @Getter(lazy = true)
  private final ProductViewModel productViewModel =
      new ProductViewModel(modelFactory.getProductModel());

  @Getter(lazy = true)
  private final MenuViewModel menuViewModel = new MenuViewModel(modelFactory.getMenuModel());

  public ViewModelFactory(ModelFactory modelFactory) {
    this.modelFactory = modelFactory;
  }
}
