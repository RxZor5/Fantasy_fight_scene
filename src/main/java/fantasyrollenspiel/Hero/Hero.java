package fantasyrollenspiel.Hero;

public class Hero {
    private String name;
    private int health;
    private int attackPower;

    public Hero(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    public void attack() {
        System.out.println(name + " greift mit " + attackPower + " Angriffskraft an!");
    }

    public void takeDamage(int damage) {
        health -= damage;
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
}
