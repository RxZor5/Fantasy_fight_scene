package fantasyrollenspiel;

import fantasyrollenspiel.Hero.Hero;
import javafx.scene.control.ProgressBar;

public class ProgressBarManager {
    private ProgressBar heroHealthBar;
    private ProgressBar monsterHealthBar;
    private ProgressBar heroArmorBar;
    private ProgressBar monsterArmorBar;

    public ProgressBarManager(ProgressBar heroHealthBar, ProgressBar monsterHealthBar, ProgressBar heroArmorBar, ProgressBar monsterArmorBar) {
        this.heroHealthBar = heroHealthBar;
        this.monsterHealthBar = monsterHealthBar;
        this.heroArmorBar = heroArmorBar;
        this.monsterArmorBar = monsterArmorBar;
    }

    public void setHeroHealth(int health) {
        heroHealthBar.setProgress((double) health / 100);
    }

    public void setHeroArmor(int armor) {
        heroArmorBar.setProgress((double) armor / 100);
    }

    public void setEnemyHealth(int health) {
        monsterHealthBar.setProgress((double) health / 100);
    }

    public void setEnemyArmor(int armor) {
        monsterArmorBar.setProgress((double) armor / 100);
    }

    public void dealDamageToHero(int damage) {
        int currentHealth = (int) (heroHealthBar.getProgress() * 100);
        setHeroHealth(Math.max(0, currentHealth - damage));
    }

    public void dealDamageToHeroArmor(int damage, Hero hero) {
        if (hero.getArmor() > 0) {
            int remainingDamage = damage - hero.getArmor();
            hero.setArmor(Math.max(0, hero.getArmor() - damage)); // Update the hero's armor

            // Falls noch Schaden übrig ist, wird dieser der Gesundheit zugefügt
            if (remainingDamage > 0) {
                dealDamageToHero(remainingDamage);
            }

            // Aktualisiere die Armor-ProgressBar
            setHeroArmor(hero.getArmor());
        } else {
            // Wenn keine Rüstung mehr vorhanden ist, füge den gesamten Schaden der Gesundheit zu
            dealDamageToHero(damage);
        }
    }

    public void dealDamageToEnemy(int damage) {
        int currentHealth = (int) (monsterHealthBar.getProgress() * 100);
        setEnemyHealth(Math.max(0, currentHealth - damage));
    }
}
