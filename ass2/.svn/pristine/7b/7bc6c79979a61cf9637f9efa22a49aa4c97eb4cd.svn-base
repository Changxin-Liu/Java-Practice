package pacman.game;

import org.junit.Before;
import org.junit.Test;
import pacman.board.BoardItem;
import pacman.board.PacmanBoard;
import pacman.ghost.*;
import pacman.hunter.Hunter;
import pacman.hunter.Speedy;
import pacman.util.Direction;
import pacman.util.Position;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.*;

public class GameWriterTest {

    private PacmanGame pacmanGame;

    @Before
    public void setUp() {
        String title = "GameWriterTest";
        String author = "Changxin Liu 45245008";

        PacmanBoard pacmanBoard = new PacmanBoard(12,7);
        pacmanBoard.setEntry(new Position(9,4), BoardItem.PACMAN_SPAWN);
        pacmanBoard.setEntry(new Position(4,2), BoardItem.GHOST_SPAWN);
        pacmanBoard.setEntry(new Position(7,5), BoardItem.BIG_DOT);

        Hunter hunter = new Speedy();
        hunter.setDirection(Direction.RIGHT);
        hunter.setPosition(new Position(2,2));
        hunter.activateSpecial(90);

        pacmanGame = new PacmanGame(title, author, hunter, pacmanBoard);
        pacmanGame.setLives(16);
        pacmanGame.setLevel(99);
        for (Ghost ghost : pacmanGame.getGhosts()) {
            if (ghost.getType() == GhostType.BLINKY) {
                ghost.setDirection(Direction.LEFT);
                ghost.setPhase(Phase.FRIGHTENED,23);
                ghost.setPosition(new Position(1,1));
            } else if (ghost.getType() == GhostType.INKY) {
                ghost.setDirection(Direction.DOWN);
                ghost.setPhase(Phase.CHASE,3);
                ghost.setPosition(new Position(1,2));
            } else if (ghost.getType() == GhostType.PINKY) {
                ghost.setDirection(Direction.UP);
                ghost.setPhase(Phase.CHASE,32);
                ghost.setPosition(new Position(7,1));
            } else if (ghost.getType() == GhostType.CLYDE) {
                ghost.setDirection(Direction.RIGHT);
                ghost.setPhase(Phase.SCATTER,2);
                ghost.setPosition(new Position(4,1));

            }
        }
        pacmanGame.getScores().increaseScore(199);
        pacmanGame.getScores().setScore("Amber",19);
        pacmanGame.getScores().setScore("Steven", 14);
        pacmanGame.getScores().setScore("Ben",49);
    }

    @Test
    public void write() throws IOException{
        StringWriter writer = new StringWriter();
        try {
            GameWriter.write(writer, pacmanGame);
        } catch (IOException e){
            throw new IOException();
        }

        assertEquals("[Board]" + System.lineSeparator() +
                "12,7" + System.lineSeparator() +
                "XXXXXXXXXXXX" + System.lineSeparator() +
                "X0000000000X" + System.lineSeparator() +
                "X000$000000X" + System.lineSeparator() +
                "X0000000000X" + System.lineSeparator() +
                "X00000000P0X" + System.lineSeparator() +
                "X000000B000X" + System.lineSeparator() +
                "XXXXXXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameWriterTest" + System.lineSeparator() +
                "author = Changxin Liu 45245008" + System.lineSeparator() +
                "lives = 16" + System.lineSeparator() +
                "level = 99" + System.lineSeparator() +
                "score = 199" + System.lineSeparator() +
                "hunter = 2,2,RIGHT,90,SPEEDY" + System.lineSeparator() +
                "blinky = 1,1,LEFT,FRIGHTENED:23" + System.lineSeparator() +
                "inky = 1,2,DOWN,CHASE:3" + System.lineSeparator() +
                "pinky = 7,1,UP,CHASE:32" + System.lineSeparator() +
                "clyde = 4,1,RIGHT,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Amber : 19" + System.lineSeparator() +
                "Ben : 49" + System.lineSeparator() +
                "Steven : 14", writer.toString());
    }

    @Test
    public void write2() throws IOException{
        StringWriter writer = new StringWriter();
        try {
            GameWriter.write(writer, pacmanGame);
        } catch (IOException e){
            throw new IOException();
        }

        assertNotEquals("[Board]" + System.lineSeparator() +
                "12,7" + System.lineSeparator() +
                "XXXXXXXXXXXX" + System.lineSeparator() +
                "X0000000000X" + System.lineSeparator() +
                "X000$000000X" + System.lineSeparator() +
                "X0000000000X" + System.lineSeparator() +
                "X00000000P0X" + System.lineSeparator() +
                "X000000B000X" + System.lineSeparator() +
                "XXXXXXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameWriterTest" + System.lineSeparator() +
                "author = Changxin Liu 45245008" + System.lineSeparator() +
                "lives = 16" + System.lineSeparator() +
                "level = 99" + System.lineSeparator() +
                "score = 199" + System.lineSeparator() +
                "hunter = 2,2,RIGHT,90,SPEEDY" + System.lineSeparator() +
                "blinky = 1,1,LEFT,FRIGHTENED:23" + System.lineSeparator() +
                "inky = 1,2,DOWN,CHASE:3" + System.lineSeparator() +
                "pinky = 7,1,UP,CHASE:32" + System.lineSeparator() +
                "clyde = 4,1,RIGHT,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Amber : 19" + System.lineSeparator() +
                "Ben : 49" + System.lineSeparator() +
                "Steven : 14" + System.lineSeparator(), writer.toString());
    }

    @Test
    public void write3() throws IOException{
        StringWriter writer = new StringWriter();
        try {
            GameWriter.write(writer, pacmanGame);
        } catch (IOException e){
            throw new IOException();
        }

        assertNotEquals("[Board]" + System.lineSeparator() +
                "12,7" + System.lineSeparator() +
                "XXXXXXXXXXXX" + System.lineSeparator() +
                "X0000000000X" + System.lineSeparator() +
                "X000$000000X" + System.lineSeparator() +
                "X0000000000X" + System.lineSeparator() +
                "X00000000P0X" + System.lineSeparator() +
                "X000000B000X" + System.lineSeparator() +
                "XXXXXXXXXXXX" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameWriterTest" + System.lineSeparator() +
                "author = Changxin Liu 45245008" + System.lineSeparator() +
                "lives = 16" + System.lineSeparator() +
                "level = 99" + System.lineSeparator() +
                "score = 199" + System.lineSeparator() +
                "hunter = 2,2,RIGHT,90,SPEEDY" + System.lineSeparator() +
                "blinky = 1,1,LEFT,FRIGHTENED:23" + System.lineSeparator() +
                "inky = 1,2,DOWN,CHASE:3" + System.lineSeparator() +
                "pinky = 7,1,UP,CHASE:32" + System.lineSeparator() +
                "clyde = 4,1,RIGHT,SCATTER:2" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Amber : 19" + System.lineSeparator() +
                "Ben : 49" + System.lineSeparator() +
                "Steven : 14", writer.toString());
    }

    @Test
    public void write4() throws IOException{
        StringWriter writer = new StringWriter();
        try {
            GameWriter.write(writer, pacmanGame);
        } catch (IOException e){
            throw new IOException();
        }

        assertNotEquals("[Board]" + System.lineSeparator() +
                "12,7" + System.lineSeparator() +
                "XXXXXXXXXXXX" + System.lineSeparator() +
                "X0000000000X" + System.lineSeparator() +
                "X000$000000X" + System.lineSeparator() +
                "X0000000000X" + System.lineSeparator() +
                "X00000000P0X" + System.lineSeparator() +
                "X000000B000X" + System.lineSeparator() +
                "XXXXXXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameWriterTest" + System.lineSeparator() +
                "author = Changxin Liu 45245008" + System.lineSeparator() +
                "lives = 16" + System.lineSeparator() +
                "level = 99" + System.lineSeparator() +
                "score = 199" + System.lineSeparator() +
                "hunter = 2,2,RIGHT,90,SPEEDY" + System.lineSeparator() +
                "blinky = 1,1,LEFT,FRIGHTENED:23" + System.lineSeparator() +
                "inky = 1,2,DOWN,CHASE:3" + System.lineSeparator() +
                "pinky = 7,1,UP,CHASE:32" + System.lineSeparator() +
                "clyde = 4,1,RIGHT,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Amber:19" + System.lineSeparator() +
                "Ben:49" + System.lineSeparator() +
                "Steven:14", writer.toString());
    }

    @Test
    public void write5() throws IOException{
        String title = "GameWriterTest";
        String author = "Changxin Liu 45245008";

        PacmanBoard pacmanBoard = new PacmanBoard(12,7);
        pacmanBoard.setEntry(new Position(9,4), BoardItem.PACMAN_SPAWN);
        pacmanBoard.setEntry(new Position(4,2), BoardItem.GHOST_SPAWN);
        pacmanBoard.setEntry(new Position(7,5), BoardItem.BIG_DOT);

        Hunter hunter = new Speedy();
        hunter.setDirection(Direction.RIGHT);
        hunter.setPosition(new Position(2,2));
        hunter.activateSpecial(90);

        pacmanGame = new PacmanGame(title, author, hunter, pacmanBoard);
        pacmanGame.setLives(16);
        pacmanGame.setLevel(99);
        for (Ghost ghost : pacmanGame.getGhosts()) {
            if (ghost.getType() == GhostType.BLINKY) {
                ghost.setDirection(Direction.LEFT);
                ghost.setPhase(Phase.FRIGHTENED,23);
                ghost.setPosition(new Position(1,1));
            } else if (ghost.getType() == GhostType.INKY) {
                ghost.setDirection(Direction.DOWN);
                ghost.setPhase(Phase.CHASE,3);
                ghost.setPosition(new Position(1,2));
            } else if (ghost.getType() == GhostType.PINKY) {
                ghost.setDirection(Direction.UP);
                ghost.setPhase(Phase.CHASE,32);
                ghost.setPosition(new Position(7,1));
            } else if (ghost.getType() == GhostType.CLYDE) {
                ghost.setDirection(Direction.RIGHT);
                ghost.setPhase(Phase.SCATTER,2);
                ghost.setPosition(new Position(4,1));

            }
        }
        pacmanGame.getScores().increaseScore(199);


        StringWriter writer = new StringWriter();
        try {
            GameWriter.write(writer, pacmanGame);
        } catch (IOException e){
            throw new IOException();
        }

        assertEquals("[Board]" + System.lineSeparator() +
                "12,7" + System.lineSeparator() +
                "XXXXXXXXXXXX" + System.lineSeparator() +
                "X0000000000X" + System.lineSeparator() +
                "X000$000000X" + System.lineSeparator() +
                "X0000000000X" + System.lineSeparator() +
                "X00000000P0X" + System.lineSeparator() +
                "X000000B000X" + System.lineSeparator() +
                "XXXXXXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameWriterTest" + System.lineSeparator() +
                "author = Changxin Liu 45245008" + System.lineSeparator() +
                "lives = 16" + System.lineSeparator() +
                "level = 99" + System.lineSeparator() +
                "score = 199" + System.lineSeparator() +
                "hunter = 2,2,RIGHT,90,SPEEDY" + System.lineSeparator() +
                "blinky = 1,1,LEFT,FRIGHTENED:23" + System.lineSeparator() +
                "inky = 1,2,DOWN,CHASE:3" + System.lineSeparator() +
                "pinky = 7,1,UP,CHASE:32" + System.lineSeparator() +
                "clyde = 4,1,RIGHT,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator(), writer.toString());
    }


    @Test
    public void write6() throws IOException{
        StringWriter writer = new StringWriter();
        try {
            GameWriter.write(writer, pacmanGame);
        } catch (IOException e){
            throw new IOException();
        }

        assertNotEquals("[Board]" + System.lineSeparator() +
                "12,7" + System.lineSeparator() +
                "XXXXXXXXXXXX" + System.lineSeparator() +
                "X0000000000X" + System.lineSeparator() +
                "X000$000000X" + System.lineSeparator() +
                "X0000000000X" + System.lineSeparator() +
                "X00000000P0X" + System.lineSeparator() +
                "X000000B000X" + System.lineSeparator() +
                "XXXXXXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameWriterTest" + System.lineSeparator() +
                "author = Changxin Liu 45245008" + System.lineSeparator() +
                "lives = 16" + System.lineSeparator() +
                "level = 99" + System.lineSeparator() +
                "score = 199" + System.lineSeparator() +
                "hunter = 2,2,RIGHT,90,SPEEDY" + System.lineSeparator() +
                "inky = 1,2,DOWN,CHASE:3" + System.lineSeparator() +
                "blinky = 1,1,LEFT,FRIGHTENED:23" + System.lineSeparator() +
                "clyde = 4,1,RIGHT,SCATTER:2" + System.lineSeparator() +
                "pinky = 7,1,UP,CHASE:32" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Amber : 19" + System.lineSeparator() +
                "Ben : 49" + System.lineSeparator() +
                "Steven : 14", writer.toString());
    }


}
