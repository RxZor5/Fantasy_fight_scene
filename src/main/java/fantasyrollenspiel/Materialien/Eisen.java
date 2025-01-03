package fantasyrollenspiel.Materialien;

public class Eisen {
    private String name;
    private int amount;

    public Eisen(String name, int amount) {
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
