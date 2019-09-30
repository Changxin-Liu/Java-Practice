package pacman.hunter;

/**
 * A Phil hunter that has no special ability.
 *
 * @ass1
 */
public class Phil extends Hunter {

    /**
     * Creates a Phil Hunter.
     * <p>
     * see {@link Hunter#Hunter()}
     *
     * @ass1
     */
    public Phil() {
        super();
    }

    /**
     * Creates a Phil hunter by copying the internal state of
     * another hunter.
     * <p>
     * see {@link pacman.hunter.Hunter#Hunter(Hunter)}
     *
     * @param original hunter to copy from
     * @ass1
     */
    public Phil(Hunter original) {
        super(original);
    }

    /**
     * Phil does not have a special.
     *
     * @return false
     * @ass1
     */
    @Override
    public boolean isSpecialActive() {
        return false;
    }

    /**
     * Represents this Hungry in a comma-seperated string format.
     * Format is: "x,y,DIRECTION,specialDuration,PHIL".
     *
     * @return a string representing Phil
     */
    @Override
    public String toString() {
        return String.format("%d,%d,%s,%d,%s",
                this.getPosition().getX(),
                this.getPosition().getY(),
                this.getDirection(),
                this.getSpecialDurationRemaining(),
                "PHIL");
    }
}