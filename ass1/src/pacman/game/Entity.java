package pacman.game;

import pacman.util.Direction;
import pacman.util.Position;

/**
 * The basic class representing the entities in the game board
 */
public abstract class Entity implements Moveable{

    // the location of an entity
    private Position position;

    // the direction that an entity is facing
    private Direction direction;

    /**
     * Constructs an entity with default settings:
     * position (0,0) and direction Direction.UP
     */
    public Entity(){
        position = new Position(0,0);
        direction = Direction.UP;
    }

    /**
     * Constructs an entity with a given position and a given direction.
     * If the given position is null, the position will be the default
     * position (0,0). If the given direction is null, the direction will
     * be set Direction.UP
     *
     * @param position the given position
     * @param direction the given direction
     */
    public Entity(Position position,
                  Direction direction){
        if (position == null){
            this.position = new Position(0,0);
        }else {
            this.position = position;
        }
        if (direction == null){
            this.direction = Direction.UP;
        }else {
            this.direction = direction;
        }
    }

    /**
     * Returns the position of an entity
     *
     * @return an entity's position
     */
    public Position getPosition(){
        return position;
    }

    /**
     * Sets the position of an entity and if the given position is null,
     * the position will not change
     *
     * @param position the given position for an entity to change
     */
    public void setPosition(Position position){
        if (position != null){
            this.position = position;
        }
    }

    /**
     * Sets the direction for an entity and if the given deriction is
     * null, the direction will not change
     *
     * @param direction the given direction for an entity to change
     */
    public void setDirection(Direction direction){
        if (direction != null){
            this.direction = direction;
        }
    }

    /**
     * Returns the direction of an entity
     *
     * @return an entity's direction
     */
    public Direction getDirection(){
        return direction;
    };

}
