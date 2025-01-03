package fantasyrollenspiel.Fight.Armor;

/**
 * Repräsentiert eine Knochenrüstung als Rüstung im Spiel.
 */
public class BoneArmor extends Armor {

    /**
     * Konstruktor zum Erstellen einer Knochenrüstung.
     * Setzt den Namen, den Preis, das Bild, das Material und den Verteidigungswert der Rüstung.
     */
    public BoneArmor() {
        super("Knochenrüstung", 150, "/Bilder/Armor/bone_armor.png", "Knochen", 20);
    }
}
