package pacman.game;

import pacman.util.Direction;
import pacman.util.Position;

/**
 * An object which is moveable will have a position and direction.
 * Also, the position and direction will be modified.
 */
public interface Moveable {

    /**
     * Sets one entity's position, if the position is null, do nothing.
     *
     * @param position the position to set
     */
    void setPosition(Position position);

    /**
     * Returns one entity's current position
     *
     * @return the current position of an entity
     */
    Position getPosition();

    /**
     * Sets one entity's direction, if the direction is null, do nothing.
     *
     * @param direction the direction to set
     */
    void setDirection(Direction direction);

    /**
     * Returns one entity's current direction
     *
     * @return the current direction of an entity
     */
    Direction getDirection();
}
