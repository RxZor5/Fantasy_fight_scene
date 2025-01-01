package fantasyrollenspiel.Fight.Buttons;

import java.util.Random;
import javafx.scene.control.ProgressBar;

public class StrongerAttack extends GameButton {
    private int enemyHealth;
    private Random random;
    private ProgressBar healthBar;

    public StrongerAttack(int initialHealth, ProgressBar healthBar) {
        super("Stronger Attack");
        this.enemyHealth = initialHealth;
        this.healthBar = healthBar;
        this.random = new Random();
        this.setOnAction(event -> strongerAttackAction());
    }

    private void strongerAttackAction() {
        int damage = random.nextInt(21) + 30; // Schaden zwischen 30 und 50
        enemyHealth = Math.max(enemyHealth - damage, 0); // Gesundheit kann nicht unter 0 fallen
        updateHealthBar();
        showAlert("Stronger Attack", "Du hast einen starken Angriff ausgeführt! Schaden: " + damage + ". Gesundheit des Gegners: " + enemyHealth);
    }

    private void updateHealthBar() {
        healthBar.setProgress(enemyHealth / 100.0);
    }

    public int getEnemyHealth() {
        return enemyHealth;
    }
}
