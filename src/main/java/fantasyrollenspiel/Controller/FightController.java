package fantasyrollenspiel.Controller;

import fantasyrollenspiel.Animations.Animations;
import fantasyrollenspiel.Fight.Armor.Armor;
import fantasyrollenspiel.Fight.DecideTurn;
import fantasyrollenspiel.Fight.ProgressBarManager;
import fantasyrollenspiel.Fight.Rounds.LevelManager;
import fantasyrollenspiel.Hero.Hero;
import fantasyrollenspiel.Inventory;
import fantasyrollenspiel.StartGame;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.*;

import java.util.Random;

public class FightController {

    @FXML
    private ImageView heroImage;

    @FXML
    public ImageView heroWeaponImage;

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
    private Label xpLabel;

    @FXML
    private TextArea battleLogTextArea;

    @FXML
    private Button updateArmorButton;



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

        hero = new Hero("Spieler", 100, 0, 0); // Start with 0 coins
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

        // Füge den OnClick Event-Handler für den neuen Button hinzu
        updateArmorButton.setOnMouseClicked(event -> updateArmor());

        addInventoryButton(new Button("Inventar"));
        progressBarManager = new ProgressBarManager(heroHealthBar, monsterHealthBar, heroArmorBar, monsterArmorBar);
        levelManager = new LevelManager(hero, progressBarManager);
        setNames("Spieler", "Monster");
        updateWeaponImages();
        addBattleLogMessage("");  // Initialize with empty log
        updateCoinsEarnedLabel();
        updateIronLabel();
        updateXpLabel();  // Update XP Label
        startGame();

        Platform.runLater(() -> {
            battleLogTextArea.getScene().setOnKeyPressed(event -> {
                if (event.getCode().toString().equals("G")) {
                    dodgeAction(); }
            });
        });
    }


    private void updateArmor() {
        // Beste verfügbare Rüstung ausrüsten
        equipBestArmor();
        addBattleLogMessage("Rüstung wurde aktualisiert. Aktuelle Rüstung: " + (hero.getEquippedArmor() != null ? hero.getEquippedArmor().getName() : "keine Rüstung"));

        // Aktualisiere die Rüstungswerte in ProgressBarManager nur, wenn der Held Rüstung trägt
        if (hero.getEquippedArmor() != null) {
            hero.getEquippedArmor().resetDefense(); // Verteidigungswert auf den Originalwert zurücksetzen
            hero.setArmor(hero.getEquippedArmor().getDefense()); // Aktuelle Rüstung verteidigen
            progressBarManager.setHeroArmor(hero.getEquippedArmor().getDefense());
        }

        // Bestätige, dass der Held Rüstung trägt oder nicht
        progressBarManager.setHeroHasArmor(hero.getEquippedArmor() != null);
    }





    private void equipBestArmor() {
        Armor bestArmor = Inventory.getBestArmor();
        if (bestArmor != null) {
            hero.equipArmor(bestArmor);
            progressBarManager.buyArmor(true, bestArmor); // Aktualisiere die Rüstungswerte und ProgressBars
            addBattleLogMessage("Beste Rüstung ausgerüstet: " + bestArmor.getName());
        } else {
            hero.setArmor(0);
            progressBarManager.buyArmor(true, null); // Keine Rüstung vorhanden
        }
    }




    private void dodgeAction() {
        if (!decideTurn.isPlayerTurn()) return;

        double dodgeChance = 0.1; // 10% Wahrscheinlichkeit zum Ausweichen
        if (random.nextDouble() < dodgeChance) {
            addBattleLogMessage("Du bist dem Angriff ausgewichen!");
            // Spieler ist sofort wieder dran
            decideTurn.nextTurn(); // Überspringe Monsterzug
            decideTurn.nextTurn();
        } else {
            addBattleLogMessage("Ausweichversuch fehlgeschlagen.");
            decideTurn.nextTurn();
            monsterAttack(); // Monster greift an, wenn Ausweichen fehlschlägt
        }
    }






    public void setTileMapController(TileMapController tileMapController) {
        this.tileMapController = tileMapController;
        tileMapController.setFightController(this);
    }

    private void addBattleLogMessage(String message) {
        battleLogTextArea.appendText(message + "\n");
    }



    public void setNames(String heroName, String monsterName) {
        lHeroName.setText(heroName);
        lMonsterName.setText(monsterName);
    }

    private void updateWeaponImages() {
        final String monsterWeaponPath = getClass().getResource(levelManager.getCurrentMonster().getWeaponImage()).toExternalForm();
        Image monsterWeapon = new Image(monsterWeaponPath);
        monsterWeaponImage.setImage(monsterWeapon);
    }

    public void updateHeroWeaponImage(String imagePath) {
        heroWeaponImage.setImage(new Image(getClass().getResource(imagePath).toExternalForm()));
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
            showAlert("Spielbeginn", "Der Spieler beginnt!"+ "\n");
        } else {
            showAlert("Spielbeginn", "Das Monster beginnt!"+ "\n");
            monsterAttack();
        }
    }

    private void resetMonster() {
        // Neue Werte für das Monster setzen (kann auch zufällig sein)
        int newHealth = 100; // Beispielwert, kann geändert werden
        int newArmor = 30; // Beispielwert, kann geändert werden

        // Monster-Health und -Armor zurücksetzen
        progressBarManager.setEnemyHealth(newHealth);
        progressBarManager.setEnemyArmor(newArmor);

        // Monster-Name und andere Eigenschaften aktualisieren
        setNames(hero.getName(), "Neues Monster");

        // Waffenbilder oder andere Darstellungen aktualisieren, falls notwendig
        updateWeaponImages();

        addBattleLogMessage("Ein neues Monster erscheint!" + "\n");
    }


    private void attackAction() {
        if (!decideTurn.isPlayerTurn()) return;

        int damage = random.nextInt(11) + 10;
        progressBarManager.dealDamageToEnemy(damage);
        addBattleLogMessage("Du hast den Gegner angegriffen! Schaden: " + damage + ". Rüstung des Gegners: " + progressBarManager.getEnemyArmor() + ". Gesundheit des Gegners: " + progressBarManager.getEnemyHealth()+ "\n");

        if (progressBarManager.getEnemyHealth() == 0) {
            addBattleLogMessage("Der Gegner wurde besiegt!" + "\n");
            int earnedCoins = (random.nextInt(5) + 1) * levelManager.getCurrentLevel();  // Apply multiplier based on current level
            int earnedIron = random.nextDouble() <= levelManager.getIronChance(levelManager.getCurrentLevel()) ? 1 : 0;
            int earnedXp = (random.nextInt(6) + 3) * levelManager.getCurrentLevel();  // Random XP between 3 and 8 multiplied by level
            hero.addCoins(earnedCoins);
            hero.addIron(earnedIron);
            xpEarned += earnedXp;
            updateCoinsEarnedLabel();
            updateIronLabel();
            updateXpLabel();

            // Rüstung des Helden auf vollen Zustand zurücksetzen
            updateArmor();

            // Monster zurücksetzen
            resetMonster();

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
        addBattleLogMessage("Du wurdest geheilt! Neue Gesundheit: " + progressBarManager.getHeroHealth() + "\n");

        decideTurn.nextTurn();
        monsterAttack();
    }

    public void setHeroArmor(int armor) {
        if (this.hero != null) {
            this.hero.setArmor(armor);
            updateHeroStats();
        }
    }


    private void monsterAttack() {
        int damage = random.nextInt(11) + 10;
        System.out.println("Monster greift an mit Schaden: " + damage);

        if (hero.getArmor() > 0) {
            progressBarManager.dealDamageToHeroArmor(damage, hero);
        } else {
            progressBarManager.dealDamageToHero(damage);
        }

        addBattleLogMessage("Das Monster hat dich angegriffen! Schaden: " + damage + ". Deine Rüstung: " + progressBarManager.getHeroArmor() + ". Deine Gesundheit: " + progressBarManager.getHeroHealth() + "\n");

        System.out.println("Helden-Gesundheit: " + progressBarManager.getHeroHealth());
        System.out.println("Helden-Rüstung: " + progressBarManager.getHeroArmor());

        if (progressBarManager.getHeroHealth() == 0) {
            addBattleLogMessage("Du wurdest besiegt und hast Level " + levelManager.getCurrentLevel() + " erreicht\n");
            levelManager.resetLevel();
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

    public void addInventoryButton(Button inventoryButton) {
        gridPane.add(inventoryButton, 1, 1); // Fügen Sie den Inventar-Button zum GridPane hinzu
        inventoryButton.setOnAction(event -> showInventory()); // Zeige das Popup-Fenster bei Klick auf den Inventar-Button
        System.out.println("Inventar-Button hinzugefügt");
    }

    private void showInventory() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fantasyrollenspiel/inventory.fxml"));
            Parent root = fxmlLoader.load();
            InventoryController inventoryController = fxmlLoader.getController();
            inventoryController.setHeroWeaponImageView(heroWeaponImage);
            inventoryController.setFightController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Inventar");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setHero(Hero hero) {
        this.hero = hero;
        updateHeroStats();
    }




    public void updateHeroStats() {
        if (hero != null && heroHealthBar != null && heroArmorBar != null) {
            heroHealthBar.setProgress((double) hero.getHealth() / 100);
            heroArmorBar.setProgress((double) hero.getArmor() / 100);
        }
    }


    @FXML
    private void handleBackButton() {
        if (tileMapController != null) {
            tileMapController.addCoins(hero.getCoins());
            tileMapController.addIron(hero.getIron());
            tileMapController.addXP(xpEarned);
        }
        try {
            StartGame.switchScene("/Map/map.fxml", hero.getCoins(), hero.getIron(), xpEarned);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
