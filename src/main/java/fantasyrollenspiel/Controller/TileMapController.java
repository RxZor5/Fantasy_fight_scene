package fantasyrollenspiel.Controller;

import fantasyrollenspiel.Hero.Hero;
import fantasyrollenspiel.StartGame;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import fantasyrollenspiel.Animations.Animations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Der Controller für die Kachelkarte im Spiel.
 */
public class TileMapController {

    @FXML
    private GridPane gridPane;

    @FXML
    private Label coinsLabel;

    @FXML
    private Label ironLabel;

    @FXML
    private Label playerLevelLabel;  // Spielerlevel-Label

    @FXML
    private ProgressBar levelProgressBar;

    @FXML
    private ScrollPane scrollPane;  // ScrollPane-Feld hinzufügen

    @FXML
    private TextArea battleLogTextArea;  // Battle Log TextArea

    private ImageView player;
    private int playerRow = 0;
    private int playerCol = 0;

    private Animations animations;

    private int coins = 0;
    private int iron = 0;
    private int xp = 0;
    private int xpThreshold = 100;


    private FightController fightController;

    // Held-Variable hinzufügen
    private Hero hero;

    // SessionCoins-Variable hinzufügen
    private int sessionCoins = 0;
    private int sessionIron = 0;

    /**
     * Initialisiert den TileMapController.
     */
    public void initialize() {
        animations = new Animations();
        drawTileMap("src/main/resources/Map/tilemap.txt");
        addPlayer();
        setupKeyEvents();

        // Initialisiert die Heldeninstanz
        hero = StartGame.hero;
        updateStats();

        // Stellt sicher, dass GridPane Tastenereignisse empfangen kann
        gridPane.setFocusTraversable(true);
        gridPane.requestFocus();  // Fordert den Fokus auf das GridPane an

        // Fokushörer hinzufügen, um den Fokus zu bestätigen
        gridPane.focusedProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("GridPane-Fokus: " + newVal);
        });

        // Überprüft, ob der Fokus anfangs gesetzt ist
        System.out.println("Hat GridPane initialen Fokus: " + gridPane.isFocused());

        // Setzt den Fokus auf das GridPane, wenn darauf geklickt wird
        gridPane.setOnMouseClicked(event -> {
            gridPane.requestFocus();
        });

        // Stellt sicher, dass die Fokusrichtlinie von ScrollPane nicht mit dem Fokus von GridPane interferiert
        scrollPane.setFocusTraversable(false);
    }

    private void drawTileMap(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                String[] tiles = line.split(" ");
                for (int col = 0; col < tiles.length; col++) {
                    ImageView imageView = new ImageView();
                    switch (tiles[col]) {
                        case "25":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_26.png"));
                            break;
                        case "24":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_25.png"));
                            break;
                        case "23":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_24.png"));
                            break;
                        case "22":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_23.png"));
                            break;
                        case "21":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_22.png"));
                            break;
                        case "20":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_21.png"));
                            break;
                        case "19":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_20.png"));
                            break;
                        case "18":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_19.png"));
                            break;
                        case "17":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_18.png"));
                            break;
                        case "16":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_17.png"));
                            break;
                        case "15":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_16.png"));
                            break;
                        case "14":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_15.png"));
                            break;
                        case "13":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_14.png"));
                            break;
                        case "12":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_13.png"));
                            break;
                        case "11":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_12.png"));
                            break;
                        case "10":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_11.png"));
                            break;
                        case "9":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_10.png"));
                            break;
                        case "8":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_9.png"));
                            break;
                        case "7":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_8.png"));
                            break;
                        case "6":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_7.png"));
                            break;
                        case "5":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_6.png"));
                            break;
                        case "4":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_5.png"));
                            break;
                        case "3":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_4.png"));
                            break;
                        case "2":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_3.png"));
                            break;
                        case "1":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_2.png"));
                            break;
                        case "0":
                            imageView.setImage(new Image("file:src/main/resources/Map/Bilder/DarkCastle_1.png"));
                            break;
                        // Weitere Fälle für unterschiedliche Tiles hinzufügen
                    }
                    imageView.setFitWidth(64);
                    imageView.setFitHeight(64);
                    gridPane.add(imageView, col, row);
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Fügt den Spieler der Karte hinzu.
     */
    private void addPlayer() {
        player = new ImageView(new Image("file:src/main/resources/fantasyrollenspiel/Bilder/Player.png"));
        player.setFitWidth(64);
        player.setFitHeight(64);
        gridPane.add(player, playerCol, playerRow);
        animations.orcAnimation(player);
    }

    /**
     * Richtet die Tastenereignisse ein.
     */
    private void setupKeyEvents() {
        gridPane.setOnKeyPressed(this::handleKeyPressed);
        gridPane.setFocusTraversable(true);
    }

    /**
     * Behandelt die gedrückten Tastenereignisse.
     *
     * @param event Das Tastenereignis.
     */
    private void handleKeyPressed(KeyEvent event) {
        System.out.println("Taste gedrückt: " + event.getCode());
        switch (event.getCode()) {
            case W -> movePlayer(playerRow - 1, playerCol);
            case S -> movePlayer(playerRow + 1, playerCol);
            case A -> movePlayer(playerRow, playerCol - 1);
            case D -> movePlayer(playerRow, playerCol + 1);
            case E -> {
                if (playerCol == 9 && (playerRow == 7 || playerRow == 8)) {
                    try {
                        // Übergabe der Session-Daten an den FightController
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fantasyrollenspiel/fight.fxml"));
                        Stage stage = (Stage) gridPane.getScene().getWindow();
                        Scene scene = new Scene(loader.load());
                        stage.setScene(scene);

                        FightController fightController = loader.getController();

                        // Übergabe der Session-Werte
                        fightController.setSessionCoins(sessionCoins);
                        fightController.setSessionIron(sessionIron);

                        // Optional: Zurücksetzen der Session-Werte, falls nach dem Kampf nicht mehr benötigt
                        sessionCoins = 0;
                        sessionIron = 0;

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else if (playerCol == 20 && (playerRow == 7 || playerRow == 8)) {
                    // Vorherige Methode zum Öffnen des Shops
                    try {
                        Stage stage = (Stage) gridPane.getScene().getWindow();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fantasyrollenspiel/shop.fxml"));
                        Scene scene = new Scene(loader.load());
                        stage.setScene(scene);

                        ShopController shopController = loader.getController();
                        shopController.setHero(hero);
                        shopController.setFightController(fightController);

                        // Übergabe der aktuellen Münzen und Eisen
                        shopController.setCoins(hero.getCoins());
                        shopController.setIron(hero.getIron());

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                } else if (playerCol == 27 && (playerRow == 7 || playerRow == 8)) {
                    try {
                        Stage stage = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fantasyrollenspiel/inventory.fxml"));
                        Scene scene = new Scene(loader.load());
                        stage.setScene(scene);
                        stage.show();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Bewegt den Spieler auf der Karte.
     *
     * @param row Die neue Zeile.
     * @param col Die neue Spalte.
     */
    private void movePlayer(int row, int col) {
        if (row >= 0 && row < gridPane.getRowCount() && col >= 0 && col < gridPane.getColumnCount()) {
            System.out.println("Bewegt Spieler zu Zeile: " + row + ", Spalte: " + col);
            gridPane.getChildren().remove(player);
            playerRow = row;
            playerCol = col;
            gridPane.add(player, playerCol, playerRow);
            scrollToPlayer();  // Scrollt zum Spieler nach der Bewegung
        }
    }





    /**
     * Scrollt zum Spieler auf der Karte.
     */
    private void scrollToPlayer() {
        double cellHeight = gridPane.getHeight() / gridPane.getRowCount();
        double cellWidth = gridPane.getWidth() / gridPane.getColumnCount();
        double scrollTop = (playerRow * cellHeight) - (scrollPane.getHeight() / 2) + (cellHeight / 2);
        double scrollLeft = (playerCol * cellWidth) - (scrollPane.getWidth() / 2) + (cellWidth / 2);
        scrollPane.setVvalue(scrollTop / (gridPane.getHeight() - scrollPane.getHeight()));
        scrollPane.setHvalue(scrollLeft / (gridPane.getWidth() / gridPane.getWidth()));
    }

    /**
     * Erhöht das Level des Spielers.
     */
    private void levelUp() {
        hero.setLevel(hero.getLevel() + 1);
        hero.setXp(hero.getXp() - xpThreshold); // Behalte den Überschuss an XP
        xpThreshold += 50; // Optional: Erhöhe die XP-Schwelle für das nächste Level
        System.out.println("Spieler hat ein Level aufgestiegen! Neues Level: " + hero.getLevel() + ", Neue XP-Schwelle: " + xpThreshold);
        updateStats();
    }

    /**
     * Aktualisiert die Statistiken des Spielers.
     */
    private void updateStats() {
        playerLevelLabel.setText("Spielerlevel: " + hero.getLevel());
        levelProgressBar.setProgress((double) hero.getXp() / xpThreshold);

        if (hero.getXp() >= xpThreshold) {
            levelUp();
        }
    }



    /**
     * Fügt die angegebene Anzahl Münzen hinzu und aktualisiert die Anzeige.
     *
     * @param amount Die hinzuzufügende Anzahl an Münzen.
     */
    public void addCoins(int amount) {
        coins += amount;
        sessionCoins += amount;
        updateCoinsLabel();
    }

    /**
     * Fügt die angegebene Menge Eisen hinzu und aktualisiert die Anzeige.
     *
     * @param amount Die hinzuzufügende Menge an Eisen.
     */
    public void addIron(int amount) {
        iron += amount;
        sessionIron += amount;
        updateIronLabel();
    }

    /**
     * Fügt die angegebene Anzahl Erfahrungspunkte hinzu und aktualisiert die Statistiken.
     *
     * @param amount Die hinzuzufügende Anzahl an Erfahrungspunkten.
     */
    public void addXP(int amount) {
        xp += amount;
        hero.setXp(hero.getXp() + amount);
        updateStats();
    }

    /**
     * Aktualisiert das Münzen-Label.
     */
    private void updateCoinsLabel() {
        coinsLabel.setText("Coins: " + coins);
    }

    /**
     * Aktualisiert das Eisen-Label.
     */
    private void updateIronLabel() {
        ironLabel.setText("Iron: " + iron);
    }


    /**
     * Setzt die Anzahl der Münzen und aktualisiert die Anzeige.
     *
     * @param amount Die Anzahl der Münzen.
     */
    public void setCoins(int amount) {
        hero.setCoins(amount);
        coinsLabel.setText("Coins: " + amount);
    }

    /**
     * Setzt die Menge an Eisen und aktualisiert die Anzeige.
     *
     * @param amount Die Menge an Eisen.
     */
    public void setIron(int amount) {
        hero.setIron(amount);
        ironLabel.setText("Iron: " + amount);
    }

    /**
     * Setzt die Erfahrungspunkte und aktualisiert die Statistiken.
     *
     * @param amount Die Anzahl der Erfahrungspunkte.
     */
    public void setXP(int amount) {
        hero.setXp(amount);
        updateStats();
    }

    /**
     * Setzt den Helden für den TileMapController.
     *
     * @param hero Der Held.
     */
    public void setHero(Hero hero) {
        this.hero = hero;
        updateStats(); // Aktualisieren der Anzeige nach dem Setzen des Helden
    }
}
