package fantasyrollenspiel.Monster;

import fantasyrollenspiel.Hero.Hero;

public class Rogue extends Monster {

    public Rogue(String name, int health, int armor, String weaponImage, int weaponDamage) {
        super(name, health, armor, weaponImage, weaponDamage);
    }



    private int calculateDamage() {
        return getWeaponDamage();
    }
}
