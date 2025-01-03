package fantasyrollenspiel.Controller;

import fantasyrollenspiel.Inventory;
import fantasyrollenspiel.ShopItem;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Der Controller für das Inventarfenster im Spiel.
 */
public class InventoryController {

    @FXML
    private ListView<ShopItem> inventoryListView;

    @FXML
    private Button equipButton;

    private ImageView heroWeaponImageView;
    private FightController fightController;

    /**
     * Initialisiert den InventoryController.
     */
    @FXML
    public void initialize() {
        loadInventory();
        inventoryListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            equipButton.setDisable(newValue == null);
        });
    }

    /**
     * Lädt das Inventar und zeigt die Items im ListView an.
     */
    private void loadInventory() {
        ObservableList<ShopItem> items = Inventory.getItems();
        inventoryListView.getItems().setAll(items);
    }

    /**
     * Handhabt das Ausrüsten eines ausgewählten Items.
     */
    @FXML
    private void handleEquipButton() {
        ShopItem selectedItem = inventoryListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            equipItem(selectedItem);
            System.out.println("Item ausgerüstet: " + selectedItem.getName());
        }
    }

    /**
     * Rüstet das ausgewählte Item aus und aktualisiert das Bild im Helden- und Kampf-Controller.
     *
     * @param item Das auszurüstende Item.
     */
    private void equipItem(ShopItem item) {
        if (heroWeaponImageView != null) {
            Image itemImage = new Image(getClass().getResourceAsStream(item.getImagePath()));
            heroWeaponImageView.setImage(itemImage);
            System.out.println("Bild des Items gesetzt: " + item.getImagePath());
        } else {
            System.out.println("heroWeaponImageView ist null");
        }

        if (fightController != null) {
            fightController.updateHeroWeaponImage(item.getImagePath());
            System.out.println("Bild des Items im FightController aktualisiert: " + item.getImagePath());
        } else {
            System.out.println("fightController ist null");
        }
    }

    /**
     * Handhabt das Schließen des Inventarfensters.
     */
    @FXML
    private void handleCloseButton() {
        Stage stage = (Stage) inventoryListView.getScene().getWindow();
        stage.close();
    }

    /**
     * Setzt das ImageView für die Waffe des Helden.
     *
     * @param heroWeaponImageView Das ImageView der Heldenwaffe.
     */
    public void setHeroWeaponImageView(ImageView heroWeaponImageView) {
        this.heroWeaponImageView = heroWeaponImageView;
    }

    /**
     * Setzt den FightController für den InventoryController.
     *
     * @param fightController Der FightController.
     */
    public void setFightController(FightController fightController) {
        this.fightController = fightController;
    }
}
