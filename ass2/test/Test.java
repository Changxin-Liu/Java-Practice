import pacman.board.BoardItem;
import pacman.board.PacmanBoard;
import pacman.game.GameWriter;
import pacman.game.PacmanGame;
import pacman.ghost.*;
import pacman.hunter.Hunter;
import pacman.hunter.Speedy;
import pacman.util.Direction;
import pacman.util.Position;

import java.io.*;

public class Test {
    public static void main(String [] args) throws IOException{

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

        Ghost blinky = new Blinky();
        blinky.setDirection(Direction.LEFT);
        blinky.setPhase(Phase.FRIGHTENED,23);
        blinky.setPosition(new Position(1,1));

        Ghost inky = new Inky();
        inky.setDirection(Direction.DOWN);
        inky.setPhase(Phase.CHASE,23);
        inky.setPosition(new Position(1,2));

        Ghost pinky = new Pinky();
        pinky.setDirection(Direction.LEFT);
        pinky.setPhase(Phase.CHASE,3);
        pinky.setPosition(new Position(7,1));

        Ghost clyde = new Clyde();
        clyde.setDirection(Direction.LEFT);
        clyde.setPhase(Phase.SCATTER,23);
        clyde.setPosition(new Position(4,1));

        PacmanGame pacmanGame = new PacmanGame(title, author, hunter, pacmanBoard);
        pacmanGame.setLives(16);
        pacmanGame.setLevel(99);
        for (Ghost ghost : pacmanGame.getGhosts()) {
            if (ghost.getType() == GhostType.BLINKY) {
                ghost = blinky;
            } else if (ghost.getType() == GhostType.INKY) {
                ghost = inky;
            } else if (ghost.getType() == GhostType.PINKY) {
                ghost = pinky;
            } else if (ghost.getType() == GhostType.CLYDE) {
                ghost = clyde;
            }
        }
        pacmanGame.getScores().increaseScore(199);
        pacmanGame.getScores().setScore("Amber",19);
        pacmanGame.getScores().setScore("Steven", 14);
        pacmanGame.getScores().setScore("Ben",49);

        StringWriter writer = new StringWriter();
        try {
            GameWriter.write(writer, pacmanGame);
        } catch (IOException e) {

        }

        System.out.println(writer.toString());
    }
}


