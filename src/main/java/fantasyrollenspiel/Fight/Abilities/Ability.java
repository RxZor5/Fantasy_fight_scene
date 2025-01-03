package fantasyrollenspiel.Fight.Abilities;

import fantasyrollenspiel.ShopItem;

public class Ability extends ShopItem {
    private String description;

    public Ability(String name, int price, String imagePath, String description) {
        super(name, price, imagePath);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getName() + " - " + getPrice() + " Gold (Beschreibung: " + description + ")";
    }
}
