package fantasyrollenspiel.Materialien;

public class Holz {
    private String name;
    private int amount;

    public Holz(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return name + " - " + amount + " Einheiten";
    }
}
