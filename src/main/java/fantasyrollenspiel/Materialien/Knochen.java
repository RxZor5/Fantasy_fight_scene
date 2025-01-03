package fantasyrollenspiel.Materialien;

import fantasyrollenspiel.Fight.Armor.Armor;

/**
 * Repräsentiert das Material Knochen, das im Spiel als Rüstung verwendet wird.
 */
public class Knochen extends Armor {

    /**
     * Konstruktor zum Erstellen einer Knochenrüstung.
     *
     * @param name      Der Name der Rüstung.
     * @param price     Der Preis der Rüstung.
     * @param imagePath Der Pfad zum Bild der Rüstung.
     * @param material  Das Material der Rüstung.
     * @param defense   Der Verteidigungswert der Rüstung.
     */
    public Knochen(String name, int price, String imagePath, String material, int defense) {
        super(name, price, imagePath, material, defense);
    }
}
