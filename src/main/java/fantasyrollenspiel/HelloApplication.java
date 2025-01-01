package fantasyrollenspiel;

import fantasyrollenspiel.Fight.Buttons.Tränke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.ProgressBar;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fantasyrollenspiel/hello-view.fxml"));
        AnchorPane root = loader.load();

        Scene scene = new Scene(root);
        HelloController controller = 
                loader.getController();

        // Get the GridPane from the FXML file
        GridPane gridPane = (GridPane) root.lookup("#gridPane");

        // Create the new tränkeButton
        Tränke tränkeButton = new Tränke(primaryStage);

        // Add the tränkeButton to the GridPane at the position of Button 4 (column 1, row 1)
        gridPane.add(tränkeButton, 1, 1);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
