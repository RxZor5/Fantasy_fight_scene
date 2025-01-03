package fantasyrollenspiel.Fight.Waffen;

import fantasyrollenspiel.ShopItem;

public class Weapon extends ShopItem {
    private String material;
    private int attackPower;

    public Weapon(String name, int price, String imagePath, String material, int attackPower) {
        super(name, price, imagePath);
        this.material = material;
        this.attackPower = attackPower;
    }

    public String getMaterial() {
        return material;
    }

    public int getAttackPower() {
        return attackPower;
    }

    @Override
    public String toString() {
        return getName() + " - " + getPrice() + " Gold (Material: " + material + ", Angriffskraft: " + attackPower + ")";
    }
}
