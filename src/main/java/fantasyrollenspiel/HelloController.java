package fantasyrollenspiel;

import fantasyrollenspiel.Animations.Animations;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class HelloController {

    @FXML
    private ImageView heroImage;

    @FXML
    public ImageView monsterImage;

    private Animations animations;



    @FXML
    public void initialize() {


        // Erstelle eine Instanz der Animations-Klasse
        animations = new Animations();

        // Setze das heroImage der Animations-Klasse
        animations.heroImage = heroImage;

        // Starte die Orc-Animation
        animations.orcAnimation();

        //Rogue
        animations = new Animations();

        animations.monsterImage = monsterImage;

        animations.rogueAnimation();
        // Skelett Ende

    }
}
