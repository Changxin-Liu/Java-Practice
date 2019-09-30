package pacman.ghost;

import pacman.game.Entity;
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
     *
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
     * @return the phase and duration formatted as such: "PHASE:DURATION".
     * @ass1
     */
    public String phaseInfo() {
        return String.format("%s:%d", phase, phaseDuration);
    }

    /**
     * Gets the ghosts colour.
     * @return hex version of the ghosts colour, e.g. #FFFFFF for white.
     * @ass1
     */
    public abstract String getColour();

    /**
     * Gets the ghosts type.
     * @return this ghosts type.
     * @ass1
     */
    public abstract GhostType getType();

    /**
     * Kills this ghost by setting its status to isDead.
     * @ass1
     */
    public void kill() {
        this.dead = true;
    }

    /**
     * Checks if this ghost is dead.
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
     *     <li>It is alive</li>
     *     <li>With a Phase of SCATTER with duration SCATTER.getDuration()</li>
     *     <li>Facing in the Direction.UP</li>
     *     <li>With a Position of ( 0, 0 )</li>
     * </ul>
     * @ass1
     */
    public void reset() {
        dead = false;
        this.phase = Phase.SCATTER;
        this.phaseDuration = Phase.SCATTER.getDuration();
        this.setDirection(Direction.UP);
        this.setPosition(new Position(0, 0));
    }
}
