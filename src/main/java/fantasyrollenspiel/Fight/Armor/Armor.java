package fantasyrollenspiel.Fight.Armor;

import fantasyrollenspiel.ShopItem;

public class Armor extends ShopItem {
    private String material;
    private int defense;

    public Armor(String name, int price, String imagePath, String material, int defense) {
        super(name, price, imagePath);
        this.material = material;
        this.defense = defense;
    }

    public String getMaterial() {
        return material;
    }

    public int getDefense() {
        return defense;
    }

    @Override
    public String toString() {
        return getName() + " - " + getPrice() + " Gold (Material: " + material + ", Verteidigung: " + defense + ")";
    }
}
