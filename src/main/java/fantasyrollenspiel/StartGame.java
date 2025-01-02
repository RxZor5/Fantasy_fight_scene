package fantasyrollenspiel;

import fantasyrollenspiel.Fight.Buttons.Tränke;
import fantasyrollenspiel.Fight.Rounds.LevelManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StartGame extends Application {

    private static Stage primaryStage;
    private static int totalCoins = 0;
    private static int totalIron = 0;
    private static int totalXp = 0;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStage.setOnCloseRequest(event -> LevelManager.resetLevel()); // Setze das Level auf 0 beim Programmende
        switchScene("/Map/map.fxml");
        primaryStage.show();
    }

    public static void switchScene(String fxml) throws Exception {
        switchScene(fxml, 0, 0, 0);
    }

    public static void switchScene(String fxml, int coinsEarned, int ironEarned) throws Exception {
        switchScene(fxml, coinsEarned, ironEarned, 0);
    }

    public static void switchScene(String fxml, int coinsEarned, int ironEarned, int xpEarned) throws Exception {
        FXMLLoader loader = new FXMLLoader(StartGame.class.getResource(fxml));
        Object root = loader.load();
        Scene scene;

        if (root instanceof AnchorPane) {
            scene = new Scene((AnchorPane) root);
        } else if (root instanceof GridPane) {
            scene = new Scene((GridPane) root);
        } else if (root instanceof BorderPane) {
            scene = new Scene((BorderPane) root);
        } else {
            throw new IllegalArgumentException("Unsupported root element: " + root.getClass().getName());
        }

        primaryStage.setScene(scene);

        if (fxml.equals("/fantasyrollenspiel/fight.fxml")) {
            FightController controller = loader.getController();
            controller.addTränkeButton(new Tränke(primaryStage));
        } else if (fxml.equals("/Map/map.fxml")) {
            TileMapController controller = loader.getController();
            totalCoins += coinsEarned;
            totalIron += ironEarned;
            totalXp += xpEarned;
            controller.setCoins(totalCoins);  // Set the total coins in the map controller
            controller.setIron(totalIron);  // Set the total iron in the map controller
            controller.setXP(totalXp);  // Set the total XP in the map controller
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
