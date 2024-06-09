package pk.edu.pl.productdesktopapp;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.*;
import pk.edu.pl.productdesktopapp.config.SpringContext;
import pk.edu.pl.productdesktopapp.core.ViewHandler;
import pk.edu.pl.productdesktopapp.model.factories.ModelFactory;
import pk.edu.pl.productdesktopapp.viewmodel.ViewModelFactory;

@SpringBootApplication
public class ProductApplication extends Application {

  private ConfigurableApplicationContext context;

  @Override
  public void init() throws Exception {
    SpringContext.init();
  }

  @Override
  public void start(Stage stage) throws IOException {
    ModelFactory mf = new ModelFactory();
    ViewModelFactory viewModelFactory = new ViewModelFactory(mf);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    ViewHandler.setStage(stage);
    viewHandler.start();
  }

  @Override
  public void stop() throws Exception {
    SpringContext.close();
  }

  private Object getBean(Class<?> name) {
    return SpringContext.getContext().getBean(name);
  }

  public static void main(String[] args) {
    launch(args);
  }
}
