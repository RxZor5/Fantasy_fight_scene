package fantasyrollenspiel.Hero;

import fantasyrollenspiel.Fight.Armor.Armor;
import fantasyrollenspiel.Fight.Waffen.Weapon;

/**
 * Repräsentiert den Helden im Spiel.
 */
public class Hero {
    private String name;
    private int health;
    private int armor;
    private int coins;
    private int iron;
    private int xp;
    private int level;
    private Armor equippedArmor;
    private Weapon equippedWeapon;

    /**
     * Konstruktor zum Erstellen eines Helden.
     *
     * @param name   Der Name des Helden.
     * @param health Die anfängliche Gesundheit des Helden.
     * @param armor  Die anfängliche Rüstung des Helden.
     * @param coins  Die anfänglichen Münzen des Helden.
     */
    public Hero(String name, int health, int armor, int coins) {
        this.name = name;
        this.health = health;
        this.armor = armor;
        this.coins = coins;
        this.iron = 0;
        this.xp = 0;
        this.level = 1;
        this.equippedArmor = null; // Keine Rüstung zu Beginn
        this.equippedWeapon = null; // Keine Waffe zu Beginn
    }

    /**
     * Gibt den Namen des Helden zurück.
     *
     * @return Der Name des Helden.
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen des Helden.
     *
     * @param name Der neue Name des Helden.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gibt die Gesundheit des Helden zurück.
     *
     * @return Die Gesundheit des Helden.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Setzt die Gesundheit des Helden.
     *
     * @param health Die neue Gesundheit des Helden.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Gibt die Rüstung des Helden zurück.
     *
     * @return Die Rüstung des Helden.
     */
    public int getArmor() {
        return (equippedArmor != null) ? equippedArmor.getDefense() : armor;
    }

    /**
     * Setzt die Rüstung des Helden.
     *
     * @param armor Die neue Rüstung des Helden.
     */
    public void setArmor(int armor) {
        this.armor = armor;
        if (this.equippedArmor != null) {
            this.equippedArmor.setDefense(armor); // Aktualisiere die Rüstungsverteidigung
        }
    }

    /**
     * Gibt die Anzahl der Münzen des Helden zurück.
     *
     * @return Die Anzahl der Münzen des Helden.
     */
    public int getCoins() {
        return coins;
    }

    /**
     * Fügt die angegebene Anzahl Münzen zum Helden hinzu.
     *
     * @param amount Die hinzuzufügende Anzahl an Münzen.
     */
    public void addCoins(int amount) {
        this.coins += amount;
    }

    /**
     * Setzt die Anzahl der Münzen des Helden.
     *
     * @param coins Die neue Anzahl der Münzen.
     */
    public void setCoins(int coins) {
        this.coins = coins;
    }

    /**
     * Gibt die Menge an Eisen des Helden zurück.
     *
     * @return Die Menge an Eisen des Helden.
     */
    public int getIron() {
        return iron;
    }

    /**
     * Setzt die Menge an Eisen des Helden.
     *
     * @param iron Die neue Menge an Eisen.
     */
    public void setIron(int iron) {
        this.iron = iron;
    }

    /**
     * Fügt die angegebene Menge Eisen zum Helden hinzu.
     *
     * @param amount Die hinzuzufügende Menge an Eisen.
     */
    public void addIron(int amount) {
        this.iron += amount;
    }

    /**
     * Gibt die Erfahrungspunkte des Helden zurück.
     *
     * @return Die Erfahrungspunkte des Helden.
     */
    public int getXp() {
        return xp;
    }

    /**
     * Setzt die Erfahrungspunkte des Helden.
     *
     * @param xp Die neuen Erfahrungspunkte.
     */
    public void setXp(int xp) {
        this.xp = xp;
    }

    /**
     * Gibt das Level des Helden zurück.
     *
     * @return Das Level des Helden.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Setzt das Level des Helden.
     *
     * @param level Das neue Level.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Rüstet den Helden mit einer Rüstung aus.
     *
     * @param armor Die auszurüstende Rüstung.
     */
    public void equipArmor(Armor armor) {
        this.equippedArmor = armor;
        this.armor = armor.getDefense();
    }

    /**
     * Setzt die ausgerüstete Rüstung des Helden.
     *
     * @param armor Die neue ausgerüstete Rüstung.
     */
    public void setEquippedArmor(Armor armor) {
        this.equippedArmor = armor;
        if (armor != null) {
            this.armor = armor.getDefense();
        } else {
            this.armor = 0; // Setze die Rüstung auf 0, wenn keine Rüstung ausgerüstet ist
        }
    }

    /**
     * Gibt die ausgerüstete Rüstung des Helden zurück.
     *
     * @return Die ausgerüstete Rüstung des Helden.
     */
    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    /**
     * Rüstet den Helden mit einer Waffe aus.
     *
     * @param weapon Die auszurüstende Waffe.
     */
    public void equipWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
    }

    /**
     * Gibt die ausgerüstete Waffe des Helden zurück.
     *
     * @return Die ausgerüstete Waffe des Helden.
     */
    public Weapon getWeapon() {
        return equippedWeapon;
    }

    /**
     * Setzt die ausgerüstete Waffe des Helden.
     *
     * @param weapon Die neue ausgerüstete Waffe.
     */
    public void setWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
    }
}
