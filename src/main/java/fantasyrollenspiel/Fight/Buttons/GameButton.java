package fantasyrollenspiel.Fight.Buttons;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

/**
 * Repräsentiert einen allgemeinen Spielknopf im Spiel.
 */
public class GameButton extends Button {

    /**
     * Konstruktor zum Erstellen eines Spielknopfs.
     *
     * @param text Der Text, der auf dem Knopf angezeigt wird.
     */
    public GameButton(String text) {
        super(text);
        // Gemeinsame Logik für alle Buttons
    }

    /**
     * Zeigt einen Informations-Alert an.
     *
     * @param title   Der Titel des Alerts.
     * @param message Die Nachricht des Alerts.
     */
    protected void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
