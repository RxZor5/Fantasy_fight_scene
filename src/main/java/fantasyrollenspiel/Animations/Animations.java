package fantasyrollenspiel.Animations;

import javafx.animation.Animation;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Animations {

    @FXML
    public ImageView heroImage;
    @FXML
    public ImageView monsterImage;

    final String spritePathOrc = getClass().getResource("/Bilder/Idle-Sheet.png").toExternalForm();

    final String spritePathSke = getClass().getResource("/Bilder/Idle-Sheet-ske.png").toExternalForm();

    public void orcAnimation(){
        Animation orc = new SpriteAnimation(
                heroImage,
                Duration.millis(500),
                4, 4,
                0, 0,
                32, 32,
                spritePathOrc
        );

        orc.setCycleCount(Animation.INDEFINITE);
        orc.play();
    }

    public void skelettonAnimation(){

        Animation skeletton = new SpriteAnimation(
                monsterImage,
                Duration.millis(500),
                4, 4,
                0, 0,
                32, 32,
                spritePathSke
        );

        skeletton.setCycleCount(Animation.INDEFINITE);
        skeletton.play();
    }

}
