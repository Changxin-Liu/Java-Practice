package pacman.game;

import pacman.ghost.Ghost;
import pacman.ghost.GhostType;
import pacman.util.Position;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

/**
 * A class to write the PacmanGame to a standard format.
 */
public class GameWriter {

    /**
     * Constructs a gamewriter
     */
    public GameWriter() {

    }

    /**
     * Saves a PacmanGame to a writer.
     *
     * @param writer the writer to store the data
     * @param game   the game to be saved
     * @throws IOException when an an I/O exception occurres.
     */
    public static void write(Writer writer, PacmanGame game)
            throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        String boardString = getBoardString(game);
        String gameString = getGameString(game);
        String scoreString = getScoreString(game);

        try {
            bufferedWriter.write(boardString);
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            bufferedWriter.write(gameString);
            bufferedWriter.newLine();
            bufferedWriter.write(scoreString);

        } catch (IOException e) {
            throw new IOException();
        } finally {
            try {
                bufferedWriter.flush();
            } catch (IOException e) {
                System.err.println("Error closing file");
            }
        }
    }

    /*
     * Gets a string representing the board in game
     * @para game the game needs to be loaded to get the board
     */
    private static String getBoardString(PacmanGame game) {

        String boardTag = "[Board]";
        int boardColumns = game.getBoard().getWidth();
        int boardRows = game.getBoard().getHeight();
        String boardSize = new Position(boardColumns, boardRows).toString();
        String boardMap = game.getBoard().toString();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(boardTag);
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(boardSize);
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(boardMap);

        return stringBuilder.toString();
    }

    /*
     * Gets a string including the game information
     * @para game the game needs to be loaded to get the game records
     */
    private static String getGameString(PacmanGame game) {

        StringBuilder stringBuilder = new StringBuilder();

        String blinkyToString = null;
        String inkyToString = null;
        String pinkyToString = null;
        String clydeToString = null;

        for (Ghost ghost : game.getGhosts()) {
            if (ghost.getType() == GhostType.BLINKY) {
                blinkyToString = ghost.toString();
            } else if (ghost.getType() == GhostType.INKY) {
                inkyToString = ghost.toString();
            } else if (ghost.getType() == GhostType.PINKY) {
                pinkyToString = ghost.toString();
            } else if (ghost.getType() == GhostType.CLYDE) {
                clydeToString = ghost.toString();
            }
        }

        List<String> gameInfo = new LinkedList<>();
        String gameTag = "[Game]";
        gameInfo.add(gameTag);
        String title = "title = " + game.getTitle();
        gameInfo.add(title);
        String author = "author = " + game.getAuthor();
        gameInfo.add(author);
        String lives = "lives = " + game.getLives();
        gameInfo.add(lives);
        String level = "level = " + game.getLevel();
        gameInfo.add(level);
        String score = "score = " + game.getScores().getScore();
        gameInfo.add(score);
        String hunter = "hunter = " + game.getHunter().toString();
        gameInfo.add(hunter);
        String blinky = "blinky = " + blinkyToString;
        gameInfo.add(blinky);
        String inky = "inky = " + inkyToString;
        gameInfo.add(inky);
        String pinky = "pinky = " + pinkyToString;
        gameInfo.add(pinky);
        String clyde = "clyde = " + clydeToString;
        gameInfo.add(clyde);

        for (String gameRecord : gameInfo) {
            stringBuilder.append(gameRecord);
            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

    /*
     * Gets a string including all score records in the game
     */
    private static String getScoreString(PacmanGame game) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[Scores]");
        stringBuilder.append(System.lineSeparator());

        List<String> list = game.getScores().getEntriesByName();

        if (list.isEmpty()) {
            stringBuilder.append(System.lineSeparator());
        } else {
            for (String entry : list) {
                if (entry != null) {
                    stringBuilder.append(entry);
                    stringBuilder.append(System.lineSeparator());
                }
            }
        }

        return stringBuilder.toString().substring(0,
                stringBuilder.length() - 1);
    }
}