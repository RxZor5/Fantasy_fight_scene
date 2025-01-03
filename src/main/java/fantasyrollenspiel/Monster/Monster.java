package fantasyrollenspiel.Monster;

import fantasyrollenspiel.Hero.Hero;
import java.util.Random;

/**
 * Repräsentiert ein Monster im Spiel.
 */
public class Monster {
    private String name;
    private int health;
    private int armor;
    private String weaponImage;
    private int weaponDamage;
    private Random random;

    /**
     * Konstruktor zum Erstellen eines Monsters.
     *
     * @param name         Der Name des Monsters.
     * @param health       Die Gesundheit des Monsters.
     * @param armor        Die Rüstung des Monsters.
     * @param weaponImage  Das Bild der Waffe des Monsters.
     * @param weaponDamage Der Schaden der Waffe des Monsters.
     */
    public Monster(String name, int health, int armor, String weaponImage, int weaponDamage) {
        this.name = name;
        this.health = health;
        this.armor = armor;
        this.weaponImage = weaponImage;
        this.weaponDamage = weaponDamage;
        this.random = new Random();
    }

    /**
     * Gibt den Namen des Monsters zurück.
     *
     * @return Der Name des Monsters.
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt die Gesundheit des Monsters zurück.
     *
     * @return Die Gesundheit des Monsters.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Gibt die Rüstung des Monsters zurück.
     *
     * @return Die Rüstung des Monsters.
     */
    public int getArmor() {
        return armor;
    }

    /**
     * Gibt das Waffenbild des Monsters zurück.
     *
     * @return Das Waffenbild des Monsters.
     */
    public String getWeaponImage() {
        return weaponImage;
    }

    /**
     * Gibt den Waffenschaden des Monsters zurück.
     *
     * @return Der Waffenschaden des Monsters.
     */
    public int getWeaponDamage() {
        return weaponDamage;
    }

    /**
     * Verursacht Schaden am Monster und reduziert dessen Gesundheit oder Rüstung.
     *
     * @param damage Der zugefügte Schaden.
     */
    public void takeDamage(int damage) {
        if (armor > 0) {
            armor = Math.max(armor - damage, 0);
        } else {
            health = Math.max(health - damage, 0);
        }
    }

    /**
     * Berechnet den Schaden, den das Monster verursachen kann.
     *
     * @return Der Schaden, den das Monster verursachen kann.
     */
    private int calculateDamage() {
        return weaponDamage;
    }
}
