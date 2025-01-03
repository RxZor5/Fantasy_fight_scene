package fantasyrollenspiel;

import fantasyrollenspiel.Fight.Armor.Armor;
import fantasyrollenspiel.Fight.Waffen.Weapon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import fantasyrollenspiel.Fight.Potions.Potion;

import java.util.Comparator;

    /**
     * Der Inventar-Manager für das Spiel.
     */
    public class Inventory {
        private static final ObservableList<ShopItem> items = FXCollections.observableArrayList();
        private static final ObservableList<Potion> potions = FXCollections.observableArrayList();

        /**
         * Gibt die Liste der Gegenstände im Inventar zurück.
         *
         * @return Die Liste der Gegenstände im Inventar.
         */
        public static ObservableList<ShopItem> getItems() {
            return items;
        }

        /**
         * Gibt die Liste der Tränke im Inventar zurück.
         *
         * @return Die Liste der Tränke im Inventar.
         */
        public static ObservableList<Potion> getPotions() {
            return potions;
        }

        /**
         * Fügt einen Gegenstand zum Inventar hinzu.
         *
         * @param item Der hinzuzufügende Gegenstand.
         */
        public static void addItem(ShopItem item) {
            items.add(item);
        }

        /**
         * Fügt einen Trank zum Inventar hinzu.
         *
         * @param potion Der hinzuzufügende Trank.
         */
        public static void addPotion(Potion potion) {
            potions.add(potion);
        }

        /**
         * Gibt die beste verfügbare Rüstung im Inventar zurück.
         *
         * @return Die beste verfügbare Rüstung, oder null, wenn keine vorhanden ist.
         */
        public static Armor getBestArmor() {
            return items.stream()
                    .filter(item -> item instanceof Armor)
                    .map(item -> (Armor) item)
                    .max(Comparator.comparingInt(Armor::getDefense))
                    .orElse(null);
        }

        /**
         * Gibt die beste verfügbare Waffe im Inventar zurück.
         *
         * @return Die beste verfügbare Waffe, oder null, wenn keine vorhanden ist.
         */
        public static Weapon getBestWeapon() {
            return items.stream()
                    .filter(item -> item instanceof Weapon)
                    .map(item -> (Weapon) item)
                    .max(Comparator.comparingInt(Weapon::getPrice))
                    .orElse(null);
        }
    }


