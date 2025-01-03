package fantasyrollenspiel.Fight.Armor;

/**
 * Repräsentiert eine Holzrüstung als Rüstung im Spiel.
 */
public class WoodArmor extends Armor {

    /**
     * Konstruktor zum Erstellen einer Holzrüstung.
     * Setzt den Namen, den Preis, das Bild, das Material und den Verteidigungswert der Rüstung.
     */
    public WoodArmor() {
        super("Holzrüstung", 250, "/Bilder/Armor/wood_armor.png", "Holz", 30);
    }
}
