package pk.edu.pl.productdesktopapp.viewmodel.menu;

import pk.edu.pl.productdesktopapp.model.menu.MenuModel;
import pk.edu.pl.productdesktopapp.viewmodel.DefaultViewModel;

public class MenuViewModel extends DefaultViewModel<MenuModel> {
    public MenuViewModel(MenuModel model) {
        super(model);
    }

    @Override
    public MenuViewModel init() {
        return this;
    }
}
