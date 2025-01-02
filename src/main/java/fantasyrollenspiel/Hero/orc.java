package fantasyrollenspiel.Hero;

public class orc extends Hero {
    private String tribe;

    public orc(String name, int health, int attackPower, int armor, String tribe) {
        super(name, health, attackPower, armor);
        this.tribe = tribe;
    }


    public void attack() {
        System.out.println(getName() + " aus dem Stamm " + tribe + " greift mit roher Gewalt an!");
    }

    // Getter und Setter
    public String getTribe() {
        return tribe;
    }

    public void setTribe(String tribe) {
        this.tribe = tribe;
    }
}
