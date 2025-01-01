package fantasyrollenspiel.Armor;

public class Armor {
    protected String material;
    protected int defense;

    public Armor(String material, int defense) {
        this.material = material;
        this.defense = defense;
    }

    public String getMaterial() {
        return material;
    }

    public int getDefense() {
        return defense;
    }
}
