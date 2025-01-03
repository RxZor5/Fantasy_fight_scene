package fantasyrollenspiel.Fight.Waffen;

/**
 * Repräsentiert ein Eisenschwert als Waffe.
 */
public class IronSword extends Weapon {

    /**
     * Konstruktor zum Erstellen eines Eisenschwerts.
     * Setzt den Namen, den Preis, das Bild und das Material der Waffe.
     */
    public IronSword() {
        super("Eisen Schwert", 300, "/Bilder/Waffen/ironsword.png", "Eisen");
    }
}
