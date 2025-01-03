package fantasyrollenspiel.Fight.Potions;

public class Potion {
    private String name;
    private int price;
    private String imagePath;

    public Potion(String name, int price, String imagePath) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public String toString() {
        return name + " - " + price + " Gold";
    }
}
