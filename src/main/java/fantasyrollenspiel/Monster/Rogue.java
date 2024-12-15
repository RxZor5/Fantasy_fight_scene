package fantasyrollenspiel.Monster;

public class Rogue extends Monster {
    private int stealthLevel;

    public Rogue(String name, int health, int stealthLevel) {
        super(name, health);
        this.stealthLevel = stealthLevel;
    }

    @Override
    public void attack() {
        System.out.println(getName() + " greift aus dem Schatten an mit Stealth Level " + stealthLevel + "!");
    }

    // Getter und Setter
    public int getStealthLevel() {
        return stealthLevel;
    }

    public void setStealthLevel(int stealthLevel) {
        this.stealthLevel = stealthLevel;
    }
}
