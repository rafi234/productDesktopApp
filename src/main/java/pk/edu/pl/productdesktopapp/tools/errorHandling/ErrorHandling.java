package pk.edu.pl.productdesktopapp.tools.errorHandling;

import javafx.scene.control.Alert;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.client.HttpStatusCodeException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorHandling {

    public static void showWarningWithMessage(String message) {
        Alert alert = createMessage(Alert.AlertType.WARNING, "Błędne dane", message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public static void showWarningWithMessage(HttpStatusCodeException e) {
        Alert alert = createMessage(Alert.AlertType.WARNING, e.getStatusCode().toString(), e.getMessage());
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public static void showErrorWithMessage(RuntimeException e) {
        Alert alert = createMessage(Alert.AlertType.ERROR, "Błąd aplikacji", e.getMessage());
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private static Alert createMessage(Alert.AlertType type, String title, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(text);
        return alert;
    }
}
