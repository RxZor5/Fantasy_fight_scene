package fantasyrollenspiel.Fight.Armor;

import fantasyrollenspiel.ShopItem;

public class Armor extends ShopItem {
    private String material;
    private int defense;
    private int originalDefense; // Füge ein Feld für den ursprünglichen Verteidigungswert hinzu

    public Armor(String name, int price, String imagePath, String material, int defense) {
        super(name, price, imagePath);
        this.material = material;
        this.defense = defense;
        this.originalDefense = defense; // Speichere den ursprünglichen Verteidigungswert
    }

    public String getMaterial() {
        return material;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getOriginalDefense() {
        return originalDefense; // Methode zum Abrufen des ursprünglichen Verteidigungswerts
    }

    public void resetDefense() {
        this.defense = originalDefense; // Methode zum Zurücksetzen des Verteidigungswerts
    }

    @Override
    public String toString() {
        return getName() + " - " + getPrice() + " Gold (Material: " + material + ", Verteidigung: " + defense + ")";
    }
}
