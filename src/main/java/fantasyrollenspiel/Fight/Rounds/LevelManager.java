package fantasyrollenspiel.Fight.Rounds;

import fantasyrollenspiel.Monster.Monster;
import fantasyrollenspiel.Hero.Hero;
import fantasyrollenspiel.Fight.ProgressBarManager;
import javafx.scene.image.Image;

public class LevelManager {

    private int currentLevel;
    private Hero hero;
    private ProgressBarManager progressBarManager;
    private Monster currentMonster;

    public LevelManager(Hero hero, ProgressBarManager progressBarManager) {
        this.currentLevel = 1;
        this.hero = hero;
        this.progressBarManager = progressBarManager;
        this.currentMonster = createMonsterForLevel(currentLevel);
        updateProgressBars();
    }

    public void nextLevel() {
        if (currentLevel < 20) {
            currentLevel++;
            resetHeroStats();
            // Erstelle ein neues Monster für das nächste Level
            currentMonster = createMonsterForLevel(currentLevel);
            // Update den Fortschrittsbalken und andere relevante Informationen
            updateProgressBars();
        } else {
            System.out.println("Gratulation! Du hast das letzte Level erreicht.");
        }
    }

    public void resetHeroStats() {
        hero.setHealth(100);  // Setze die Gesundheit des Helden zurück
        progressBarManager.setHeroHealth(100);
        hero.setArmor(50);  // Setze die Rüstung des Helden zurück
        progressBarManager.setHeroArmor(50);
    }

    public void stayAtCurrentLevel() {
        System.out.println("Du bist im aktuellen Level stecken geblieben.");
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
}
