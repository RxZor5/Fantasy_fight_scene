package fantasyrollenspiel.Hero;

/**
 * Repräsentiert einen Ork-Helden im Spiel.
 */
public class orc extends Hero {
    private String tribe;

    /**
     * Konstruktor zum Erstellen eines Ork-Helden.
     *
     * @param name        Der Name des Orks.
     * @param health      Die Gesundheit des Orks.
     * @param attackPower Die Angriffskraft des Orks.
     * @param armor       Die Rüstung des Orks.
     * @param tribe       Der Stamm des Orks.
     */
    public orc(String name, int health, int attackPower, int armor, String tribe) {
        super(name, health, attackPower, armor);
        this.tribe = tribe;
    }

    /**
     * Gibt den Stamm des Orks zurück.
     *
     * @return Der Stamm des Orks.
     */
    public String getTribe() {
        return tribe;
    }

    /**
     * Setzt den Stamm des Orks.
     *
     * @param tribe Der neue Stamm des Orks.
     */
    public void setTribe(String tribe) {
        this.tribe = tribe;
    }
}
