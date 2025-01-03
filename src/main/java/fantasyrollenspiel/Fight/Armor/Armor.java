package fantasyrollenspiel.Fight.Armor;

import fantasyrollenspiel.ShopItem;

/**
 * Repräsentiert eine Rüstung, die im Spiel verwendet wird.
 */
public class Armor extends ShopItem {
    private String material;
    private int defense;
    private int originalDefense; // Füge ein Feld für den ursprünglichen Verteidigungswert hinzu

    /**
     * Konstruktor zum Erstellen einer Rüstung.
     *
     * @param name        Der Name der Rüstung.
     * @param price       Der Preis der Rüstung.
     * @param imagePath   Der Pfad zum Bild der Rüstung.
     * @param material    Das Material der Rüstung.
     * @param defense     Der Verteidigungswert der Rüstung.
     */
    public Armor(String name, int price, String imagePath, String material, int defense) {
        super(name, price, imagePath);
        this.material = material;
        this.defense = defense;
        this.originalDefense = defense; // Speichere den ursprünglichen Verteidigungswert
    }

    /**
     * Gibt das Material der Rüstung zurück.
     *
     * @return Das Material der Rüstung.
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Gibt den Verteidigungswert der Rüstung zurück.
     *
     * @return Der Verteidigungswert der Rüstung.
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Setzt den Verteidigungswert der Rüstung.
     *
     * @param defense Der neue Verteidigungswert.
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * Gibt den ursprünglichen Verteidigungswert der Rüstung zurück.
     *
     * @return Der ursprüngliche Verteidigungswert.
     */
    public int getOriginalDefense() {
        return originalDefense; // Methode zum Abrufen des ursprünglichen Verteidigungswerts
    }

    /**
     * Setzt den Verteidigungswert der Rüstung auf den ursprünglichen Wert zurück.
     */
    public void resetDefense() {
        this.defense = originalDefense; // Methode zum Zurücksetzen des Verteidigungswerts
    }

    /**
     * Gibt eine textuelle Darstellung der Rüstung zurück.
     *
     * @return Eine Zeichenkette, die den Namen, Preis, Material und Verteidigungswert der Rüstung enthält.
     */
    @Override
    public String toString() {
        return getName() + " - " + getPrice() + " Gold (Material: " + material + ", Verteidigung: " + defense + ")";
    }
}
