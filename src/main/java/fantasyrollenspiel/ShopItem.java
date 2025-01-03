package fantasyrollenspiel;

public class ShopItem {
    private String name;
    private int price;
    private String imagePath;

    public ShopItem(String name, int price, String imagePath) {
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
