package pacman.ghost;

import pacman.game.PacmanGame;
import pacman.util.Direction;
import pacman.util.Position;

/**
 * Pinky is a cunning ghost that tries to ambush the hunter.
 * When not chasing the hunter down, Pinky likes to hang out in
 * the top left corner of the board in a pink glow.
 *
 * @ass1
 */
public class Pinky extends Ghost {

    /**
     * Get Pinky colour.
     *
     * @return "#c397d8"
     * @ass1
     */
    @Override
    public String getColour() {
        return "#c397d8";
    }

    /**
     * Get Pinkys type/name.
     *
     * @return PINKY;
     * @ass1
     */
    @Override
    public GhostType getType() {
        return GhostType.PINKY;
    }

    /**
     * Pinky will chase 4 blocks in front of the hunter's current direction.
     *
     * @param game the game to load
     * @return the position 4 blocks in front of the position of hunter
     */
    public Position chaseTarget(PacmanGame game) {
        Direction currentDirection = game.getHunter().getDirection();
        int xCoordinate = currentDirection.offset().getX() * 4;
        int yCoordinate = currentDirection.offset().getY() * 4;
        return game.getHunter().getPosition().add(
                new Position(xCoordinate, yCoordinate));
    }

    /**
     * Pinky's home position is one block outside of the top left of the game
     * board. Where the top left position of the board is (0, 0).
     *
     * @param game the game to load
     * @return Pinky's home position, which is one diagional block out from the
     * top left corner.
     */
    public Position home(PacmanGame game) {
        return new Position(-1, -1);
    }
}