package fantasyrollenspiel.Fight.Waffen;

public class Weapon {
    private String material;
    private int attackPower;

    public Weapon(String material, int attackPower) {
        this.material = material;
        this.attackPower = attackPower;
    }

    public String getMaterial() {
        return material;
    }

    public int getAttackPower() {
        return attackPower;
    }
}
