package fantasyrollenspiel;

public class DamageLog {
    // Anfang Attribute
    private int dmgToMonster;
    private int dmgToHero;
    // Ende Attribute

    // Konstruktor
    public DamageLog(){

    }
    public DamageLog(int adMonster, int adHero){
        this.dmgToHero = adMonster;
        this.dmgToMonster = adHero;
    }


    // Anfang Methoden
    public int getDmgToMonster() {
        return dmgToMonster;
    }

    public void setDmgToMonster(int dmgToMonster) {
        this.dmgToMonster = dmgToMonster;
    }

    public int getDmgToHero() {
        return dmgToHero;
    }

    public void setDmgToHero(int dmgToHero) {
        this.dmgToHero = dmgToHero;
    }

    // Ende Methoden
}
