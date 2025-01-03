package fantasyrollenspiel.Fight.Rounds;

import fantasyrollenspiel.Monster.Monster;
import fantasyrollenspiel.Hero.Hero;
import fantasyrollenspiel.Fight.ProgressBarManager;
import java.io.*;
import java.util.Random;

/**
 * Der Level-Manager verwaltet den Fortschritt und die Level im Spiel.
 */
public class LevelManager {
    private int currentLevel;
    private Hero hero;
    private ProgressBarManager progressBarManager;
    private Monster currentMonster;
    private Random random;

    /**
     * Konstruktor zum Erstellen eines Level-Managers.
     *
     * @param hero Der Held im Spiel.
     * @param progressBarManager Der Manager für die Fortschrittsbalken.
     */
    public LevelManager(Hero hero, ProgressBarManager progressBarManager) {
        this.hero = hero;
        this.progressBarManager = progressBarManager;
        this.random = new Random();
        loadCurrentLevel();
        this.currentMonster = createMonsterForLevel(currentLevel);
        updateProgressBars();
    }

    /**
     * Wechselt zum nächsten Level.
     */
    public void nextLevel() {
        if (random.nextDouble() <= getIronChance(currentLevel)) {
            hero.addIron(1);
        }

        currentLevel++;
        saveCurrentLevel(currentLevel);
        resetHeroStats();
        currentMonster = createMonsterForLevel(currentLevel);
        updateProgressBars();
    }

    /**
     * Berechnet die Wahrscheinlichkeit, Eisen zu finden.
     *
     * @param level Das aktuelle Level.
     * @return Die Wahrscheinlichkeit, Eisen zu finden.
     */
    public double getIronChance(int level) {
        return Math.min(0.05 * level, 1.0);
    }

    /**
     * Berechnet die Menge an Münzen, die im aktuellen Level verdient werden können.
     *
     * @return Die Menge an verdienten Münzen.
     */
    public int calculateCoins() {
        return currentLevel * (random.nextInt(5) + 1);
    }

    /**
     * Setzt die Statistiken des Helden zurück.
     */
    public void resetHeroStats() {
        hero.setHealth(100);
        progressBarManager.setHeroHealth(100);
        if (hero.getEquippedArmor() != null) {
            hero.setArmor(hero.getEquippedArmor().getDefense());
        } else {
            hero.setArmor(0);
        }
        progressBarManager.setHeroArmor(hero.getArmor());
    }

    /**
     * Verbleibt im aktuellen Level.
     */
    public void stayAtCurrentLevel() {
        System.out.println("Du bist im aktuellen Level geblieben.");
    }

    /**
     * Erstellt ein Monster für das aktuelle Level.
     *
     * @param level Das aktuelle Level.
     * @return Das erstellte Monster.
     */
    private Monster createMonsterForLevel(int level) {
        int health = Math.min(30 + level * 10, 100);
        int armor = level > 7 ? Math.min(20 + (level - 7) * 10, 100) : 0;
        String weaponImage = getWeaponImageForLevel(level);
        int damage = getWeaponDamageForLevel(level);
        return new Monster("Monster Level " + level, health, armor, weaponImage, damage);
    }

    /**
     * Gibt das Waffenbild für das aktuelle Level zurück.
     *
     * @param level Das aktuelle Level.
     * @return Der Pfad zum Waffenbild.
     */
    private String getWeaponImageForLevel(int level) {
        if (level <= 3) return "/Bilder/Waffen/hands.png";
        else if (level <= 6) return "/Bilder/Waffen/bonebow.png";
        else if (level <= 9) return "/Bilder/Waffen/bonesword.png";
        else if (level <= 12) return "/Bilder/Waffen/woodbow.png";
        else if (level <= 15) return "/Bilder/Waffen/woodsword.png";
        else return "/Bilder/Waffen/ironsword.png";
    }

    /**
     * Gibt den Waffenschaden für das aktuelle Level zurück.
     *
     * @param level Das aktuelle Level.
     * @return Der Waffenschaden.
     */
    private int getWeaponDamageForLevel(int level) {
        if (level <= 3) return 5 + level * 2;
        else if (level <= 6) return 10 + (level - 3) * 3;
        else if (level <= 9) return 15 + (level - 6) * 3;
        else if (level <= 12) return 20 + (level - 9) * 3;
        else if (level <= 15) return 25 + (level - 12) * 3;
        else return 30 + (level - 15) * 2;
    }

    /**
     * Aktualisiert die Fortschrittsbalken für Monster und Held.
     */
    private void updateProgressBars() {
        progressBarManager.setEnemyHealth(currentMonster.getHealth());
        progressBarManager.setEnemyArmor(currentMonster.getArmor());
    }

    /**
     * Gibt das aktuelle Level zurück.
     *
     * @return Das aktuelle Level.
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Gibt das aktuelle Monster zurück.
     *
     * @return Das aktuelle Monster.
     */
    public Monster getCurrentMonster() {
        return currentMonster;
    }

    /**
     * Besiegt das aktuelle Monster und wechselt zum nächsten Level.
     */
    public void defeatMonster() {
        int monsterCoins = calculateCoins();
        hero.addCoins(monsterCoins);
        nextLevel();
    }

    /**
     * Speichert das aktuelle Level in eine Datei.
     *
     * @param level Das aktuelle Level.
     */
    public void saveCurrentLevel(int level) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("currentLevel.txt"))) {
            writer.write(String.valueOf(level));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lädt das aktuelle Level aus einer Datei.
     */
    private void loadCurrentLevel() {
        try (BufferedReader reader = new BufferedReader(new FileReader("currentLevel.txt"))) {
            String line = reader.readLine();
            if (line != null) {
                currentLevel = Integer.parseInt(line);
            } else {
                currentLevel = 1;
            }
        } catch (IOException e) {
            currentLevel = 1;
        }
    }

    /**
     * Setzt das Level auf 1 zurück.
     */
    public static void resetLevel() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("currentLevel.txt"))) {
            writer.write("1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verursacht Schaden an der Rüstung des Helden.
     *
     * @param damage Der zugefügte Schaden.
     */
    public void dealDamageToHeroArmor(int damage) {
        if (hero.getArmor() > 0) {
            int remainingDamage = damage - hero.getArmor();
            hero.setArmor(Math.max(0, hero.getArmor() - damage)); // Aktualisiert die Rüstung des Helden

            // Falls noch Schaden übrig ist, wird dieser der Gesundheit zugefügt
            if (remainingDamage > 0) {
                progressBarManager.dealDamageToHero(remainingDamage);
            }

            // Aktualisiert die Armor-ProgressBar
            progressBarManager.setHeroArmor(hero.getArmor());
        } else {
            // Wenn keine Rüstung mehr vorhanden ist, füge den gesamten Schaden der Gesundheit zu
            progressBarManager.dealDamageToHero(damage);
        }
    }
}
