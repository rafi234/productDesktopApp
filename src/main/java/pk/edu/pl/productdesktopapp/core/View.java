package pk.edu.pl.productdesktopapp.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum View {

  PRODUCT("product", "Product"),
  MENU("menu", "Menu");

  private final String fileName;
  private final String label;
}
