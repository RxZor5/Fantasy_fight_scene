package fantasyrollenspiel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class ShopController {

    @FXML
    private ListView<String> itemsListView;

    @FXML
    private Button buyButton;

    public void initialize() {
        // Initialize the list of items available in the shop
        itemsListView.getItems().addAll(
                "Sword - 100 gold",
                "Shield - 150 gold",
                "Potion - 50 gold",
                "Armor - 200 gold"
        );

        // Ensure the buy button is disabled when no item is selected
        buyButton.setDisable(true);

        // Enable the buy button when an item is selected
        itemsListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            buyButton.setDisable(newVal == null);
        });
    }

    @FXML
    private void handleBuy() {
        String selectedItem = itemsListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            System.out.println("Bought: " + selectedItem);
            // Perform the purchase action (e.g., deducting gold, adding item to inventory)
        }
    }
}
