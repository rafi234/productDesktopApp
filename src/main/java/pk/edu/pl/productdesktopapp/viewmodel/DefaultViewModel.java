package pk.edu.pl.productdesktopapp.viewmodel;

public abstract class DefaultViewModel<T> {

  protected T model;

  protected DefaultViewModel(T model) {
    this.model = model;
  }

  public abstract DefaultViewModel<T> init();

  protected void refreshFields() {
    init();
  }
}
