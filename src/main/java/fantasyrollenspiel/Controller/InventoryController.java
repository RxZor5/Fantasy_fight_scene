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

public class InventoryController {

    @FXML
    private ListView<ShopItem> inventoryListView;

    @FXML
    private Button equipButton;

    private ImageView heroWeaponImageView;
    private FightController fightController;

    @FXML
    public void initialize() {
        loadInventory();
        inventoryListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            equipButton.setDisable(newValue == null);
        });
    }

    private void loadInventory() {
        ObservableList<ShopItem> items = Inventory.getItems();
        inventoryListView.getItems().setAll(items);
    }

    @FXML
    private void handleEquipButton() {
        ShopItem selectedItem = inventoryListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            equipItem(selectedItem);
            System.out.println("Item ausgerüstet: " + selectedItem.getName());
        }
    }

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

    @FXML
    private void handleCloseButton() {
        Stage stage = (Stage) inventoryListView.getScene().getWindow();
        stage.close();
    }

    public void setHeroWeaponImageView(ImageView heroWeaponImageView) {
        this.heroWeaponImageView = heroWeaponImageView;
    }

    public void setFightController(FightController fightController) {
        this.fightController = fightController;
    }
}
