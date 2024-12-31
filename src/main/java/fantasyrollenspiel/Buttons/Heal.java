package fantasyrollenspiel.Buttons;

public class Heal extends GameButton {
    private int heroHealth;
    private javafx.scene.control.ProgressBar healthBar;

    public Heal(int initialHealth, javafx.scene.control.ProgressBar healthBar) {
        super("Heal");
        this.heroHealth = initialHealth;
        this.healthBar = healthBar;
        this.setOnAction(event -> healAction());
    }

    private void healAction() {
        heroHealth = Math.min(heroHealth + 10, 100); // Beispiel-Heilung, maximale Gesundheit 100
        updateHealthBar();
        showAlert("Heal", "Du wurdest geheilt! Neue Gesundheit: " + heroHealth);
    }

    private void updateHealthBar() {
        healthBar.setProgress(heroHealth / 100.0);
    }

    public int getHeroHealth() {
        return heroHealth;
    }
}
