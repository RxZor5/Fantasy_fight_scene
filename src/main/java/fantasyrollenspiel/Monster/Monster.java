package fantasyrollenspiel.Monster;

public class Monster{
    // Anfang Attribute
    private String name;
    private int health;

    public Monster(String name, int health) {
        this.name = name;
        this.health = health;

    }

    public void attack() {
        System.out.println(name + " greift an!");
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
}

// Ende Methoden


