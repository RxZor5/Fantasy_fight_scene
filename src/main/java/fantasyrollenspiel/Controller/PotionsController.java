package fantasyrollenspiel.Controller;

import fantasyrollenspiel.Inventory;
import fantasyrollenspiel.Fight.Potions.Potion;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class PotionsController {

    @FXML
    private ListView<Potion> potionsListView;

    @FXML
    public void initialize() {
        loadPotions();
    }

    private void loadPotions() {
        ObservableList<Potion> potions = Inventory.getPotions();
        potionsListView.getItems().setAll(potions);
    }

    @FXML
    private void handleCloseButton() {
        // Schließe das Fenster
        Stage stage = (Stage) potionsListView.getScene().getWindow();
        stage.close();
    }
}
