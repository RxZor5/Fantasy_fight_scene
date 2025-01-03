package fantasyrollenspiel.Fight.Waffen;

/**
 * Repräsentiert einen Holzbogen als Waffe.
 */
public class WoodBow extends Weapon {

    /**
     * Konstruktor zum Erstellen eines Holzbogens.
     * Setzt den Namen, den Preis, das Bild und das Material der Waffe.
     */
    public WoodBow() {
        super("Holz Bogen", 150, "/Bilder/Waffen/woodbow.png", "Holz");
    }
}
