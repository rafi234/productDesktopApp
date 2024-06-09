package pk.edu.pl.productdesktopapp.view.converter;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.StringConverter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class StringStringPropertyConverter extends StringConverter<StringProperty> {

  @Override
  public String toString(StringProperty object) {
    return object.getValue();
  }

  @Override
  public StringProperty fromString(String string) {
    return new SimpleStringProperty(string);
  }
}
