package fantasyrollenspiel.Monster;

public class Skelett extends Monster {
    private String weapon;

    public Skelett(String name, int health, String weapon) {
        super(name, health);
        this.weapon = weapon;
    }

    @Override
    public void attack() {
        System.out.println(getName() + " greift mit " + weapon + " an!");
    }

    // Getter und Setter
    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }
}
