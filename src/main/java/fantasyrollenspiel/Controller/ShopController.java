package fantasyrollenspiel.Controller;

import fantasyrollenspiel.Fight.Armor.Armor;
import fantasyrollenspiel.Fight.Potions.HealthPotion;
import fantasyrollenspiel.Fight.Potions.StrengthPotion;
import fantasyrollenspiel.Fight.ProgressBarManager;
import fantasyrollenspiel.Fight.Waffen.BoneBow;
import fantasyrollenspiel.Fight.Waffen.BoneSword;
import fantasyrollenspiel.Fight.Waffen.WoodBow;
import fantasyrollenspiel.Fight.Waffen.WoodSword;
import fantasyrollenspiel.Fight.Waffen.IronSword;
import fantasyrollenspiel.Fight.Armor.BoneArmor;
import fantasyrollenspiel.Fight.Armor.WoodArmor;
import fantasyrollenspiel.Fight.Armor.IronArmor;
import fantasyrollenspiel.Hero.Hero;
import fantasyrollenspiel.Inventory;
import fantasyrollenspiel.ShopItem;
import fantasyrollenspiel.StartGame;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShopController {

    @FXML
    private ListView<ShopItem> itemsListView;

    @FXML
    private Button buyButton;

    @FXML
    private Label coinsLabel;

    @FXML
    private Label ironLabel;

    @FXML
    private Label levelLabel;

    private int totalCoins = 0;
    private int totalIron = 0;
    private int currentLevel = 0;
    private FightController fightController;
    private Hero hero;
    private ProgressBarManager progressBarManager;

    public void initialize() {
        System.out.println("ShopController initialisiert");
        buyButton.setDisable(true);
        itemsListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
                buyButton.setDisable(newVal == null));
        itemsListView.setCellFactory(param -> new ListCell<>() {
            private ImageView imageView = new ImageView();

            @Override
            protected void updateItem(ShopItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.toString());
                    try {
                        imageView.setImage(new Image(getClass().getResource(item.getImagePath()).toExternalForm()));
                    } catch (Exception e) {
                        System.err.println("Fehler beim Laden des Bildes: " + item.getImagePath());
                        e.printStackTrace();
                        imageView.setImage(null); // Du kannst hier ein Standardbild setzen
                    }
                    setGraphic(imageView);
                }
            }
        });

        setHero(new Hero("Spieler", 100, 0, totalCoins));



        showPotions();
        updateStats();
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void setFightController(FightController fightController) {
        this.fightController = fightController;
    }

    @FXML
    private void showPotions() {
        itemsListView.getItems().setAll(
                new HealthPotion(),
                new StrengthPotion()
        );
    }

    @FXML
    private void showWeapons() {
        itemsListView.getItems().setAll(
                new BoneBow(),
                new BoneSword(),
                new WoodBow(),
                new WoodSword(),
                new IronSword()
        );
    }

    @FXML
    private void showArmor() {
        itemsListView.getItems().setAll(
                new BoneArmor(),
                new WoodArmor(),
                new IronArmor()
        );
    }

    @FXML
    private void handleBuy() {
        ShopItem selectedItem = itemsListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            int itemPrice = selectedItem.getPrice();
            int itemIronCost = 0;
            if (selectedItem instanceof Armor) {
                itemIronCost = ((Armor) selectedItem).getDefense();
            }

            if (totalCoins >= itemPrice && totalIron >= itemIronCost) {
                Inventory.addItem(selectedItem);
                hero.addCoins(-itemPrice);
                hero.addIron(-itemIronCost);

                if (selectedItem instanceof Armor) {
                    equipBestArmor();
                }
            } else {
                showAlert("Nicht genügend Ressourcen", "Du hast nicht genügend Coins oder Eisen, um diesen Gegenstand zu kaufen.");
            }
        }
    }

    private void equipBestArmor() {
        Armor bestArmor = Inventory.getBestArmor();
        if (bestArmor != null) {
            hero.equipArmor(bestArmor);
            if (fightController != null) {
                fightController.setHeroArmor(bestArmor.getDefense());
                fightController.updateHeroStats();
            }
        } else {
            hero.setArmor(0);
            if (fightController != null) {
                fightController.setHeroArmor(0);
                fightController.updateHeroStats();
            }
        }
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setCoins(int coins) {
        System.out.println("Setting coins: " + coins);
        this.totalCoins = coins;
        updateStats();
    }

    public void setIron(int iron) {
        System.out.println("Setting iron: " + iron);
        this.totalIron = iron;
        updateStats();
    }

    private void updateStats() {
        coinsLabel.setText("Coins: " + totalCoins);
        ironLabel.setText("Iron: " + totalIron);
        levelLabel.setText("Level: " + currentLevel);
        System.out.println("Stats updated: Coins - " + totalCoins + ", Iron - " + totalIron);
    }

    @FXML
    private void handleBackButton() {
        try {
            StartGame.switchScene("/Map/map.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
