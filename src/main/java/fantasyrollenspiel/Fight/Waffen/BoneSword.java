package fantasyrollenspiel.Fight.Waffen;

/**
 * Repräsentiert ein Knochenschwert als Waffe.
 */
public class BoneSword extends Weapon {

    /**
     * Konstruktor zum Erstellen eines Knochenschwerts.
     * Setzt den Namen, den Preis, das Bild und das Material der Waffe.
     */
    public BoneSword() {
        super("Knochen Schwert", 120, "/Bilder/Waffen/bonesword.png", "Knochen");
    }
}
