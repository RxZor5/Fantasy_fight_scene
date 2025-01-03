package fantasyrollenspiel.Materialien;

/**
 * Repräsentiert das Material Holz im Spiel.
 */
public class Holz {
    private String name;
    private int amount;

    /**
     * Konstruktor zum Erstellen einer Instanz von Holz.
     *
     * @param name   Der Name des Materials.
     * @param amount Die Menge des Materials.
     */
    public Holz(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    /**
     * Gibt den Namen des Materials zurück.
     *
     * @return Der Name des Materials.
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt die Menge des Materials zurück.
     *
     * @return Die Menge des Materials.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Gibt eine textuelle Darstellung des Materials zurück.
     *
     * @return Eine Zeichenkette, die den Namen und die Menge des Materials enthält.
     */
    @Override
    public String toString() {
        return name + " - " + amount + " Einheiten";
    }
}
