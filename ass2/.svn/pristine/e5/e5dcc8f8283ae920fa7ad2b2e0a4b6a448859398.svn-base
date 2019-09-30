package pacman.ghost;

import pacman.game.PacmanGame;
import pacman.util.Direction;
import pacman.util.Position;

/**
 * Inky is a ghost that likes to tail close behind the hunter.
 * When not chasing the hunter down, Inky likes to hang out in
 * the bottom right corner of the board in a blue glow.
 *
 * @ass1
 */
public class Inky extends Ghost {

    /**
     * Get Inky's colour.
     *
     * @return "#7aa6da"
     * @ass1
     */
    @Override
    public String getColour() {
        return "#7aa6da";
    }

    /**
     * Get Inky's type/name.
     *
     * @return INKY;
     * @ass1
     */
    @Override
    public GhostType getType() {
        return GhostType.INKY;
    }


    /**
     * Inky will chase 2 blocks behind the hunter's current direction.
     *
     * @param game the game to load
     * @return the position 2 blocks behind hunter position.
     */
    public Position chaseTarget(PacmanGame game) {
        Direction oppositeDirection =
                game.getHunter().getDirection().opposite();
        Position behindPosition =
                oppositeDirection.offset().add(oppositeDirection.offset());

        return game.getHunter().getPosition().add(behindPosition);
    }

    /**
     * Inky's home position is one block outside of the bottom right of the
     * game board. Where the top left position of the board is (0, 0).
     *
     * @param game the game to load
     * @return Inky's home position, which is one diagonal block out from the
     * bottom right corner.
     */
    public Position home(PacmanGame game) {

        return new Position(game.getBoard().getWidth(),
                game.getBoard().getHeight());
    }
}
