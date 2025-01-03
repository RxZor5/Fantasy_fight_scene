package fantasyrollenspiel;

import fantasyrollenspiel.Controller.ShopController;
import fantasyrollenspiel.Controller.FightController;
import fantasyrollenspiel.Controller.TileMapController;
import fantasyrollenspiel.Fight.Rounds.LevelManager;
import fantasyrollenspiel.Hero.Hero;
import fantasyrollenspiel.Fight.Waffen.BoneBow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Die Hauptklasse, die das Spiel startet.
 */
public class StartGame extends Application {

    private static Stage primaryStage;
    private static int totalCoins = 0;
    private static int totalIron = 0;
    private static int totalXp = 0;
    public static Hero hero;

    /**
     * Startet das Hauptfenster des Spiels.
     *
     * @param stage Das Hauptfenster.
     * @throws Exception Wenn ein Fehler auftritt.
     */
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStage.setOnCloseRequest(event -> LevelManager.resetLevel()); // Setzt das Level am Ende des Programms zurück

        // Initialisiert die Heldeninstanz mit einer Anfangswaffe
        hero = new Hero("Spieler", 100, 0, 0);
        BoneBow startWaffe = new BoneBow();
        startWaffe.setPrice(0); // Setzt den Preis der Anfangswaffe auf 0
        hero.equipWeapon(startWaffe);
        hero.setXp(totalXp);
        hero.setLevel(1); // Setzt das Anfangslevel
        switchScene("/Map/map.fxml");
        primaryStage.show();
    }

    /**
     * Wechselt die Szene im Hauptfenster.
     *
     * @param fxml Der Pfad zur FXML-Datei der neuen Szene.
     * @throws Exception Wenn ein Fehler auftritt.
     */
    public static void switchScene(String fxml) throws Exception {
        switchScene(fxml, 0, 0, 0);
    }

    /**
     * Wechselt die Szene im Hauptfenster und aktualisiert die verdienten Ressourcen.
     *
     * @param fxml        Der Pfad zur FXML-Datei der neuen Szene.
     * @param coinsEarned Die verdienten Münzen.
     * @param ironEarned  Das verdiente Eisen.
     * @throws Exception Wenn ein Fehler auftritt.
     */
    public static void switchScene(String fxml, int coinsEarned, int ironEarned) throws Exception {
        switchScene(fxml, coinsEarned, ironEarned, 0);
    }

    /**
     * Wechselt die Szene im Hauptfenster und aktualisiert die verdienten Ressourcen.
     *
     * @param fxml        Der Pfad zur FXML-Datei der neuen Szene.
     * @param coinsEarned Die verdienten Münzen.
     * @param ironEarned  Das verdiente Eisen.
     * @param xpEarned    Die verdienten Erfahrungspunkte.
     * @throws Exception Wenn ein Fehler auftritt.
     */
    public static void switchScene(String fxml, int coinsEarned, int ironEarned, int xpEarned) throws Exception {
        // Prüfen, ob die Szene die Kampfszene ist und die Ressourcen nur einmal aktualisieren
        if (!fxml.equals("/fantasyrollenspiel/fight.fxml")) {
            updateResources(coinsEarned, ironEarned, xpEarned);
        }

        System.out.println("Wechsel der Szene zu: " + fxml);
        System.out.println("Verdiente Münzen: " + coinsEarned + ", Verdientes Eisen: " + ironEarned + ", Verdiente XP: " + xpEarned);

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
            throw new IllegalArgumentException("Nicht unterstütztes Root-Element: " + root.getClass().getName());
        }

        primaryStage.setScene(scene);

        if (fxml.equals("/Map/map.fxml")) {
            TileMapController controller = loader.getController();
            controller.setHero(hero); // Setzen des Heldenobjekts
            controller.setCoins(hero.getCoins());
            controller.setIron(hero.getIron());
            controller.setXP(hero.getXp()); // Falls XP auch wichtig ist

        } else if (fxml.equals("/fantasyrollenspiel/shop.fxml")) {
            ShopController shopController = loader.getController();
            shopController.setHero(hero); // Setzen des Heldenobjekts
            shopController.setCoins(hero.getCoins()); // Münzen setzen
            shopController.setIron(hero.getIron()); // Eisen setzen

        } else if (fxml.equals("/fantasyrollenspiel/fight.fxml")) {
            FightController fightController = loader.getController();
            fightController.setHero(hero);
        }
    }

    /**
     * Aktualisiert die Ressourcen des Helden.
     *
     * @param coinsEarned Die verdienten Münzen.
     * @param ironEarned  Das verdiente Eisen.
     * @param xpEarned    Die verdienten Erfahrungspunkte.
     */
    private static void updateResources(int coinsEarned, int ironEarned, int xpEarned) {
        totalCoins += coinsEarned;
        totalIron += ironEarned;
        totalXp += xpEarned;
        hero.setXp(totalXp); // Setzen des aktualisierten XP im Helden

        System.out.println("Aktualisierte Ressourcen - Münzen: " + totalCoins + ", Eisen: " + totalIron + ", XP: " + totalXp);
    }

    /**
     * Die Main-Methode zum Starten des Spiels.
     *
     * @param args Die Argumente der Kommandozeile.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
