package fantasyrollenspiel.Monster;

import fantasyrollenspiel.Hero.Hero;
import java.util.Random;

public class Monster {
    private String name;
    private int health;
    private int armor;
    private String weaponImage;
    private int weaponDamage;
    private Random random;

    public Monster(String name, int health, int armor, String weaponImage, int weaponDamage) {
        this.name = name;
        this.health = health;
        this.armor = armor;
        this.weaponImage = weaponImage;
        this.weaponDamage = weaponDamage;
        this.random = new Random();
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getArmor() {
        return armor;
    }

    public String getWeaponImage() {
        return weaponImage;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public void takeDamage(int damage) {
        if (armor > 0) {
            armor = Math.max(armor - damage, 0);
        } else {
            health = Math.max(health - damage, 0);
        }
    }

    public void attack(Hero hero) {
        int damage = calculateDamage();
        hero.takeDamage(damage);
        System.out.println(name + " greift an und verursacht " + damage + " Schaden!");
    }

    private int calculateDamage() {
        return weaponDamage;
    }
}
