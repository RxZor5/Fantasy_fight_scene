package fantasyrollenspiel.Fight.Buttons;

/**
 * Repräsentiert den Heilknopf im Spiel.
 */
public class Heal extends GameButton {
    private int heroHealth;
    private javafx.scene.control.ProgressBar healthBar;

    /**
     * Konstruktor zum Erstellen eines Heilknopfs.
     *
     * @param initialHealth Die anfängliche Gesundheit des Helden.
     * @param healthBar Die Gesundheitsleiste des Helden.
     */
    public Heal(int initialHealth, javafx.scene.control.ProgressBar healthBar) {
        super("Heal");
        this.heroHealth = initialHealth;
        this.healthBar = healthBar;
        this.setOnAction(event -> healAction());
    }

    /**
     * Führt die Heilungshandlung aus.
     */
    private void healAction() {
        heroHealth = Math.min(heroHealth + 10, 100); // Beispiel-Heilung, maximale Gesundheit 100
        updateHealthBar();
        showAlert("Heal", "Du wurdest geheilt! Neue Gesundheit: " + heroHealth);
    }

    /**
     * Aktualisiert die Gesundheitsleiste des Helden.
     */
    private void updateHealthBar() {
        healthBar.setProgress(heroHealth / 100.0);
    }

    /**
     * Gibt die aktuelle Gesundheit des Helden zurück.
     *
     * @return Die aktuelle Gesundheit des Helden.
     */
    public int getHeroHealth() {
        return heroHealth;
    }
}
