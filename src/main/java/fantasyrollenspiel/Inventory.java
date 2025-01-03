package fantasyrollenspiel;

import fantasyrollenspiel.Fight.Armor.Armor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import fantasyrollenspiel.Fight.Potions.Potion;

import java.util.Comparator;

public class Inventory {
    private static final ObservableList<ShopItem> items = FXCollections.observableArrayList();
    private static final ObservableList<Potion> potions = FXCollections.observableArrayList();

    public static ObservableList<ShopItem> getItems() {
        return items;
    }

    public static ObservableList<Potion> getPotions() {
        return potions;
    }

    public static void addItem(ShopItem item) {
        items.add(item);
    }

    public static void addPotion(Potion potion) {
        potions.add(potion);
    }

    public static Armor getBestArmor() {
        return items.stream()
                .filter(item -> item instanceof Armor)
                .map(item -> (Armor) item)
                .max(Comparator.comparingInt(Armor::getDefense))
                .orElse(null);
    }
}
