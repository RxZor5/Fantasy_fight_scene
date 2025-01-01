package fantasyrollenspiel.Hero;

public class Hero {
    private String name;
    private int health;
    private int attackPower;
    private int armor;

    public Hero(String name, int health, int attackPower, int armor) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.armor = armor;
    }

    public void attack() {
        System.out.println(name + " greift mit " + attackPower + " Angriffskraft an!");
    }

    public void takeDamage(int damage) {
        if (armor > 0) {
            armor = Math.max(armor - damage, 0);
        } else {
            health = Math.max(health - damage, 0);
        }
        System.out.println(name + " erleidet " + damage + " Schaden. Verbleibende Gesundheit: " + health);
    }

    // Getter und Setter
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

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
}
