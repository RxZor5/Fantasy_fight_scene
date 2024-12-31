package fantasyrollenspiel;

import fantasyrollenspiel.Animations.Animations;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;

public class HelloController {

    @FXML
    private ImageView heroImage;

    @FXML
    public ImageView monsterImage;

    @FXML
    public ImageView ivAttack;

    @FXML
    public ImageView ivHeal;

    @FXML
    private ProgressBar heroHealthBar;

    @FXML
    private ProgressBar monsterHealthBar;

    private Animations animations;
    private int heroHealth = 50;
    private int enemyHealth = 100;
    private Random random;

    @FXML
    public void initialize() {
        random = new Random();

        // Erstelle eine Instanz der Animations-Klasse
        animations = new Animations();

        // Setze das heroImage der Animations-Klasse
        animations.heroImage = heroImage;

        // Starte die Orc-Animation
        animations.orcAnimation();

        // Rogue
        animations.monsterImage = monsterImage;
        animations.rogueAnimation();

        // Buttons
        final String AttackButton = getClass().getResource("/Bilder/Buttons/Button_Red_3Slides.png").toExternalForm();
        Image Attack = new Image(AttackButton);
        ivAttack.setImage(Attack);

        final String HealButton = getClass().getResource("/Bilder/Buttons/Button_Blue_3Slides.png").toExternalForm();
        Image Heal = new Image(HealButton);
        ivHeal.setImage(Heal);

        // Button-Aktionen
        ivAttack.setOnMouseClicked(event -> attackAction());
        ivHeal.setOnMouseClicked(event -> healAction());

        // Initialisieren der HealthBars
        heroHealthBar.setProgress(heroHealth / 100.0);
        monsterHealthBar.setProgress(enemyHealth / 100.0);
    }

    private void attackAction() {
        int damage = random.nextInt(11) + 10; // Schaden zwischen 10 und 20
        enemyHealth = Math.max(enemyHealth - damage, 0); // Gesundheit kann nicht unter 0 fallen
        updateHealthBar(monsterHealthBar, enemyHealth); // Aktualisiere die Healthbar
        showAlert("Attack", "Du hast den Gegner angegriffen! Schaden: " + damage + ". Gesundheit des Gegners: " + enemyHealth);
    }

    private void healAction() {
        heroHealth = Math.min(heroHealth + 10, 100); // Beispiel-Heilung, maximale Gesundheit 100
        updateHealthBar(heroHealthBar, heroHealth); // Aktualisiere die Healthbar
        showAlert("Heal", "Du wurdest geheilt! Neue Gesundheit: " + heroHealth);
    }

    private void updateHealthBar(ProgressBar healthBar, int health) {
        healthBar.setProgress(health / 100.0);
    }

    private void showAlert(String title, String message) {
        // Logik zum Anzeigen eines Alerts
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
