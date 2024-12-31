package fantasyrollenspiel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.ProgressBar;

public class HelloApplication extends Application {

    private int heroHealth = 100;
    private int enemyHealth = 100;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fantasyrollenspiel/hello-view.fxml"));
        AnchorPane root = loader.load();

        Scene scene = new Scene(root);
        HelloController controller = loader.getController();

        // Erstellen und Hinzufügen der neuen Buttons und Healthbars
        VBox buttonBox = new VBox(10);

        ProgressBar heroHealthBar = new ProgressBar(heroHealth / 100.0);
        ProgressBar enemyHealthBar = new ProgressBar(enemyHealth / 100.0);

        fantasyrollenspiel.Buttons.Tränke tränkeButton = new fantasyrollenspiel.Buttons.Tränke(primaryStage);
        buttonBox.getChildren().addAll(tränkeButton);


        root.getChildren().add(buttonBox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
