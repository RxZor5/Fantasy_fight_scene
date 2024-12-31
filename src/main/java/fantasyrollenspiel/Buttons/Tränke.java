package fantasyrollenspiel.Buttons;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Tränke extends GameButton {
    private Stage primaryStage;

    public Tränke(Stage primaryStage) {
        super("Tränke");
        this.primaryStage = primaryStage;
        this.setOnAction(event -> openWindow());
    }

    private void openWindow() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initOwner(primaryStage);

        VBox layout = new VBox(10);
        layout.getChildren().add(new Label("Tränke-Fenster"));

        Scene scene = new Scene(layout, 200, 100);
        window.setScene(scene);
        window.setTitle("Tränke");
        window.show();
    }
}
