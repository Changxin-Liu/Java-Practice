package pacman.game;

import pacman.util.Direction;
import pacman.util.Position;

/**
 * Entity
 *
 * A entity is the animated objects in the game that can traverse
 * the game board and interact with other entities.
 * @ass1
 */
public abstract class Entity implements Moveable {

    // position of the entity
    private Position position;
    // direction the entity is facing
    private Direction direction;

    /**
     * Creates an entity that is at position (0, 0) and is facing UP.
     * @ass1
     */
    public Entity() {
        position = new Position(0, 0);
        direction = Direction.UP;
    }

    /**
     * Creates an entity that is at the given position facing in the
     * given direction.
     *
     * If the position is null then the position will be the same
     * as the default position ( 0, 0 ). If the direction is null
     * then the direction will be the same as the default ( UP ).
     *
     * @param position to be set to.
     * @param direction to be facing.
     * @ass1
     */
    public Entity(Position position, Direction direction) {
        this();

        if (position != null) {
            this.position = position;
        }

        if (direction != null) {
            this.direction = direction;
        }
    }

    /**
     * {@inheritDoc}
     * @ass1
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     * @ass1
     */
    public void setPosition(Position position) {
        if (position != null) {
            this.position = position;
        }
    }

    /**
     * {@inheritDoc}
     * @ass1
     */
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * {@inheritDoc}
     * @ass1
     */
    public void setDirection(Direction direction) {
        if (direction != null) {
            this.direction = direction;
        }
    }
}
