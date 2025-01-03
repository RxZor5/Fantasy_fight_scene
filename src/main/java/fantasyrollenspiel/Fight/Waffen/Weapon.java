package fantasyrollenspiel.Fight.Waffen;

import fantasyrollenspiel.ShopItem;

/**
 * Repräsentiert eine Waffe, die im Spiel verwendet wird.
 */
public class Weapon extends ShopItem {
    private String material;
    private int attackPower;

    /**
     * Konstruktor zum Erstellen einer Waffe.
     *
     * @param name       Der Name der Waffe.
     * @param price      Der Preis der Waffe.
     * @param imagePath  Der Pfad zum Bild der Waffe.
     * @param material   Das Material der Waffe.
     */
    public Weapon(String name, int price, String imagePath, String material) {
        super(name, price, imagePath);
        this.material = material;
    }

    /**
     * Setzt den Preis der Waffe.
     *
     * @param price Der neue Preis der Waffe.
     */
    public void setPrice(int price) {
        super.setPrice(price);
    }

    /**
     * Gibt das Material der Waffe zurück.
     *
     * @return Das Material der Waffe.
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Gibt eine textuelle Darstellung der Waffe zurück.
     *
     * @return Eine Zeichenkette, die den Namen, Preis, Material und Angriffskraft der Waffe enthält.
     */
    @Override
    public String toString() {
        return getName() + " - " + getPrice() + " Gold (Material: " + material + ", Angriffskraft: " + attackPower + ")";
    }
}
