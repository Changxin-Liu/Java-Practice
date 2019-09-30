package pacman.ghost;

import pacman.game.PacmanGame;
import pacman.util.Position;

/**
 * Clyde is a ghost that behaves in a very scared manner
 * when close to a hunter. When not chasing the hunter down, clyde
 * likes to hang out in the bottom left corner of the board in a
 * orange glow.
 *
 * @ass1
 */
public class Clyde extends Ghost {

    /**
     * Get Clydes colour.
     *
     * @return "#e78c45"
     * @ass1
     */
    @Override
    public String getColour() {
        return "#e78c45";
    }

    /**
     * Get Clydes type/name.
     *
     * @return CLYDE;
     * @ass1
     */
    @Override
    public GhostType getType() {
        return GhostType.CLYDE;
    }

    /**
     * Clyde will target the hunter if equal to or greater than a distance of 8
     * away from the hunter. Otherwise if closer than 8 it will target its home
     * position.
     *
     * @param game the game to load
     * @return home if less than 8 away from hunter, otherwise hunter position.
     */
    public Position chaseTarget(PacmanGame game) {
        if (this.getPosition().distance(game.getHunter().getPosition()) >= 8) {
            return game.getHunter().getPosition();
        }
        return home(game);
    }

    /**
     * Clyde's home position is one block outside of the bottom left of the
     * game board. Where the top left position of the board is (0, 0).
     *
     * @param game the game to load
     * @return One diagional block out from the bottom left corner.
     */
    public Position home(PacmanGame game) {

        int yCoordinate = game.getBoard().getHeight();
        return new Position(-1, yCoordinate);
    }
}
