package fantasyrollenspiel;

import fantasyrollenspiel.Animations.Animations;
import fantasyrollenspiel.Fight.DecideTurn;
import fantasyrollenspiel.Fight.ProgressBarManager;
import fantasyrollenspiel.Fight.Rounds.LevelManager;
import fantasyrollenspiel.Hero.Hero;
import fantasyrollenspiel.Fight.Buttons.Tränke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.util.Random;

public class FightController {

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

    @FXML
    private GridPane gridPane;

    @FXML
    private Button backButton;

    @FXML
    private Label coinsEarnedLabel;

    @FXML
    private Label ironLabel;

    @FXML
    private Label xpLabel;  // XP Label

    private Animations animations;
    private ProgressBarManager progressBarManager;
    private DecideTurn decideTurn;
    private Random random;
    private LevelManager levelManager;
    private Hero hero;

    private TileMapController tileMapController;

    private int xpEarned = 0;

    @FXML
    public void initialize() {
        random = new Random();
        decideTurn = new DecideTurn();

        hero = new Hero("Spieler", 100, 20, 0); // Start with 0 coins
        progressBarManager = new ProgressBarManager(heroHealthBar, monsterHealthBar, heroArmorBar, monsterArmorBar);
        levelManager = new LevelManager(hero, progressBarManager);

        animations = new Animations();
        animations.heroImage = heroImage;
        animations.orcAnimation(heroImage);
        animations.monsterImage = monsterImage;
        animations.rogueAnimation();

        final String AttackButton = getClass().getResource("/Bilder/Buttons/Button_Red_3Slides.png").toExternalForm();
        Image Attack = new Image(AttackButton);
        ivAttack.setImage(Attack);

        final String HealButton = getClass().getResource("/Bilder/Buttons/Button_Blue_3Slides.png").toExternalForm();
        Image Heal = new Image(HealButton);
        ivHeal.setImage(Heal);

        ivAttack.setOnMouseClicked(event -> attackAction());
        ivHeal.setOnMouseClicked(event -> healAction());

        setNames("Spieler", "Monster");
        updateWeaponImages();
        updateCoinsEarnedLabel();
        updateIronLabel();
        updateXpLabel();  // Update XP Label
        startGame();
    }

    public void setTileMapController(TileMapController controller) {
        this.tileMapController = controller;
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

    private void updateCoinsEarnedLabel() {
        coinsEarnedLabel.setText("Coins Earned: " + hero.getCoins());
    }

    private void updateIronLabel() {
        ironLabel.setText("Iron: " + hero.getIron());
    }

    private void updateXpLabel() {
        xpLabel.setText("XP: " + xpEarned);
    }

    private void startGame() {
        if (decideTurn.isPlayerTurn()) {
            showAlert("Spielbeginn", "Der Spieler beginnt!");
        } else {
            showAlert("Spielbeginn", "Das Monster beginnt!");
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
            int earnedCoins = (random.nextInt(5) + 1) * levelManager.getCurrentLevel();  // Apply multiplier based on current level
            int earnedIron = random.nextDouble() <= levelManager.getIronChance(levelManager.getCurrentLevel()) ? 1 : 0;
            int earnedXp = (random.nextInt(6) + 3) * levelManager.getCurrentLevel();  // Random XP between 3 and 8 multiplied by level
            hero.addCoins(earnedCoins);
            hero.addIron(earnedIron);
            xpEarned += earnedXp;
            updateCoinsEarnedLabel();
            updateIronLabel();
            updateXpLabel();
            levelManager.nextLevel();
            updateWeaponImages();
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

    public void addTränkeButton(Tränke tränkeButton) {
        gridPane.add(tränkeButton, 1, 1);
    }

    @FXML
    private void handleBackButton() {
        if (tileMapController != null) {
            tileMapController.addCoins(hero.getCoins());
            tileMapController.addIron(hero.getIron());
            tileMapController.addXP(xpEarned);  // Pass the XP to the map controller
        }
        try {
            StartGame.switchScene("/Map/map.fxml", hero.getCoins(), hero.getIron(), xpEarned);  // Pass the XP when switching scene
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
