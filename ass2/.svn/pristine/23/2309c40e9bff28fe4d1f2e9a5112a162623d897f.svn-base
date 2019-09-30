package pacman.hunter;


import pacman.game.PacmanGame;

/**
 * A Speedy hunter that has a special ability that allows the hunter
 * to travel twice as fast.
 *
 * @ass1
 */
public class Speedy extends Hunter {

    /**
     * Creates a Speedy Hunter with its special ability.
     * <p>
     * see {@link Hunter#Hunter()}
     *
     * @ass1
     */
    public Speedy() {
        super();
    }

    /**
     * Creates a Speedy Hunter by copying the internal state of
     * another hunter.
     * <p>
     * see {@link pacman.hunter.Hunter#Hunter(Hunter)}
     *
     * @param original hunter to copy from
     * @ass1
     */
    public Speedy(Hunter original) {
        super(original);
    }

    /**
     * Moves a speedy in a pacman game. If speedy's special is activated,
     * move it twice. Otherwise, move it as a normal hunter.
     *
     * @param game the game to load
     */
    public void move(PacmanGame game) {
        Speedy speedy = (Speedy) game.getHunter();

        if (!speedy.isSpecialActive()) {
            super.move(game);
        } else {
            super.move(game);
            super.move(game);
        }
    }

    /**
     * Represents this Hungry in a comma-seperated string format.
     * Format is: "x,y,DIRECTION,specialDuration,SPEEDY".
     *
     * @return a string representing a speedy.
     */
    @Override
    public String toString() {
        return String.format("%d,%d,%s,%d,%s",
                this.getPosition().getX(),
                this.getPosition().getY(),
                this.getDirection(),
                this.getSpecialDurationRemaining(),
                "SPEEDY");
    }
}
