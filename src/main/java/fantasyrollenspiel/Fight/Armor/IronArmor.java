package fantasyrollenspiel.Fight.Armor;

/**
 * Repräsentiert eine Eisenrüstung als Rüstung im Spiel.
 */
public class IronArmor extends Armor {

    /**
     * Konstruktor zum Erstellen einer Eisenrüstung.
     * Setzt den Namen, den Preis, das Bild, das Material und den Verteidigungswert der Rüstung.
     */
    public IronArmor() {
        super("Eisenrüstung", 350, "/Bilder/Armor/iron_armor.png", "Eisen", 50);
    }
}
