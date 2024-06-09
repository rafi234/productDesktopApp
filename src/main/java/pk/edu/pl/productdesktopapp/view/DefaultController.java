package pk.edu.pl.productdesktopapp.view;

import org.springframework.stereotype.Component;
import pk.edu.pl.productdesktopapp.view.mode.Mode;

@Component
public abstract class DefaultController<T> {
    protected T viewModel;

    protected Mode mode;

    public void init(T viewModel, Mode mode) {
        this.viewModel = viewModel;
        this.mode = mode;
        configureFields();
        addListeners();
    }

    protected void configureFields() {}

    protected void addListeners() {}

    /**
     If edit is true then enter edit mode otherwise exit edit mode and open mode view
     */
    protected void setModeEdit(boolean edit) {
        mode = edit ? Mode.EDIT : Mode.VIEW;
    }

    /**
     If add is true then enter add mode otherwise exit add mode and open mode view
     */
    protected void setModeAdd(boolean add) {
        mode = add ? Mode.ADD : Mode.VIEW;
    }
}
