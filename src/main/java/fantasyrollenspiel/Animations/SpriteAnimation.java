package fantasyrollenspiel.Animations;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition {
    private final ImageView imageView;  // Die ImageView-Komponente, in der die Animation angezeigt wird
    private final int count;  // Anzahl der Einzelbilder (Frames) in der Animation
    private final int columns;  // Anzahl der Spalten in der Sprite-Sheet
    private final int offsetX;  // Horizontale Verschiebung im Sprite-Sheet
    private final int offsetY;  // Vertikale Verschiebung im Sprite-Sheet
    private final int width;  // Breite eines Einzelbildes
    private final int height;  // Höhe eines Einzelbildes

    public SpriteAnimation(
            ImageView imageView,
            Duration duration,
            int count, int columns,
            int offsetX, int offsetY,
            int width, int height,
            String spritePath) {
        this.imageView = imageView;
        this.count = count;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        setCycleDuration(duration);  // Die Dauer der gesamten Animation festlegen
        setInterpolator(Interpolator.LINEAR);  // Den Interpolator für die Animation festlegen (hier linear)

        Image spriteSheet = new Image(spritePath);  // Das Sprite-Sheet aus dem angegebenen Pfad laden
        this.imageView.setImage(spriteSheet);  // Das geladene Sprite-Sheet der ImageView zuweisen
    }

    @Override
    protected void interpolate(double k) { // K = fortschritt der animation
        final int index = Math.min((int) Math.floor(k * count), count - 1);  // Den aktuellen Frame-Index berechnen
        final int x = (index % columns) * width + offsetX;  // Die x-Koordinate des aktuellen Frames berechnen
        final int y = (index / columns) * height + offsetY;  // Die y-Koordinate des aktuellen Frames berechnen
        imageView.setViewport(new javafx.geometry.Rectangle2D(x, y, width, height));  // Den sichtbaren Bereich der ImageView aktualisieren
    }
}
