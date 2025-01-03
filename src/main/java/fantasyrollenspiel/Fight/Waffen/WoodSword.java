package fantasyrollenspiel.Fight.Waffen;

/**
 * Repräsentiert ein Holzschwert als Waffe.
 */
public class WoodSword extends Weapon {

    /**
     * Konstruktor zum Erstellen eines Holzschwerts.
     * Setzt den Namen, den Preis, das Bild und das Material der Waffe.
     */
    public WoodSword() {
        super("Holz Schwert", 200, "/Bilder/Waffen/woodsword.png", "Holz");
    }
}
