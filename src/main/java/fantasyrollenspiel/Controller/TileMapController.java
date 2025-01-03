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
    private ScrollPane scrollPane;  // Add ScrollPane field

    @FXML
    private TextArea battleLogTextArea;  // Battle Log TextArea

    private ImageView player;
    private int playerRow = 0;
    private int playerCol = 0;

    private Animations animations;

    private int coins = 0;
    private int iron = 0;
    private int xp = 0;
    private int playerLevel = 1;
    private int xpThreshold = 100;
    private static int totalCoins = 0;
    private static int totalIron = 0;

    private FightController fightController;

    public void initialize() {
        animations = new Animations();
        drawTileMap("src/main/resources/Map/tilemap.txt");
        addPlayer();
        setupKeyEvents();

        // Initialize the hero instance
        Hero hero = new Hero("Spieler", 100, 0, 0);
        // Initialize controllers and pass hero instance
        fightController = new FightController();
        ShopController shopController = new ShopController();

        fightController.setHero(hero);
        shopController.setHero(hero);
        shopController.setFightController(fightController);

        // Initialize UI elements
        updateStats();

        // Ensure GridPane can receive key events
        gridPane.setFocusTraversable(true);
        gridPane.requestFocus();  // Request focus on the gridPane

        // Add focus listener to confirm focus
        gridPane.focusedProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("GridPane focus: " + newVal);
        });

        // Check if focus is set initially
        System.out.println("Initial GridPane has focus: " + gridPane.isFocused());

        // Set focus on the GridPane when it's clicked
        gridPane.setOnMouseClicked(event -> {
            gridPane.requestFocus();
        });

        // Ensure ScrollPane focus policy doesn't interfere with GridPane focus
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


    private void addPlayer() {
        player = new ImageView(new Image("file:src/main/resources/fantasyrollenspiel/Bilder/Player.png"));
        player.setFitWidth(64);
        player.setFitHeight(64);
        gridPane.add(player, playerCol, playerRow);
        animations.orcAnimation(player);
    }

    private void setupKeyEvents() {
        gridPane.setOnKeyPressed(this::handleKeyPressed);
        gridPane.setFocusTraversable(true);
    }

    private void handleKeyPressed(KeyEvent event) {
        System.out.println("Key pressed: " + event.getCode());
        switch (event.getCode()) {
            case W -> movePlayer(playerRow - 1, playerCol);
            case S -> movePlayer(playerRow + 1, playerCol);
            case A -> movePlayer(playerRow, playerCol - 1);
            case D -> movePlayer(playerRow, playerCol + 1);
            case E -> {
                if (playerCol == 9 && (playerRow == 7 || playerRow == 8)) {
                    try {
                        StartGame.switchScene("/fantasyrollenspiel/fight.fxml", coins, iron, xp);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else if (playerCol == 20 && (playerRow == 7 || playerRow == 8)) {
                    try {
                        Stage stage = (Stage) gridPane.getScene().getWindow();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fantasyrollenspiel/shop.fxml"));
                        Scene scene = new Scene(loader.load());
                        stage.setScene(scene);

                        ShopController shopController = loader.getController();
                        totalCoins += coins;
                        totalIron += iron;
                        System.out.println("Passing coins to ShopController: " + totalCoins + " und iron: " + totalIron);
                        shopController.setCoins(totalCoins);
                        shopController.setIron(totalIron);
                        shopController.setFightController(fightController); // Setzen des FightControllers hier
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

    private void movePlayer(int row, int col) {
        if (row >= 0 && row < gridPane.getRowCount() && col >= 0 && col < gridPane.getColumnCount()) {
            System.out.println("Moving player to row: " + row + ", col: " + col);
            gridPane.getChildren().remove(player);
            playerRow = row;
            playerCol = col;
            gridPane.add(player, playerCol, playerRow);
            scrollToPlayer();  // Scroll to player after moving
        }
    }

    private void scrollToPlayer() {
        double cellHeight = gridPane.getHeight() / gridPane.getRowCount();
        double cellWidth = gridPane.getWidth() / gridPane.getColumnCount();
        double scrollTop = (playerRow * cellHeight) - (scrollPane.getHeight() / 2) + (cellHeight / 2);
        double scrollLeft = (playerCol * cellWidth) - (scrollPane.getWidth() / 2) + (cellWidth / 2);
        scrollPane.setVvalue(scrollTop / (gridPane.getHeight() - scrollPane.getHeight()));
        scrollPane.setHvalue(scrollLeft / (gridPane.getWidth() - scrollPane.getWidth()));
    }

    private void updateStats() {
        coinsLabel.setText("Coins: " + coins);
        ironLabel.setText("Iron: " + iron);
        playerLevelLabel.setText("Player Level: " + playerLevel);  // Update player level label
        levelProgressBar.setProgress((double) xp / xpThreshold);  // Aktualisiere die Fortschrittsleiste basierend auf XP und XP-Schwelle
    }

    private void levelUp() {
        if (xp >= xpThreshold) {
            playerLevel++;
            xp -= xpThreshold; // Behalte den Überschuss an XP
            xpThreshold += 50; // Optional: Erhöhe die XP-Schwelle für das nächste Level
            updateStats();
        }
    }

    private void addBattleLogMessage(String message) {
        battleLogTextArea.appendText(message + "\n");
    }

    public void setFightController(FightController fightController) {
        this.fightController = fightController;
    }

    public void addCoins(int amount) {
        coins += amount;
        updateStats();
    }

    public void setCoins(int amount) {
        coins = amount;
        updateStats();
    }

    public void addIron(int amount) {
        iron += amount;
        updateStats();
    }

    public void setIron(int amount) {
        iron = amount;
        updateStats();
    }

    public void addXP(int amount) {
        xp += amount;
        if (xp >= xpThreshold) {
            levelUp();
        }
        updateStats();
    }

    public void setXP(int amount) {
        xp = amount;
        updateStats();
    }
}

