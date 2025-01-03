package fantasyrollenspiel.Fight;

import fantasyrollenspiel.Fight.Armor.Armor;
import fantasyrollenspiel.Hero.Hero;
import javafx.scene.control.ProgressBar;

/**
 * Der Manager für die Fortschrittsbalken im Spiel.
 */
public class ProgressBarManager {

    private ProgressBar heroHealthBar;
    private ProgressBar monsterHealthBar;
    private ProgressBar heroArmorBar;
    private ProgressBar monsterArmorBar;

    private int heroHealth = 100;
    private int enemyHealth = 100;
    private int heroArmor = 0; // Standardwert für die Rüstung des Helden
    private int enemyArmor = 30; // Standardwert für die Rüstung des Gegners (Eisen)

    private boolean heroHasArmor = false; // Standard: Held hat keine Rüstung
    private boolean enemyHasArmor = true; // Standard: Gegner hat Rüstung

    /**
     * Konstruktor zum Erstellen eines ProgressBarManagers.
     *
     * @param heroHealthBar    Die Gesundheitsleiste des Helden.
     * @param monsterHealthBar Die Gesundheitsleiste des Gegners.
     * @param heroArmorBar     Die Rüstungsleiste des Helden.
     * @param monsterArmorBar  Die Rüstungsleiste des Gegners.
     */
    public ProgressBarManager(ProgressBar heroHealthBar, ProgressBar monsterHealthBar, ProgressBar heroArmorBar, ProgressBar monsterArmorBar) {
        this.heroHealthBar = heroHealthBar;
        this.monsterHealthBar = monsterHealthBar;
        this.heroArmorBar = heroArmorBar;
        this.monsterArmorBar = monsterArmorBar;

        initializeBars();
    }

    /**
     * Initialisiert die Fortschrittsbalken.
     */
    private void initializeBars() {
        heroHealthBar.setProgress(heroHealth / 100.0);
        monsterHealthBar.setProgress(enemyHealth / 100.0);
        heroArmorBar.setProgress(heroArmor / 100.0);
        monsterArmorBar.setProgress(enemyArmor / 100.0);
    }

    /**
     * Aktualisiert die Gesundheitsleiste.
     *
     * @param healthBar Die Gesundheitsleiste.
     * @param health    Die aktuelle Gesundheit.
     */
    public void updateHealthBar(ProgressBar healthBar, int health) {
        System.out.println("Updating Health Bar: " + health);
        healthBar.setProgress(health / 100.0);
    }

    /**
     * Aktualisiert die Rüstungsleiste.
     *
     * @param armorBar Die Rüstungsleiste.
     * @param armor    Die aktuelle Rüstung.
     */
    public void updateArmorBar(ProgressBar armorBar, int armor) {
        System.out.println("Updating Armor Bar: " + armor);
        armorBar.setProgress(armor / 100.0);
    }


    /**
     * Setzt, ob der Gegner eine Rüstung hat.
     *
     * @param enemyHasArmor true, wenn der Gegner eine Rüstung hat, sonst false.
     */
    public void setEnemyHasArmor(boolean enemyHasArmor) {
        this.enemyHasArmor = enemyHasArmor;
        if (!enemyHasArmor) {
            this.enemyArmor = 0;
        }
        updateArmorBar(monsterArmorBar, enemyArmor);
    }

    /**
     * Kauft Rüstung für den Helden oder den Gegner.
     *
     * @param isHero true, wenn die Rüstung für den Helden ist, sonst false.
     * @param armor  Die gekaufte Rüstung.
     */
    public void buyArmor(boolean isHero, Armor armor) {
        if (isHero) {
            if (armor != null && armor.getDefense() > heroArmor) {
                setHeroArmor(armor.getDefense());
                setHeroHasArmor(true);
            }
        } else {
            if (armor != null && armor.getDefense() > enemyArmor) {
                setEnemyArmor(armor.getDefense());
                setEnemyHasArmor(true);
            }
        }
    }



    /**
     * Verursacht Schaden am Helden und aktualisiert die Gesundheits- und Rüstungsleisten.
     *
     * @param damage Der zugefügte Schaden.
     */
    public void dealDamageToHero(int damage) {
        System.out.println("Schaden an Helden: " + damage);
        if (heroArmor > 0) {
            int armorDamage = Math.min(damage, heroArmor);
            heroArmor -= armorDamage;
            System.out.println("Rüstungsschaden: " + armorDamage + ", Verbleibende Rüstung: " + heroArmor);
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


    /**
     * Verursacht Schaden an der Rüstung des Helden und aktualisiert die Gesundheits- und Rüstungsleisten.
     *
     * @param damage Der zugefügte Schaden.
     * @param hero   Der Held.
     */
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

    /**
     * Verursacht Schaden am Gegner und aktualisiert die Gesundheits- und Rüstungsleisten.
     *
     * @param damage Der zugefügte Schaden.
     */
    public void dealDamageToEnemy(int damage) {
        if (enemyArmor > 0) {
            enemyArmor = Math.max(enemyArmor - damage, 0);
            updateArmorBar(monsterArmorBar, enemyArmor);
        } else {
            enemyHealth = Math.max(enemyHealth - damage, 0);
            updateHealthBar(monsterHealthBar, enemyHealth);
        }
    }

    /**
     * Setzt, ob der Held eine Rüstung hat.
     *
     * @param heroHasArmor true, wenn der Held eine Rüstung hat, sonst false.
     */
    public void setHeroHasArmor(boolean heroHasArmor) {
        this.heroHasArmor = heroHasArmor;
        if (!heroHasArmor) {
            this.heroArmor = 0;
        }
        updateArmorBar(heroArmorBar, heroArmor);
    }

    /**
     * Gibt die aktuelle Gesundheit des Helden zurück.
     *
     * @return Die aktuelle Gesundheit des Helden.
     */
    public int getHeroHealth() {
        return heroHealth;
    }

    /**
     * Setzt die Gesundheit des Helden und aktualisiert die Gesundheitsleiste.
     *
     * @param heroHealth Die neue Gesundheit des Helden.
     */
    public void setHeroHealth(int heroHealth) {
        this.heroHealth = heroHealth;
        updateHealthBar(heroHealthBar, heroHealth);
    }

    /**
     * Gibt die aktuelle Gesundheit des Gegners zurück.
     *
     * @return Die aktuelle Gesundheit des Gegners.
     */
    public int getEnemyHealth() {
        return enemyHealth;
    }

    /**
     * Setzt die Gesundheit des Gegners und aktualisiert die Gesundheitsleiste.
     *
     * @param enemyHealth Die neue Gesundheit des Gegners.
     */
    public void setEnemyHealth(int enemyHealth) {
        this.enemyHealth = enemyHealth;
        updateHealthBar(monsterHealthBar, enemyHealth);
    }

    /**
     * Gibt die aktuelle Rüstung des Helden zurück.
     *
     * @return Die aktuelle Rüstung des Helden.
     */
    public int getHeroArmor() {
        return heroArmor;
    }

    /**
     * Setzt die Rüstung des Helden und aktualisiert die Rüstungsleiste.
     *
     * @param heroArmor Die neue Rüstung des Helden.
     */
    public void setHeroArmor(int heroArmor) {
        this.heroArmor = heroArmor;
        updateArmorBar(heroArmorBar, heroArmor);
    }


    /**
     * Setzt die Rüstung des Gegners und aktualisiert die Rüstungsleiste.
     *
     * @param enemyArmor Die neue Rüstung des Gegners.
     */
    public void setEnemyArmor(int enemyArmor) {
        this.enemyArmor = enemyArmor;
        updateArmorBar(monsterArmorBar, enemyArmor);
    }
}
