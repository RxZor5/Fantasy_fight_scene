package fantasyrollenspiel.Fight;

import java.util.Random;

public class DecideTurn {
    private Random random;
    private boolean isPlayerTurn;

    public DecideTurn() {
        random = new Random();
        isPlayerTurn = random.nextBoolean();
    }

    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }

    public void nextTurn() {
        isPlayerTurn = !isPlayerTurn;
    }
}
