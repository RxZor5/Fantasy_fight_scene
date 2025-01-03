package fantasyrollenspiel.Fight.Potions;

/**
 * Repräsentiert einen Trank im Spiel.
 */
public class Potion {
    private String name;
    private int price;
    private String imagePath;

    /**
     * Konstruktor zum Erstellen eines Tranks.
     *
     * @param name      Der Name des Tranks.
     * @param price     Der Preis des Tranks.
     * @param imagePath Der Pfad zum Bild des Tranks.
     */
    public Potion(String name, int price, String imagePath) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
    }

    /**
     * Gibt den Namen des Tranks zurück.
     *
     * @return Der Name des Tranks.
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt den Preis des Tranks zurück.
     *
     * @return Der Preis des Tranks.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Gibt den Pfad zum Bild des Tranks zurück.
     *
     * @return Der Pfad zum Bild des Tranks.
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Gibt eine textuelle Darstellung des Tranks zurück.
     *
     * @return Eine Zeichenkette, die den Namen und Preis des Tranks enthält.
     */
    @Override
    public String toString() {
        return name + " - " + price + " Gold";
    }
}
