package fantasyrollenspiel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
    private ProgressBar levelProgressBar;

    private ImageView player;
    private int playerRow = 0;
    private int playerCol = 0;

    private Animations animations;

    private int coins = 0;
    private int iron = 0;
    private double levelProgress = 0.0;

    public void initialize() {
        animations = new Animations();
        drawTileMap("src/main/resources/Map/tilemap.txt");
        addPlayer();
        setupKeyEvents();

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
                        StartGame.switchScene("/fantasyrollenspiel/fight.fxml", coins, iron);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else if (playerCol == 20 && (playerRow == 7 || playerRow == 8)) {
                    try {
                        Stage stage = (Stage) gridPane.getScene().getWindow();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fantasyrollenspiel/Shop.fxml"));
                        Scene scene = new Scene(loader.load());
                        stage.setScene(scene);
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
        }
    }

    private void updateStats() {
        coinsLabel.setText("Coins: " + coins);
        ironLabel.setText("Iron: " + iron);
        levelProgressBar.setProgress(levelProgress);
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
}
