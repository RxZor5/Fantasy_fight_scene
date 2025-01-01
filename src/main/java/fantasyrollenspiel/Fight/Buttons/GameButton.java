package fantasyrollenspiel.Fight.Buttons;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class GameButton extends Button {
    public GameButton(String text) {
        super(text);
        // Gemeinsame Logik für alle Buttons
    }

    protected void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
