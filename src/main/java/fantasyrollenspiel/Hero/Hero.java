package fantasyrollenspiel.Hero;

import fantasyrollenspiel.Fight.Armor.Armor;

public class Hero {
    private String name;
    private int health;
    private int armor;
    private int coins;
    private int iron;
    private Armor equippedArmor;

    public Hero(String name, int health, int armor, int coins) {
        this.name = name;
        this.health = health;
        this.armor = armor;
        this.coins = coins;
        this.iron = 0;
        this.equippedArmor = null; // Keine Rüstung zu Beginn
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getArmor() {
        return (equippedArmor != null) ? equippedArmor.getDefense() : armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int amount) {
        this.coins += amount;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setIron(int iron) {
        this.iron = iron;
    }

    public int getIron() {
        return iron;
    }

    public void addIron(int amount) {
        this.iron += amount;
    }

    public void equipArmor(Armor armor) {
        this.equippedArmor = armor;
        this.armor = armor.getDefense();
    }

    public Armor getEquippedArmor() {
        return equippedArmor;
    }


}
