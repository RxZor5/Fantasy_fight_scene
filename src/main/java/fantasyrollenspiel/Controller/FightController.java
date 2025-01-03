
package fantasyrollenspiel.Controller;

import fantasyrollenspiel.Animations.Animations;
import fantasyrollenspiel.Fight.Armor.Armor;
import fantasyrollenspiel.Fight.DecideTurn;
import fantasyrollenspiel.Fight.ProgressBarManager;
import fantasyrollenspiel.Fight.Rounds.LevelManager;
import fantasyrollenspiel.Fight.Waffen.Weapon;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Modality;

import java.util.Random;

/**
 * Controller-Klasse zur Steuerung der Kampflogik und UI-Interaktionen.
 */
public class FightController {

    // FXML-Elemente für die Benutzeroberfläche
    @FXML
    private ImageView heroImage, heroWeaponImage, monsterImage, monsterWeaponImage, ivAttack, ivHeal;
    @FXML
    private ProgressBar heroHealthBar, monsterHealthBar, heroArmorBar, monsterArmorBar;
    @FXML
    private Label lHeroName, lMonsterName, coinsEarnedLabel, ironLabel, xpLabel;
    @FXML
    private TextArea battleLogTextArea;
    @FXML
    private Button updateArmorButton, backButton;

    // Instanzen von Hilfsklassen und weiteren benötigten Klassen
    private Animations animations;
    private ProgressBarManager progressBarManager;
    private DecideTurn decideTurn;
    private Random random;
    private LevelManager levelManager;
    private Hero hero;
    private ShopController shopController;
    private TileMapController tileMapController;

    // Variablen zur Verwaltung von Münzen, Eisen und Erfahrungspunkten
    private int totalCoins = 0, totalIron = 0;
    private int sessionCoins = 0, sessionIron = 0, xpEarned = 0;

    /**
     * Initialisiert den FightController und legt den anfänglichen Spielstatus fest.
     */
    @FXML
    public void initialize() {
        random = new Random();
        decideTurn = new DecideTurn();
        hero = StartGame.hero; // Verweise auf das globale Hero-Objekt
        progressBarManager = new ProgressBarManager(heroHealthBar, monsterHealthBar, heroArmorBar, monsterArmorBar);
        levelManager = new LevelManager(hero, progressBarManager);
        animations = new Animations();
        animations.heroImage = heroImage;
        animations.orcAnimation(heroImage);
        animations.monsterImage = monsterImage;
        animations.rogueAnimation();

        ivAttack.setImage(new Image(getClass().getResource("/Bilder/Buttons/Button_Red_3Slides.png").toExternalForm()));
        ivHeal.setImage(new Image(getClass().getResource("/Bilder/Buttons/Button_Blue_3Slides.png").toExternalForm()));

        ivAttack.setOnMouseClicked(event -> attackAction());
        ivHeal.setOnMouseClicked(event -> healAction());
        updateArmorButton.setOnMouseClicked(event -> updateArmor());

        shopController = new ShopController();
        shopController.setHero(hero);

        setNames("Spieler", "Monster");
        updateWeaponImages();
        addBattleLogMessage("");  // Initialisiere mit leerem Log
        updateCoinsEarnedLabel();
        updateIronLabel();
        updateXpLabel();
        startGame();

        // Setze die Aktion für die Taste "G", um das Ausweichen zu ermöglichen
        Platform.runLater(() -> battleLogTextArea.getScene().setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("G")) dodgeAction();
        }));
    }

    /**
     * Setzt das Hero-Objekt für den Controller.
     *
     * @param hero Das zu setzende Hero-Objekt.
     */
    public void setHero(Hero hero) {
        this.hero = hero;
        updateHeroStats();
    }

    /**
     * Setzt den Rüstungswert für den Helden.
     *
     * @param armor Der zu setzende Rüstungswert.
     */
    public void setHeroArmor(int armor) {
        hero.setArmor(armor);
    }

    /**
     * Aktualisiert die Rüstung des Helden auf die beste verfügbare Rüstung.
     */
    private void updateArmor() {
        equipBestArmor();
        addBattleLogMessage("Rüstung wurde aktualisiert.");
        if (hero.getEquippedArmor() != null) {
            hero.getEquippedArmor().resetDefense();
            hero.setArmor(hero.getEquippedArmor().getDefense());
            progressBarManager.setHeroArmor(hero.getEquippedArmor().getDefense());
        }
        progressBarManager.setHeroHasArmor(hero.getEquippedArmor() != null);
    }

    /**
     * Rüstet die beste verfügbare Rüstung aus dem Inventar aus.
     */
    private void equipBestArmor() {
        Armor bestArmor = Inventory.getBestArmor();
        if (bestArmor != null) {
            hero.equipArmor(bestArmor);
            progressBarManager.buyArmor(true, bestArmor);
            addBattleLogMessage("Beste Rüstung ausgerüstet: " + bestArmor.getName());
        } else {
            hero.setArmor(0);
            progressBarManager.buyArmor(true, null);
        }
    }

    /**
     * Aktualisiert die Anzeige der Heldenstatistiken.
     */
    public void updateHeroStats() {
        heroHealthBar.setProgress((double) hero.getHealth() / 100.00);
        heroArmorBar.setProgress((double) hero.getArmor() / hero.getEquippedArmor().getDefense());
        lHeroName.setText(hero.getName());
    }

    /**
     * Führt die Ausweichaktion für den Helden aus.
     */
    private void dodgeAction() {
        if (!decideTurn.isPlayerTurn()) return;
        if (random.nextDouble() < 0.1) {
            addBattleLogMessage("Du bist dem Angriff ausgewichen!");
            decideTurn.nextTurn();
            decideTurn.nextTurn(); // Überspringt den nächsten Zug des Monsters
        } else {
            addBattleLogMessage("Ausweichversuch fehlgeschlagen.");
            decideTurn.nextTurn();
            monsterAttack();
        }
    }

    /**
     * Fügt dem Kampflog eine Nachricht hinzu.
     *
     * @param message Die hinzuzufügende Nachricht.
     */
    private void addBattleLogMessage(String message) {
        battleLogTextArea.appendText(message + "\n");
    }

    /**
     * Setzt die Namen für den Helden und das Monster.
     *
     * @param heroName    Der Name des Helden.
     * @param monsterName Der Name des Monsters.
     */
    public void setNames(String heroName, String monsterName) {
        lHeroName.setText(heroName);
        lMonsterName.setText(monsterName);
    }

    /**
     * Aktualisiert die Waffenbilder für das Monster.
     */
    private void updateWeaponImages() {
        monsterWeaponImage.setImage(new Image(getClass().getResource(levelManager.getCurrentMonster().getWeaponImage()).toExternalForm()));
    }

    /**
     * Aktualisiert das Waffenbild des Helden.
     *
     * @param imagePath Der Pfad zum Waffenbild des Helden.
     */
    public void updateHeroWeaponImage(String imagePath) {
        try {
            Image image = new Image(getClass().getResource(imagePath).toExternalForm());
            heroWeaponImage.setImage(image);
            System.out.println("Bild erfolgreich geladen: " + imagePath);
        } catch (Exception e) {
            System.err.println("Fehler beim Laden des Bildes: " + imagePath);
            e.printStackTrace();
        }
    }

    /**
     * Aktualisiert das Eisen-Label in der UI.
     */
    private void updateIronLabel() {
        ironLabel.setText("Iron: " + sessionIron);
    }

    /**
     * Aktualisiert das XP-Label in der UI.
     */
    private void updateXpLabel() {
        xpLabel.setText("XP: " + xpEarned);
    }

    /**
     * Startet das Spiel und bestimmt, welcher Spieler beginnt.
     */
    private void startGame() {
        showAlert("Spielbeginn", decideTurn.isPlayerTurn() ? "Der Spieler beginnt!" : "Das Monster beginnt!");
        if (!decideTurn.isPlayerTurn()) monsterAttack();
    }

    /**
     * Setzt das Monster für eine neue Begegnung zurück.
     */
    private void resetMonster() {
        progressBarManager.setEnemyHealth(100);
        progressBarManager.setEnemyArmor(30);
        setNames(hero.getName(), "Neues Monster");
        updateWeaponImages();
        addBattleLogMessage("Ein neues Monster erscheint!");
    }


    /**
     * Diese Methode führt die Angriffsaktion des Helden aus.
     * Wenn es nicht der Spielerzug ist, kehrt sie sofort zurück.
     */
    private void attackAction() {
        if (!decideTurn.isPlayerTurn()) return;
        Weapon heroWeapon = hero.getWeapon();
        int baseDamage = 10; // Anfangsschaden zwischen 10 und 15
        int maxDamage = 15; // Maximalschaden

        if (heroWeapon != null) {
            // Jede 100 Coins erhöhen den Schaden um 1,5%
            double priceMultiplier = 1 + (heroWeapon.getPrice() / 100) * 0.015;
            baseDamage = (int) Math.round(baseDamage * priceMultiplier);
            maxDamage = (int) Math.round(maxDamage * priceMultiplier);
        }

        // Zufälliger Schaden basierend auf dem skalierten Bereich
        int damage = new Random().nextInt(maxDamage - baseDamage + 1) + baseDamage;
        progressBarManager.dealDamageToEnemy(damage);
        addBattleLogMessage("Du hast den Gegner angegriffen! Schaden: " + damage + ".");
        if (progressBarManager.getEnemyHealth() == 0) {
            addBattleLogMessage("Der Gegner wurde besiegt!");
            int earnedCoins = (new Random().nextInt(5) + 1) * levelManager.getCurrentLevel();
            int earnedIron = new Random().nextDouble() <= levelManager.getIronChance(levelManager.getCurrentLevel()) ? 1 : 0;
            xpEarned += (new Random().nextInt(6) + 3) * levelManager.getCurrentLevel();
            hero.addCoins(earnedCoins);
            hero.addIron(earnedIron);
            sessionCoins += earnedCoins;
            updateCoinsEarnedLabel();
            updateIronLabel();
            updateXpLabel();
            updateArmor();
            resetMonster();
            levelManager.nextLevel();
            updateWeaponImages();
            return;
        }
        decideTurn.nextTurn();
        monsterAttack();
    }

    /**
     * Aktualisiert das Label der verdienten Münzen in dieser Sitzung.
     */
    private void updateCoinsEarnedLabel() {
        coinsEarnedLabel.setText("In dieser Sitzung verdiente Münzen: " + sessionCoins);
    }

    /**
     * Diese Methode führt die Heilaktion des Helden aus.
     * Wenn es nicht der Spielerzug ist, kehrt sie sofort zurück.
     */
    private void healAction() {
        if (!decideTurn.isPlayerTurn()) return;
        progressBarManager.setHeroHealth(Math.min(progressBarManager.getHeroHealth() + 10, 100));
        addBattleLogMessage("Du wurdest geheilt!");
        decideTurn.nextTurn();
        monsterAttack();
    }

    /**
     * Diese Methode führt die Angriffsaktion des Monsters aus.
     */
    private void monsterAttack() {
        int damage = random.nextInt(11) + 10;
        if (hero.getArmor() > 0) progressBarManager.dealDamageToHeroArmor(damage, hero);
        else progressBarManager.dealDamageToHero(damage);
        addBattleLogMessage("Das Monster hat dich angegriffen! Schaden: " + damage);
        if (progressBarManager.getHeroHealth() == 0) {
            addBattleLogMessage("Du wurdest besiegt und hast Level " + levelManager.getCurrentLevel() + " erreicht");
            levelManager.resetLevel();
            return;
        }
        decideTurn.nextTurn();
    }

    /**
     * Zeigt einen Informations-Alert an.
     *
     * @param title   Der Titel des Alerts.
     * @param message Die Nachricht des Alerts.
     */
    private void showAlert(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setSessionCoins(int sessionCoins) {
        this.sessionCoins = sessionCoins;
    }

    public void setSessionIron(int sessionIron) {
        this.sessionIron = sessionIron;
    }

    public int getTotalCoins() {
        return totalCoins;
    }

    public int getTotalIron() {
        return totalIron;
    }

    /**
     * Behandelt die Aktion des Zurück-Buttons.
     */
    @FXML
    private void handleBackButton() {
        endFight();
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

    /**
     * Beendet den Kampf und setzt die Session-Werte zurück.
     */
    public void endFight() {
        totalCoins += sessionCoins;
        totalIron += sessionIron;
        sessionCoins = 0;
        sessionIron = 0;
    }
}

