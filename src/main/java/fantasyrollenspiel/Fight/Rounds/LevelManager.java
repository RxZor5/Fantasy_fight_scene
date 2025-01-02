package fantasyrollenspiel.Fight.Rounds;

import fantasyrollenspiel.Monster.Monster;
import fantasyrollenspiel.Hero.Hero;
import fantasyrollenspiel.Fight.ProgressBarManager;
import java.io.*;
import java.util.Random;

public class LevelManager {

    private int currentLevel;
    private Hero hero;
    private ProgressBarManager progressBarManager;
    private Monster currentMonster;
    private Random random;

    public LevelManager(Hero hero, ProgressBarManager progressBarManager) {
        this.hero = hero;
        this.progressBarManager = progressBarManager;
        this.random = new Random();
        loadCurrentLevel();
        this.currentMonster = createMonsterForLevel(currentLevel);
        updateProgressBars();
    }

    public void nextLevel() {
        if (random.nextDouble() <= getIronChance(currentLevel)) {
            hero.addIron(1);
        }

        currentLevel++;
        saveCurrentLevel(currentLevel); // Speichere das aktuelle Level nach jedem Level-Up
        resetHeroStats();
        currentMonster = createMonsterForLevel(currentLevel);
        updateProgressBars();
    }

    public double getIronChance(int level) {
        return Math.min(0.05 * level, 1.0);  // Calculate the chance of earning iron, capped at 100%
    }

    public int calculateCoins() {
        return currentLevel * (random.nextInt(5) + 1); // Multipliziere den Coin-Wert mit dem aktuellen Level
    }

    public void resetHeroStats() {
        hero.setHealth(100);  // Reset hero's health
        progressBarManager.setHeroHealth(100);
        hero.setArmor(50);  // Reset hero's armor
        progressBarManager.setHeroArmor(50);
    }

    public void stayAtCurrentLevel() {
        System.out.println("You've stayed at the current level.");
    }

    private Monster createMonsterForLevel(int level) {
        int health = Math.min(30 + level * 10, 100);
        int armor = level > 7 ? Math.min(20 + (level - 7) * 10, 100) : 0;
        String weaponImage = getWeaponImageForLevel(level);
        int damage = getWeaponDamageForLevel(level);
        return new Monster("Monster Level " + level, health, armor, weaponImage, damage);
    }

    private String getWeaponImageForLevel(int level) {
        if (level <= 3) return "/Bilder/Waffen/hands.png";
        else if (level <= 6) return "/Bilder/Waffen/bonebow.png";
        else if (level <= 9) return "/Bilder/Waffen/bonesword.png";
        else if (level <= 12) return "/Bilder/Waffen/woodbow.png";
        else if (level <= 15) return "/Bilder/Waffen/woodsword.png";
        else return "/Bilder/Waffen/ironsword.png";
    }

    private int getWeaponDamageForLevel(int level) {
        if (level <= 3) return 5 + level * 2;
        else if (level <= 6) return 10 + (level - 3) * 3;
        else if (level <= 9) return 15 + (level - 6) * 3;
        else if (level <= 12) return 20 + (level - 9) * 3;
        else if (level <= 15) return 25 + (level - 12) * 3;
        else return 30 + (level - 15) * 2;
    }

    private void updateProgressBars() {
        progressBarManager.setEnemyHealth(currentMonster.getHealth());
        progressBarManager.setEnemyArmor(currentMonster.getArmor());
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public Monster getCurrentMonster() {
        return currentMonster;
    }

    public void defeatMonster() {
        int monsterCoins = calculateCoins(); // Verwende die neue Berechnungsmethode für Coins
        hero.addCoins(monsterCoins);
        nextLevel();
    }

    // Speichere das aktuelle Level in einer Datei
    public void saveCurrentLevel(int level) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("currentLevel.txt"))) {
            writer.write(String.valueOf(level));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Lade das aktuelle Level aus der Datei
    private void loadCurrentLevel() {
        try (BufferedReader reader = new BufferedReader(new FileReader("currentLevel.txt"))) {
            String line = reader.readLine();
            if (line != null) {
                currentLevel = Integer.parseInt(line);
            } else {
                currentLevel = 1; // Wenn die Datei leer ist, beginne bei Level 1
            }
        } catch (IOException e) {
            currentLevel = 1; // Wenn die Datei nicht gefunden wird, beginne bei Level 1
        }
    }

    // Setze das Level auf 1 beim Programmende
    public static void resetLevel() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("currentLevel.txt"))) {
            writer.write("1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
