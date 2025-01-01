package fantasyrollenspiel.Monster;

import fantasyrollenspiel.Hero.Hero;

public class Rogue extends Monster {

    public Rogue(String name, int health, int armor, String weaponImage, int weaponDamage) {
        super(name, health, armor, weaponImage, weaponDamage);
    }

    @Override
    public void attack(Hero hero) {
        int damage = calculateDamage();
        hero.takeDamage(damage);
        System.out.println(getName() + " greift an und verursacht " + damage + " Schaden!");
    }

    private int calculateDamage() {
        return getWeaponDamage();
    }
}
