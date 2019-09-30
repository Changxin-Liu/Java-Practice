package pacman.ghost;

import pacman.game.Entity;
import pacman.game.PacmanGame;
import pacman.util.Direction;
import pacman.util.Position;

import java.util.*;

/**
 * An Abstract Ghost which is a game entity.
 *
 * @ass1
 */
public abstract class Ghost extends Entity {

    // whether the ghost is dead
    private boolean dead;
    // current phase of this ghost
    private Phase phase;
    // duration of current phase
    private int phaseDuration;

    /**
     * Creates a ghost which is alive and starts in the SCATTER phase
     * with a duration of Phase.SCATTER.duration(). This ghost also has
     * a default position of (0, 0) and a default direction of facing
     * up.
     *
     * @ass1
     */
    public Ghost() {
        super();
        dead = false;
        phase = Phase.SCATTER;
        phaseDuration = Phase.SCATTER.getDuration();
    }

    /**
     * Sets the Ghost Phase and its duration overriding any current
     * phase information.
     * <p>
     * if Phase is null then no changes are made. If the duration is
     * less than zero then the duration is set to 0.
     *
     * @param newPhase to set the ghost to.
     * @param duration of ticks for the phase to last for.
     * @ass1
     */
    public void setPhase(Phase newPhase, int duration) {
        if (newPhase != null) {
            phase = newPhase;
            phaseDuration = Integer.max(0, duration);
        }
    }

    /**
     * Get the phase that the ghost currently is in.
     *
     * @return the set phase.
     * @ass1
     */
    public Phase getPhase() {
        return phase;
    }

    /*
     * NextPhase decreases our phase duration and moves us to the
     * next phase if it is 0.
     *
     * - CHASE goes to SCATTER.
     * - FRIGHTENED && SCATTER go to CHASE.
     */
    private void nextPhase() {
        phaseDuration = Integer.max(0, phaseDuration - 1);
        if (phaseDuration == 0) {
            switch (getPhase()) {
                case CHASE:
                    setPhase(Phase.SCATTER, Phase.SCATTER.getDuration());
                    break;
                case FRIGHTENED:
                case SCATTER:
                    setPhase(Phase.CHASE, Phase.CHASE.getDuration());
                    break;
            }
        }
    }

    /**
     * Gets the phase info of the ghost.
     *
     * @return the phase and duration formatted as such: "PHASE:DURATION".
     * @ass1
     */
    public String phaseInfo() {
        return String.format("%s:%d", phase, phaseDuration);
    }

    /**
     * Gets the ghosts colour.
     *
     * @return hex version of the ghosts colour, e.g. #FFFFFF for white.
     * @ass1
     */
    public abstract String getColour();

    /**
     * Gets the ghosts type.
     *
     * @return this ghosts type.
     * @ass1
     */
    public abstract GhostType getType();

    /**
     * Kills this ghost by setting its status to isDead.
     *
     * @ass1
     */
    public void kill() {
        this.dead = true;
    }

    /**
     * Checks if this ghost is dead.
     *
     * @return true if dead, false otherwise.
     * @ass1
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * Resets the ghost back to an initial state where:
     *
     * <ul>
     * <li>It is alive</li>
     * <li>With a Phase of SCATTER with duration SCATTER.getDuration()</li>
     * <li>Facing in the Direction.UP</li>
     * <li>With a Position of ( 0, 0 )</li>
     * </ul>
     *
     * @ass1
     */
    public void reset() {
        dead = false;
        this.phase = Phase.SCATTER;
        this.phaseDuration = Phase.SCATTER.getDuration();
        this.setDirection(Direction.UP);
        this.setPosition(new Position(0, 0));
    }

    /**
     * Gets the target block that we should be heading towards when in the
     * chase phase.
     *
     * @param game the game to load
     * @return the ghosts target position
     */
    public abstract Position chaseTarget(PacmanGame game);

    /**
     * Gets the home block that we should be heading towards when in the
     * scatter phase.
     *
     * @param game the game to load
     * @return the ghosts home position.
     */
    public abstract Position home(PacmanGame game);

    /**
     * Move advances the ghost in a direction by one point on the board.
     *
     * @param game the game to load
     */
    @Override
    public void move(PacmanGame game) {
        nextPhase();

        Position targetPosition = getTargetPosition(game);
        Direction direction = getDirection(game, targetPosition);

        this.setDirection(direction);
        this.setPosition(this.getPosition().add(direction.offset()));
    }

    /**
     * Checks if another object instance is equal to this Ghost. Ghosts are
     * equal if they have the same alive/dead status, phase duration ,current
     * phase, direction and position.
     *
     * @param o the object to compare with
     * @return true if two ghosts are equal. Otherwise, false.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ghost)) {
            return false;
        }
        return (this.isDead() == ((Ghost) o).isDead())
                && (this.phaseDuration == ((Ghost) o).phaseDuration)
                && (this.getPhase() == ((Ghost) o).getPhase())
                && (this.getDirection() == ((Ghost) o).getDirection())
                && (this.getPosition().getX() ==
                ((Ghost) o).getPosition().getX())
                && (this.getPosition().getY() ==
                ((Ghost) o).getPosition().getY());
    }

    /**
     * Returns a hashcode of a ghost
     *
     * @return a ghost's hashcode
     */
    @Override
    public int hashCode() {
        int dead = 0;

        if (this.isDead()) {
            dead = 1;
        }

        return 3 * dead + 5 * this.phaseDuration +
                7 * this.getPhase().hashCode() + 11 * getDirection().hashCode()
                + 13 * getPosition().hashCode();
    }

    /**
     * Represents this Ghost in a comma-seperated string format.
     * Format is: "x,y,DIRECTION,PHASE:phaseDuration".
     *
     * @return a string representing a ghost
     */
    @Override
    public String toString() {
        return String.format("%d,%d,%s,%s",
                this.getPosition().getX(),
                this.getPosition().getY(),
                this.getDirection(),
                this.getPhase() + ":" + this.phaseDuration);
    }

    /*
     * Returns the target position.
     */
    private Position getTargetPosition(PacmanGame game) {

        Position targetPosition;

        if (this.getPhase() == Phase.CHASE) {
            targetPosition = chaseTarget(game);
        } else if (this.getPhase() == Phase.SCATTER) {
            targetPosition = home(game);
        } else {
            targetPosition = new Position(this.getPosition().getX() * 24 %
                    (2 * game.getBoard().getWidth() -
                    game.getBoard().getWidth()),
                    this.getPosition().getY() * 36 %
                    (2 * game.getBoard().getHeight()) -
                    game.getBoard().getHeight());
        }
        return targetPosition;
    }

    /*
     * Returns the direction according to the target position.
     */
    private Direction getDirection(PacmanGame game, Position targetPosition) {

        List<Direction> list = new ArrayList<>();
        list.add(Direction.UP);
        list.add(Direction.LEFT);
        list.add(Direction.DOWN);
        list.add(Direction.RIGHT);

        double minDistance = Integer.MAX_VALUE;
        Direction finalDirection = getDirection();
        Direction oppositeDirection = this.getDirection().opposite();

        for (Direction direction : list) {
            if (game.getBoard().getEntry(
                    this.getPosition().add(direction.offset())).getPathable()
                    && this.getPosition().add(direction.offset()).getX() <
                    game.getBoard().getWidth()
                    && this.getPosition().add(direction.offset()).getY() <
                    game.getBoard().getHeight()) {
                if (direction != oppositeDirection) {
                    double distance = getPosition().add(
                            direction.offset()).distance(targetPosition);
                    if (minDistance > distance) {
                        minDistance = distance;
                        finalDirection = direction;
                    }
                }
            }
        }

        return finalDirection;
    }

}
