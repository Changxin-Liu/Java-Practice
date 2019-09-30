package pacman.game;

import pacman.board.BoardItem;
import pacman.board.PacmanBoard;
import pacman.ghost.*;
import pacman.hunter.Hunter;
import pacman.score.ScoreBoard;
import pacman.util.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to store the game's state and to act as a model for the entire game.
 */
public class PacmanGame {

    // The title of the game
    private String title;
    // The author of the game
    private String author;
    // The hunter in the game
    private Hunter hunter;
    // The pacmanboard in the game
    private PacmanBoard board;
    // The tick in the game
    private int tick;
    // The level in the game
    private int level;
    // The lives remaining in the game
    private int lives;
    // The scoreboard in the game
    private ScoreBoard scoreBoard;
    // The list to store the ghosts in the game
    private List<Ghost> ghostList;


    /**
     * Constructs a pacmangame
     *
     * @param title  the title of the game
     * @param author the author of the game
     * @param hunter the hunter in the game
     * @param board  the board to be used in the game
     *
     * @requires title != null and author != null and hunter != null and
     * board != null and board contains a spawn point for Ghosts and for the
     * hunter.
     */
    public PacmanGame(String title,
                      String author,
                      Hunter hunter,
                      PacmanBoard board) {

        this.title = title;
        this.author = author;
        this.hunter = hunter;
        this.board = new PacmanBoard(board);

        ghostList = new ArrayList<>();
        ghostList.add(new Blinky());
        ghostList.add(new Inky());
        ghostList.add(new Pinky());
        ghostList.add(new Clyde());

        ghostList.get(0).setPosition(board.getGhostSpawn());
        ghostList.get(1).setPosition(board.getGhostSpawn());
        ghostList.get(2).setPosition(board.getGhostSpawn());
        ghostList.get(3).setPosition(board.getGhostSpawn());

        this.tick = 0;
        this.level = 0;
        this.lives = 4;
        scoreBoard = new ScoreBoard();

    }

    /**
     * Gets the title of the game
     *
     * @return the game title
     *
     * @ensures result != null
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Gets the author of the game
     *
     * @return the game author
     *
     * @ensures result != null
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Gets the board used in the game
     *
     * @return a pacmanboard used int the game
     */
    public PacmanBoard getBoard() {
        return this.board;
    }

    /**
     * Gets the tick in the game
     *
     * @return the game tick
     */
    public int getTick() {
        return this.tick;
    }

    /**
     * Gets the scoreboard in the game
     *
     * @return the scoreboard used in the game
     */
    public ScoreBoard getScores() {
        return this.scoreBoard;
    }

    /**
     * Gets the level in the game
     *
     * @return the game leve
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Sets the game level
     *
     * @param level A number representing the level
     *
     * @ensures newLevel = max(0, givenLevel)
     */
    public void setLevel(int level) {
        if (level >= 0) {
            this.level = level;
        }
    }

    /**
     * Gets the remaining lives in the game
     *
     * @return the number of remaining lives
     */
    public int getLives() {
        return this.lives;
    }

    /**
     * Sets the remaining lives in the game
     *
     * @param lives the number to set
     *
     * @ensures newLives = max(0, givenLives)
     */
    public void setLives(int lives) {
        if (lives >= 0) {
            this.lives = lives;
        }
    }

    /**
     * Gets the hunter in the game
     *
     * @return the hunter in the game
     */
    public Hunter getHunter() {
        return this.hunter;
    }

    /**
     * Gets a copy of the list which containing the ghosts in the game
     *
     * @return A list copy containing the ghosts in the game
     */
    public List<Ghost> getGhosts() {

        List<Ghost> list = new ArrayList<>();
        list.add(ghostList.get(0));
        list.add(ghostList.get(1));
        list.add(ghostList.get(2));
        list.add(ghostList.get(3));

        return list;
    }

    /**
     * Tick If we do not have any lives (getLives() == 0) then do nothing.
     * Otherwise, we do the following things:
     * 1. Move the hunter.
     * 2. Hit all ghosts in the game
     * 3. For the alive ghosts, move them
     * 4. Hit all ghosts
     * 5. For dead ghosts, reset them on the ghost spawn position.
     * Add 200 points to the current score.
     * 6. If the hunter is dead, decrease the lives by 1 and respawn it on the
     * pacman spawn position.
     * 7. If the board is empty, increase the game level. Reset the tick, the
     * board and all entities.
     * 8. If we did not increase the level then increase the tick value.
     */
    public void tick() {
        if (this.getLives() != 0) {
            getHunter().move(this);

            for (Ghost ghost : getGhosts()) {
                getHunter().hit(ghost);
            }

            for (Ghost ghost : getGhosts()) {
                if (!ghost.isDead()) {
                    if (getTick() % 2 == 0) {
                        ghost.move(this);
                    }
                }
            }

            for (Ghost ghost : getGhosts()) {
                getHunter().hit(ghost);
            }

            for (Ghost ghost : getGhosts()) {
                if (ghost.isDead()) {
                    ghost.reset();
                    ghost.setPosition(getBoard().getGhostSpawn());
                    getScores().increaseScore(200);
                }
            }

            if (getHunter().isDead()) {
                this.lives -= 1;
                resetEntities();
            }

            if (isBoardEmpty()) {
                level += 1;
                tick = 0;
                resetEntities();
            } else {
                tick += 1;
            }
        }
    }

    /**
     * Resets the game
     */
    public void reset() {
        this.lives = 4;
        this.level = 0;
        scoreBoard.reset();
        board.reset();

        resetEntities();

        this.tick = 0;
    }

    /**
     * Sets the phase of all ghosts Phase.FRIGHTENED
     */
    public void setGhostsFrightened() {
        for (int i = 0; i < ghostList.size(); i++) {
            ghostList.get(i).setPhase(
                    Phase.FRIGHTENED, Phase.FRIGHTENED.getDuration());
        }
    }

    /*
     * Decides if the board is empty.
     * @return true if it is empty. Otherwise, false
     */
    private boolean isBoardEmpty() {
        for (int x = 0; x < getBoard().getWidth(); x++) {
            for (int y = 0; y < getBoard().getHeight(); y++) {
                BoardItem item = getBoard().getEntry(new Position(x, y));
                if (item == BoardItem.DOT
                        || item == BoardItem.BIG_DOT) {
                    return false;
                }
            }
        }

        return true;
    }

    /*
     * Resets all entities
     */
    private void resetEntities() {
        getHunter().reset();
        getHunter().setPosition(getBoard().getPacmanSpawn());
        for (Ghost ghost : getGhosts()) {
            ghost.reset();
            ghost.setPosition(getBoard().getGhostSpawn());
        }
    }
}
