package fantasyrollenspiel;

import fantasyrollenspiel.Animations.Animations;
import fantasyrollenspiel.Armor.Armor;
import fantasyrollenspiel.Materialien.Eisen;
import fantasyrollenspiel.Fight.DecideTurn;
import fantasyrollenspiel.Fight.ProgressBarManager;
import fantasyrollenspiel.Fight.Rounds.LevelManager;
import fantasyrollenspiel.Hero.Hero;
import fantasyrollenspiel.Monster.Monster;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;

public class HelloController {

    @FXML
    private ImageView heroImage;

    @FXML
    private ImageView heroWeaponImage;

    @FXML
    public ImageView monsterImage;

    @FXML
    private ImageView monsterWeaponImage;

    @FXML
    public ImageView ivAttack;

    @FXML
    public ImageView ivHeal;

    @FXML
    private ProgressBar heroHealthBar;

    @FXML
    private ProgressBar monsterHealthBar;

    @FXML
    private ProgressBar heroArmorBar;

    @FXML
    private ProgressBar monsterArmorBar;

    @FXML
    private Label lHeroName;

    @FXML
    private Label lMonsterName;

    private Animations animations;
    private ProgressBarManager progressBarManager;
    private DecideTurn decideTurn;
    private Random random;
    private LevelManager levelManager;
    private Hero hero;

    @FXML
    public void initialize() {
        random = new Random();
        decideTurn = new DecideTurn();

        hero = new Hero("Spieler", 100, 20, 50);
        progressBarManager = new ProgressBarManager(heroHealthBar, monsterHealthBar, heroArmorBar, monsterArmorBar);
        levelManager = new LevelManager(hero, progressBarManager);

        // Initialize animations
        animations = new Animations();
        animations.heroImage = heroImage;
        animations.orcAnimation();
        animations.monsterImage = monsterImage;
        animations.rogueAnimation();

        // Set button images
        final String AttackButton = getClass().getResource("/Bilder/Buttons/Button_Red_3Slides.png").toExternalForm();
        Image Attack = new Image(AttackButton);
        ivAttack.setImage(Attack);

        final String HealButton = getClass().getResource("/Bilder/Buttons/Button_Blue_3Slides.png").toExternalForm();
        Image Heal = new Image(HealButton);
        ivHeal.setImage(Heal);

        // Button actions
        ivAttack.setOnMouseClicked(event -> attackAction());
        ivHeal.setOnMouseClicked(event -> healAction());

        // Initialize names
        setNames("Spieler", "Monster");

        // Initialize weapon images
        updateWeaponImages();

        // Start game
        startGame();
    }

    public void setNames(String heroName, String monsterName) {
        lHeroName.setText(heroName);
        lMonsterName.setText(monsterName);
    }

    private void updateWeaponImages() {
        final String heroWeaponPath = getClass().getResource("/Bilder/Waffen/hands.png").toExternalForm();
        Image heroWeapon = new Image(heroWeaponPath);
        heroWeaponImage.setImage(heroWeapon);

        final String monsterWeaponPath = getClass().getResource(levelManager.getCurrentMonster().getWeaponImage()).toExternalForm();
        Image monsterWeapon = new Image(monsterWeaponPath);
        monsterWeaponImage.setImage(monsterWeapon);
    }

    private void startGame() {
        if (decideTurn.isPlayerTurn()) {
            showAlert("Spielbeginn", "Der Spieler beginnt!");
            System.out.println("Der Spieler beginnt!");
        } else {
            showAlert("Spielbeginn", "Das Monster beginnt!");
            System.out.println("Das Monster beginnt!");
            monsterAttack();
        }
    }

    private void attackAction() {
        if (!decideTurn.isPlayerTurn()) return;

        int damage = random.nextInt(11) + 10;
        progressBarManager.dealDamageToEnemy(damage);
        showAlert("Attack", "Du hast den Gegner angegriffen! Schaden: " + damage + ". Rüstung des Gegners: " + progressBarManager.getEnemyArmor() + ". Gesundheit des Gegners: " + progressBarManager.getEnemyHealth());

        if (progressBarManager.getEnemyHealth() == 0) {
            showAlert("Victory!", "Der Gegner wurde besiegt!");
            levelManager.nextLevel();
            updateWeaponImages(); // Aktualisiere das Waffenbild nach dem Levelaufstieg
            return;
        }

        decideTurn.nextTurn();
        monsterAttack();
    }

    private void healAction() {
        if (!decideTurn.isPlayerTurn()) return;

        progressBarManager.setHeroHealth(Math.min(progressBarManager.getHeroHealth() + 10, 100));
        showAlert("Heal", "Du wurdest geheilt! Neue Gesundheit: " + progressBarManager.getHeroHealth());

        decideTurn.nextTurn();
        monsterAttack();
    }

    private void monsterAttack() {
        int damage = random.nextInt(11) + 10;
        progressBarManager.dealDamageToHero(damage);
        showAlert("Monster Attack", "Das Monster hat dich angegriffen! Schaden: " + damage + ". Deine Rüstung: " + progressBarManager.getHeroArmor() + ". Deine Gesundheit: " + progressBarManager.getHeroHealth());

        if (progressBarManager.getHeroHealth() == 0) {
            showAlert("Defeat!", "Du wurdest besiegt!");
            levelManager.stayAtCurrentLevel();
            return;
        }

        decideTurn.nextTurn();
    }

    private void showAlert(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
