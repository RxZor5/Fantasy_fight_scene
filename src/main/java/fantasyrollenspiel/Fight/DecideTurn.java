package fantasyrollenspiel.Fight;

import java.util.Random;

/**
 * Die Klasse entscheidet, wessen Zug es ist (Spieler oder Gegner).
 */
public class DecideTurn {
    private Random random;
    private boolean isPlayerTurn;

    /**
     * Konstruktor, der zufällig entscheidet, ob der Spieler beginnt.
     */
    public DecideTurn() {
        random = new Random();
        isPlayerTurn = random.nextBoolean();
    }

    /**
     * Überprüft, ob es der Zug des Spielers ist.
     *
     * @return true, wenn es der Zug des Spielers ist, sonst false.
     */
    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }

    /**
     * Wechselt zum nächsten Zug.
     */
    public void nextTurn() {
        isPlayerTurn = !isPlayerTurn;
    }
}
