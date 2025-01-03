package fantasyrollenspiel.Controller;

import fantasyrollenspiel.Fight.Armor.Armor;
import fantasyrollenspiel.Fight.Potions.HealthPotion;
import fantasyrollenspiel.Fight.Potions.StrengthPotion;
import fantasyrollenspiel.Fight.Waffen.BoneBow;
import fantasyrollenspiel.Fight.Waffen.BoneSword;
import fantasyrollenspiel.Fight.Waffen.WoodBow;
import fantasyrollenspiel.Fight.Waffen.WoodSword;
import fantasyrollenspiel.Fight.Waffen.IronSword;
import fantasyrollenspiel.Fight.Waffen.Weapon;
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

/**
 * Der Controller für den Shop-Bildschirm im Spiel.
 */
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

    private FightController fightController;
    private Hero hero;

    /**
     * Initialisiert den Shop-Controller.
     */
    public void initialize() {
        System.out.println("ShopController initialisiert");

        // Debug-Ausgaben zur Überprüfung der Labels
        System.out.println("coinsLabel: " + (coinsLabel != null));
        System.out.println("ironLabel: " + (ironLabel != null));
        System.out.println("levelLabel: " + (levelLabel != null));

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

        showPotions();
    }



    /**
     * Zeigt die verfügbaren Tränke im Shop an.
     */
    @FXML
    private void showPotions() {
        itemsListView.getItems().setAll(
                new HealthPotion(),
                new StrengthPotion()
        );
    }

    /**
     * Zeigt die verfügbaren Waffen im Shop an.
     */
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

    /**
     * Zeigt die verfügbare Rüstung im Shop an.
     */
    @FXML
    private void showArmor() {
        itemsListView.getItems().setAll(
                new BoneArmor(),
                new WoodArmor(),
                new IronArmor()
        );
    }

    /**
     * Handhabt den Kauf eines ausgewählten Gegenstands.
     */
    @FXML
    private void handleBuy() {
        ShopItem selectedItem = itemsListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            int itemPrice = selectedItem.getPrice();
            int itemIronCost = 0;

            if (selectedItem instanceof Armor) {
                itemIronCost = ((Armor) selectedItem).getDefense();
            }

            if (hero.getCoins() >= itemPrice && hero.getIron() >= itemIronCost) {
                Inventory.addItem(selectedItem);
                hero.addCoins(-itemPrice);
                hero.addIron(-itemIronCost);

                if (selectedItem instanceof Armor) {
                    equipBestArmor();
                } else if (selectedItem instanceof Weapon) {
                    equipWeapon((Weapon) selectedItem);
                }

                updateStats(); // Aktualisieren der Anzeige nach dem Kauf
            } else {
                showAlert("Nicht genügend Ressourcen", "Du hast nicht genügend Coins oder Eisen, um diesen Gegenstand zu kaufen.");
            }
        }
    }

    /**
     * Rüstet den Helden mit einer neuen Waffe aus, falls sie besser ist.
     *
     * @param newWeapon Die neue Waffe.
     */
    private void equipWeapon(Weapon newWeapon) {
        Weapon currentWeapon = hero.getWeapon();
        if (currentWeapon == null || newWeapon.getPrice() > currentWeapon.getPrice()) {
            hero.setWeapon(newWeapon);
            System.out.println("Neue Waffe ausgerüstet: " + newWeapon);
            if (fightController != null) {
                fightController.updateHeroWeaponImage(newWeapon.getImagePath());
                fightController.updateHeroStats();
            }
        } else {
            System.out.println("Die neue Waffe ist schwächer oder gleich stark wie die aktuelle Waffe.");
        }
    }

    /**
     * Rüstet den Helden mit der besten verfügbaren Rüstung aus.
     */
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

    /**
     * Zeigt einen Alert mit der gegebenen Nachricht an.
     *
     * @param title   Der Titel des Alerts.
     * @param message Die Nachricht des Alerts.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Aktualisiert die Anzeige der Ressourcen.
     */
    private void updateStats() {
        System.out.println("Aktualisiere Anzeige mit Coins: " + (hero != null ? hero.getCoins() : 0) + ", Iron: " + (hero != null ? hero.getIron() : 0));
    }

    /**
     * Handhabt die Aktion des Zurück-Buttons.
     */
    @FXML
    private void handleBackButton() {
        try {
            StartGame.switchScene("/Map/map.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Setzt den Helden für den Shop-Controller.
     *
     * @param hero Der Held.
     */
    public void setHero(Hero hero) {
        this.hero = hero;
        updateStats(); // Aktualisieren der Anzeige nach dem Setzen des Helden
    }

    /**
     * Setzt den Fight-Controller für den Shop-Controller.
     *
     * @param fightController Der Fight-Controller.
     */
    public void setFightController(FightController fightController) {
        this.fightController = fightController;
    }

    /**
     * Setzt die Anzahl der Münzen und aktualisiert die Anzeige.
     *
     * @param amount Die Anzahl der Münzen.
     */
    public void setCoins(int amount) {
        hero.setCoins(amount); // Setzen des Münzwerts im Heldenobjekt
        coinsLabel.setText("Coins: " + amount);
    }

    /**
     * Setzt die Menge an Eisen und aktualisiert die Anzeige.
     *
     * @param amount Die Menge an Eisen.
     */
    public void setIron(int amount) {
        hero.setIron(amount); // Setzen des Eisenwerts im Heldenobjekt
        ironLabel.setText("Iron: " + amount);
    }
}
