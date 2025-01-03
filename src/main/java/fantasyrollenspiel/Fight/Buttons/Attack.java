package fantasyrollenspiel.Fight.Buttons;

import java.util.Random;
import javafx.scene.control.ProgressBar;

/**
 * Repräsentiert den Angriffsknopf im Spiel.
 */
public class Attack extends GameButton {
    private int enemyHealth;
    private Random random;
    private ProgressBar healthBar;

    /**
     * Konstruktor zum Erstellen eines Angriffsknopfs.
     *
     * @param initialHealth Die anfängliche Gesundheit des Gegners.
     * @param healthBar Die Gesundheitsleiste des Gegners.
     */
    public Attack(int initialHealth, ProgressBar healthBar) {
        super("Attack");
        this.enemyHealth = initialHealth;
        this.healthBar = healthBar;
        this.random = new Random();
        this.setOnAction(event -> attackAction());
    }

    /**
     * Führt die Angriffshandlung aus.
     */
    private void attackAction() {
        int damage = random.nextInt(11) + 10; // Schaden zwischen 10 und 20
        enemyHealth = Math.max(enemyHealth - damage, 0); // Gesundheit kann nicht unter 0 fallen
        updateHealthBar();
        showAlert("Attack", "Du hast den Gegner angegriffen! Schaden: " + damage + ". Gesundheit des Gegners: " + enemyHealth);
    }

    /**
     * Aktualisiert die Gesundheitsleiste des Gegners.
     */
    private void updateHealthBar() {
        healthBar.setProgress(enemyHealth / 100.0);
    }

    /**
     * Gibt die aktuelle Gesundheit des Gegners zurück.
     *
     * @return Die aktuelle Gesundheit des Gegners.
     */
    public int getEnemyHealth() {
        return enemyHealth;
    }
}
