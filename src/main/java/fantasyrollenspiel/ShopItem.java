package fantasyrollenspiel;

/**
 * Repräsentiert einen Gegenstand im Shop.
 */
public class ShopItem {
    private String name;
    private int price;
    private String imagePath;

    /**
     * Konstruktor zum Erstellen eines Shop-Gegenstands.
     *
     * @param name      Der Name des Gegenstands.
     * @param price     Der Preis des Gegenstands.
     * @param imagePath Der Pfad zum Bild des Gegenstands.
     */
    public ShopItem(String name, int price, String imagePath) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
    }

    /**
     * Gibt den Namen des Gegenstands zurück.
     *
     * @return Der Name des Gegenstands.
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt den Preis des Gegenstands zurück.
     *
     * @return Der Preis des Gegenstands.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Gibt den Pfad zum Bild des Gegenstands zurück.
     *
     * @return Der Pfad zum Bild des Gegenstands.
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Setzt den Preis des Gegenstands.
     *
     * @param price Der neue Preis des Gegenstands.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Gibt eine textuelle Darstellung des Gegenstands zurück.
     *
     * @return Eine Zeichenkette, die den Namen und den Preis des Gegenstands enthält.
     */
    @Override
    public String toString() {
        return name + " - " + price + " Gold";
    }
}
