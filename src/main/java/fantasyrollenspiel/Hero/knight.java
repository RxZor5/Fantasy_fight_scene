package fantasyrollenspiel.Hero;

public class knight extends Hero {
    private String weapon;

    public knight(String name, int health, int attackPower, String weapon) {
        super(name, health, attackPower);
        this.weapon = weapon;
    }

    @Override
    public void attack() {
        System.out.println(getName() + " greift mit seinem " + weapon + " an!");
    }

    // Getter und Setter
    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }
}
