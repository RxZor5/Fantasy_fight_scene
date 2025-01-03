package fantasyrollenspiel;

import fantasyrollenspiel.Controller.ShopController;
import fantasyrollenspiel.Controller.FightController;
import fantasyrollenspiel.Controller.TileMapController;
import fantasyrollenspiel.Fight.Rounds.LevelManager;
import fantasyrollenspiel.Hero.Hero;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
    public static Hero hero;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStage.setOnCloseRequest(event -> LevelManager.resetLevel()); // Reset the level at the end of the program

        // Initialize the hero instance
        hero = new Hero("Spieler", 100, 0, 0);

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
        System.out.println("Switching scene to: " + fxml);
        System.out.println("Coins Earned: " + coinsEarned + ", Iron Earned: " + ironEarned + ", XP Earned: " + xpEarned);

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

        if (fxml.equals("/fantasyrollenspiel/shop.fxml")) {
            ShopController shopController = loader.getController();
            FightController fightController = new FightController();
            totalCoins = coinsEarned;
            totalIron = ironEarned;
            System.out.println("Passing coins to ShopController: " + totalCoins + " and iron: " + totalIron);
            shopController.setHero(hero);
            shopController.setFightController(fightController);
            shopController.setCoins(totalCoins);
            shopController.setIron(totalIron);

        } else if (fxml.equals("/Map/map.fxml")) {
            TileMapController controller = loader.getController();
            totalCoins += coinsEarned;
            totalIron += ironEarned;
            totalXp += xpEarned;
            System.out.println("Updated totals - Coins: " + totalCoins + ", Iron: " + totalIron + ", XP: " + totalXp);
            controller.setCoins(totalCoins);
            controller.setIron(totalIron);
            controller.setXP(totalXp);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }


}
