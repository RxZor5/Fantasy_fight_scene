package fantasyrollenspiel.Fight;

import fantasyrollenspiel.Fight.Armor.Armor;
import fantasyrollenspiel.Hero.Hero;
import javafx.scene.control.ProgressBar;

public class ProgressBarManager {

    private ProgressBar heroHealthBar;
    private ProgressBar monsterHealthBar;
    private ProgressBar heroArmorBar;
    private ProgressBar monsterArmorBar;

    private int heroHealth = 100;
    private int enemyHealth = 100;
    private int heroArmor = 0; // Default value for hero armor
    private int enemyArmor = 30; // Default value for enemy armor (Eisen)

    private boolean heroHasArmor = false; // Default: hero has no armor
    private boolean enemyHasArmor = true; // Default: enemy has armor

    public ProgressBarManager(ProgressBar heroHealthBar, ProgressBar monsterHealthBar, ProgressBar heroArmorBar, ProgressBar monsterArmorBar) {
        this.heroHealthBar = heroHealthBar;
        this.monsterHealthBar = monsterHealthBar;
        this.heroArmorBar = heroArmorBar;
        this.monsterArmorBar = monsterArmorBar;

        initializeBars();
    }

    private void initializeBars() {
        heroHealthBar.setProgress(heroHealth / 100.0);
        monsterHealthBar.setProgress(enemyHealth / 100.0);
        heroArmorBar.setProgress(heroArmor / 100.0);
        monsterArmorBar.setProgress(enemyArmor / 100.0);
    }

    public void updateHealthBar(ProgressBar healthBar, int health) {
        System.out.println("Updating Health Bar: " + health);
        healthBar.setProgress(health / 100.0);
    }

    public void updateArmorBar(ProgressBar armorBar, int armor) {
        System.out.println("Updating Armor Bar: " + armor);
        armorBar.setProgress(armor / 100.0);
    }

    public boolean isHeroHasArmor() {
        return heroHasArmor;
    }

    public void setHeroHasArmor(boolean heroHasArmor) {
        this.heroHasArmor = heroHasArmor;
        if (!heroHasArmor) {
            this.heroArmor = 0;
        }
        updateArmorBar(heroArmorBar, heroArmor);
    }

    public boolean isEnemyHasArmor() {
        return enemyHasArmor;
    }

    public void setEnemyHasArmor(boolean enemyHasArmor) {
        this.enemyHasArmor = enemyHasArmor;
        if (!enemyHasArmor) {
            this.enemyArmor = 0;
        }
        updateArmorBar(monsterArmorBar, enemyArmor);
    }

    public void buyArmor(boolean isHero, Armor armor) {
        if (isHero) {
            if (armor.getDefense() > heroArmor) {
                setHeroArmor(armor.getDefense());
                setHeroHasArmor(true);
            }
        } else {
            if (armor.getDefense() > enemyArmor) {
                setEnemyArmor(armor.getDefense());
                setEnemyHasArmor(true);
            }
        }
    }

    public int getHeroHealth() {
        return heroHealth;
    }

    public void setHeroHealth(int heroHealth) {
        this.heroHealth = heroHealth;
        updateHealthBar(heroHealthBar, heroHealth);
    }

    public int getEnemyHealth() {
        return enemyHealth;
    }

    public void setEnemyHealth(int enemyHealth) {
        this.enemyHealth = enemyHealth;
        updateHealthBar(monsterHealthBar, enemyHealth);
    }

    public int getHeroArmor() {
        return heroArmor;
    }

    public void setHeroArmor(int heroArmor) {
        this.heroArmor = heroArmor;
        updateArmorBar(heroArmorBar, heroArmor);
    }

    public int getEnemyArmor() {
        return enemyArmor;
    }

    public void setEnemyArmor(int enemyArmor) {
        this.enemyArmor = enemyArmor;
        updateArmorBar(monsterArmorBar, enemyArmor);
    }

    public void dealDamageToHero(int damage) {
        System.out.println("Schaden an Helden: " + damage);
        if (heroArmor > 0) {
            int armorDamage = Math.min(damage, heroArmor);
            heroArmor -= armorDamage;
            System.out.println("Rüstungsschaden: " + armorDamage + ", Verbleibender Rüstung: " + heroArmor);
            updateArmorBar(heroArmorBar, heroArmor);

            int remainingDamage = damage - armorDamage;
            System.out.println("Verbleibender Schaden nach Rüstung: " + remainingDamage);
            if (remainingDamage > 0) {
                heroHealth = Math.max(heroHealth - remainingDamage, 0);
                updateHealthBar(heroHealthBar, heroHealth);
            }
        } else {
            heroHealth = Math.max(heroHealth - damage, 0);
            updateHealthBar(heroHealthBar, heroHealth);
        }
        System.out.println("Helden-Gesundheit nach Schaden: " + heroHealth);
        System.out.println("Helden-Rüstung nach Schaden: " + heroArmor);
    }

    public void dealDamageToHeroArmor(int damage, Hero hero) {
        System.out.println("Schaden an Heldenrüstung: " + damage);
        int armor = hero.getArmor();
        if (armor > 0) {
            int armorDamage = Math.min(damage, armor);
            hero.setArmor(armor - armorDamage); // Aktualisiere die Rüstung des Helden
            updateArmorBar(heroArmorBar, hero.getArmor()); // Aktualisiere die Rüstungsleiste

            int remainingDamage = damage - armorDamage;
            System.out.println("Rüstungsschaden: " + armorDamage + ", Verbleibender Schaden nach Rüstung: " + remainingDamage);
            if (remainingDamage > 0) {
                heroHealth = Math.max(heroHealth - remainingDamage, 0);
                updateHealthBar(heroHealthBar, heroHealth); // Aktualisiere die Gesundheitsleiste
            }
        } else {
            heroHealth = Math.max(heroHealth - damage, 0);
            updateHealthBar(heroHealthBar, heroHealth); // Aktualisiere die Gesundheitsleiste
        }
        System.out.println("Helden-Gesundheit nach Rüstungsschaden: " + heroHealth);
        System.out.println("Helden-Rüstung nach Rüstungsschaden: " + hero.getArmor());
    }


    public void dealDamageToEnemy(int damage) {
        if (enemyArmor > 0) {
            enemyArmor = Math.max(enemyArmor - damage, 0);
            updateArmorBar(monsterArmorBar, enemyArmor);
        } else {
            enemyHealth = Math.max(enemyHealth - damage, 0);
            updateHealthBar(monsterHealthBar, enemyHealth);
        }
    }
}
