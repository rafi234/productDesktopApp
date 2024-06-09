package pk.edu.pl.productdesktopapp.view.menu;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Component;
import pk.edu.pl.productdesktopapp.config.SpringContext;
import pk.edu.pl.productdesktopapp.core.View;
import pk.edu.pl.productdesktopapp.core.ViewHandler;
import pk.edu.pl.productdesktopapp.view.DefaultController;
import pk.edu.pl.productdesktopapp.viewmodel.menu.MenuViewModel;

@Component
@NoArgsConstructor
public class MenuController extends DefaultController<MenuViewModel> {

  @Transient
  @Getter(lazy = true)
  private final ViewHandler viewHandler = SpringContext.getContext().getBean(ViewHandler.class);

  public void onStartButton() {
    getViewHandler().openView(View.PRODUCT);
  }
}
