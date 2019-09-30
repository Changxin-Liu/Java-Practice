package pacman.game;

import pacman.board.BoardItem;
import pacman.board.PacmanBoard;
import pacman.ghost.*;
import pacman.hunter.*;
import pacman.util.Direction;
import pacman.util.Position;
import pacman.util.UnpackableException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * GameReader Reads in a saved games state and returns a game instance.
 */
public class GameReader {

    // The array to store strings splited by line
    private static String[] recordsByLine;
    // The number of the board rows (Height - y)
    private static int boardRows;
    // The number of the board columns (Width - x)
    private static int boardColumns;
    // The map representing the char of keys on each position in the board
    private static StringBuilder boardMap;
    // The title of the game
    private static String title;
    // The author of the game
    private static String author;
    // The number of remaining lives in the game
    private static int lives;
    // The level in the game
    private static int level;
    // The current score in the game
    private static int score;
    // The hunter in the game
    private static Hunter hunter;
    // The array to store the information of the hunter
    private static String[] hunterDetails;
    // The hunter's direction
    private static Direction hunterDirection;
    // The array to store blinky's info.
    private static String[] blinkyDetails;
    // The array tp store inky's info.
    private static String[] inkyDetails;
    // The array to store pinky's info.
    private static String[] pinkyDetails;
    // The array to store clyde's info.
    private static String[] clydeDetails;

    /**
     * Constructs a gamereader
     */
    public GameReader() {

    }

    /**
     * Reads in a game according to given game details
     *
     * @param reader the reader to read the file
     * @return a pacmangame recorded in a file
     * @throws UnpackableException when the game cannot be loaded
     *                             (e.g: one game record is invalid)
     * @throws IOException         when an I/O exception occurs
     */
    public static PacmanGame read(Reader reader)
            throws UnpackableException, IOException {


        String rawRecord;
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String rawString;
            while ((rawString = bufferedReader.readLine()) != null) {

                if (!rawString.equals("")) {
                    if (rawString.charAt(0) != ';') {
                        stringBuilder.append(rawString);
                        stringBuilder.append(System.lineSeparator());
                    }
                } else {
                    stringBuilder.append(rawString);
                    stringBuilder.append(System.lineSeparator());
                }
            }

            rawRecord = stringBuilder.toString().substring(0,
                    stringBuilder.length() - 1);

        } catch (IOException e) {
            throw new IOException();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                System.err.println("Error closing file");
            }
        }

        recordsByLine = rawRecord.split(System.lineSeparator());

        validateTag(0, "[Board]");
        validateBoardSize();
        validateBlankLine();
        validateBoardMap();
        validateTag(1 + boardRows + 2, "[Game]");
        validateAssignments(getAssignments());
        validateTag(1 + boardRows + 1 + 13, "[Scores]");

        return setPacmanGame();
    }

    /*
     * Sets the pacmangame
     */
    private static PacmanGame setPacmanGame() throws UnpackableException {
        String finalTitle;
        String finalAuthor;
        Hunter finalHunter;
        PacmanBoard finalPacmanBoard;

        Map<String, Integer> scoreEntry = validateEntries();

        finalTitle = title;
        finalAuthor = author;
        finalHunter = setHunter(hunter, hunterDirection, hunterDetails);
        finalPacmanBoard = getBoard();

        PacmanGame pacmanGame = new PacmanGame(finalTitle, finalAuthor,
                finalHunter, finalPacmanBoard);
        setGhost(pacmanGame, blinkyDetails,
                inkyDetails, pinkyDetails, clydeDetails);
        pacmanGame.getScores().setScores(scoreEntry);
        pacmanGame.getScores().increaseScore(score);
        pacmanGame.setLevel(level);
        pacmanGame.setLives(lives);

        return pacmanGame;
    }

    /*
     * Validates the tags in the file
     * @throws UnpackableException when the tags are invalid
     */
    private static void validateTag(int line, String tagName) throws UnpackableException {
        String tag = recordsByLine[line];
        if (line == 0) {
            if (!tag.trim().equals("[Board]")) {
                throw new UnpackableException();
            }
        } else if (line == 1 + boardRows + 2) {
            if (!tag.trim().equals("[Game]")) {
                throw new UnpackableException();
            }
        } else if (line == 1 + boardRows + 1 + 13) {
            if (!tag.trim().equals("[Scores]")) {
                throw new UnpackableException();
            }
        }
    }

    /*
     * Validates the boardsize
     * @throws UnpackableException when the record for board size is invalid
     */
    private static void validateBoardSize() throws UnpackableException {

        String boardSize = recordsByLine[1];
        String[] boardArray;

        try {
            boardArray = boardSize.split(",", 2);
        } catch (Exception e) {
            throw new UnpackableException();
        }

        try {
            boardRows = Integer.parseInt(boardArray[1]);
            boardColumns = Integer.parseInt(boardArray[0]);
            if (boardRows < 0 || boardColumns < 0) {
                throw new UnpackableException();
            }
        } catch (Exception e) {
            throw new UnpackableException();
        }
    }

    /*
     * Validates blank lines in the file. There must be no empty lines before
     * the first block and only between each block there must be one line.
     * @throws UnpackableException when blank lines
     * appear in invalid positions
     */
    private static void validateBlankLine() throws UnpackableException {
        int firstBlankLineIndex = 1 + boardRows + 1;
        int secondBlankLineIndex = firstBlankLineIndex + 12;
        int blankLineCount = 0;

        String firstBlankLine = recordsByLine[firstBlankLineIndex];
        String secondBlankLine = recordsByLine[secondBlankLineIndex];

        if (!firstBlankLine.equals("") || !secondBlankLine.equals("")) {
            throw new UnpackableException();
        }

        for (int i = 0; i < recordsByLine.length; i++) {
            if (recordsByLine[i].equals("")) {
                blankLineCount++;
            }
            if (blankLineCount > 2) {
                throw new UnpackableException();
            }
        }
    }

    /*
     * Validates the board map. For each position, there must be a char for a
     * BoardItem.
     * @throws UnpackableException when the board map is invalid
     */
    private static void validateBoardMap() throws UnpackableException {
        boardMap = new StringBuilder();
        for (int i = 2; i < boardRows + 2; i++) {
            boardMap.append(recordsByLine[i]);
        }

        if (boardMap.length() != boardRows * boardColumns) {
            throw new UnpackableException();
        }

        for (int i = 0; i < boardMap.length(); i++) {
            if (!(boardMap.charAt(i) == 'X' || boardMap.charAt(i) == '1' ||
                    boardMap.charAt(i) == '0' || boardMap.charAt(i) == 'B' ||
                    boardMap.charAt(i) == 'b' || boardMap.charAt(i) == 'P' ||
                    boardMap.charAt(i) == '$')) {
                throw new UnpackableException();
            }
        }
    }

    /*
     * Gets a board according to the board map
     * @returns a board including the BoardItem according
     * to the given board map
     */
    private static PacmanBoard getBoard() {
        PacmanBoard pacmanBoard = new PacmanBoard(boardColumns, boardRows);
        BoardItem[] boardItems = new BoardItem[boardRows * boardColumns];

        for (int i = 0; i < boardMap.length(); i++) {
            switch (boardMap.charAt(i)) {
                case 'X':
                    boardItems[i] = BoardItem.WALL;
                    break;
                case '1':
                    boardItems[i] = BoardItem.DOT;
                    break;
                case '0':
                    boardItems[i] = BoardItem.NONE;
                    break;
                case 'B':
                    boardItems[i] = BoardItem.BIG_DOT;
                    break;
                case 'b':
                    boardItems[i] = BoardItem.BIG_DOT_SPAWN;
                    break;
                case 'P':
                    boardItems[i] = BoardItem.PACMAN_SPAWN;
                    break;
                case '$':
                    boardItems[i] = BoardItem.GHOST_SPAWN;
                    break;
                default:
                    boardItems[i] = null;
            }
        }

        for (int y = 0; y < boardRows; y++) {
            for (int x = 0; x < boardColumns; x++) {
                pacmanBoard.setEntry(new Position(x, y),
                        boardItems[y * boardColumns + x]);
            }
        }
        return pacmanBoard;
    }

    /*
     * Gets the assignments for the file
     * @returns an array to store the assignments
     */
    private static String[] getAssignments() {
        String[] assignments = new String[10];
        int counter = 0;
        for (int i = boardRows + 4; i < boardRows + 14; i++) {
            assignments[counter] = recordsByLine[i];
            counter++;
        }

        return assignments;
    }

    /*
     * Validates the type of string in assignments
     * @throws UnpackableException when the type of records is not a string
     */
    private static String validateString(String string)
            throws UnpackableException {
        String[] stringsArray = string.split("=", 2);
        if (!stringsArray[1].trim().getClass().
                getName().equals("java.lang.String") ||
                stringsArray[1].trim().length() == 0) {
            throw new UnpackableException();
        }
        return stringsArray[1].trim();
    }

    /*
     * Validates the type of integer in assignments
     * @return the integers
     * @throws UnpackableException when the type of records cannot be parsed
     * to Integers
     */
    private static int validateInteger(String integer)
            throws UnpackableException {
        String[] integersArray = integer.split("=", 2);
        int integerValue;

        try {
            integerValue = Integer.parseInt(integersArray[1].trim());
        } catch (Exception e) {
            throw new UnpackableException();
        }

        if (integerValue < 0) {
            throw new UnpackableException();
        }
        return integerValue;
    }

    /*
     * Validates the coordinates in the file
     * @throws UnpackableException when the coordinate is invalid
     */
    private static void validateCoordinate(String[] valueArray)
            throws UnpackableException {

        try {
            int x = Integer.parseInt(valueArray[0]);
            int y = Integer.parseInt(valueArray[1]);
            if (x >= boardColumns || x < 0 || y >= boardRows || y < 0) {
                throw new UnpackableException();
            }
        } catch (Exception e) {
            throw new UnpackableException();
        }
    }

    /*
     * Validates the directions in the file
     * @return an Enum in Direction
     * @throws UnpackableException when the direction is invalid
     */
    private static Direction validateDirection(String[] valueArray)
            throws UnpackableException {

        switch (valueArray[2]) {
            case "LEFT":
                return Direction.LEFT;
            case "RIGHT":
                return Direction.RIGHT;
            case "UP":
                return Direction.UP;
            case "DOWN":
                return Direction.DOWN;
            default:
                throw new UnpackableException();
        }

    }

    /*
     * Validates the hunter in the file
     * @return a hunter according to the file record
     * @throws UnpackableException when the record for a hunter is invalid
     */
    private static Hunter validateHunter(String hunter)
            throws UnpackableException {
        String[] hunterArray = hunter.split("=", 2);
        String value = hunterArray[1].trim();
        hunterDetails = value.split(",", 5);

        validateCoordinate(hunterDetails);
        hunterDirection = validateDirection(hunterDetails);

        HunterType hunterType;

        switch (hunterDetails[4]) {
            case "SPEEDY":
                hunterType = HunterType.SPEEDY;
                break;
            case "PHASEY":
                hunterType = HunterType.PHASEY;
                break;
            case "HUNGRY":
                hunterType = HunterType.HUNGRY;
                break;
            case "PHIL":
                hunterType = HunterType.PHIL;
                break;
            default:
                throw new UnpackableException();
        }

        switch (hunterType) {
            case PHIL:
                return new Phil();
            case HUNGRY:
                return new Hungry();
            case PHASEY:
                return new Phasey();
            case SPEEDY:
                return new Speedy();
            default:
                throw new UnpackableException();
        }
    }

    /*
     * Validates the duration for both Hunters and Ghosts
     * @return the value of the duration
     */
    private static int validateDuration(String durationString)
            throws UnpackableException {
        int duration;
        try {
            duration = Integer.parseInt(durationString);
            if (duration < 0) {
                throw new UnpackableException();
            }
        } catch (Exception e) {
            throw new UnpackableException();
        }

        return duration;
    }

    /*
     * Validates ghosts in the file
     * @throws UnpackableException when a ghost is invalid
     */
    private static void validateGhost(String ghost)
            throws UnpackableException {
        String[] ghostAssignments = ghost.split("=", 2);
        String ghostValue = ghostAssignments[1].trim();
        String[] ghostDetails = ghostValue.split(",", 4);

        validateCoordinate(ghostDetails);
        validateDirection(ghostDetails);
        String phaseEntry = ghostDetails[3];
        String[] phaseEntries = phaseEntry.split(":", 2);

        if (!phaseEntries[0].equals("FRIGHTENED") &&
                !phaseEntries[0].equals("SCATTER") &&
                !phaseEntries[0].equals("CHASE")) {
            throw new UnpackableException();
        }

        if (ghostAssignments[0].trim().equals("blinky")) {
            blinkyDetails = ghostDetails;
        } else if (ghostAssignments[0].trim().equals("clyde")) {
            clydeDetails = ghostDetails;
        } else if (ghostAssignments[0].trim().equals("inky")) {
            inkyDetails = ghostDetails;
        } else if (ghostAssignments[0].trim().equals("pinky")) {
            pinkyDetails = ghostDetails;
        }
    }

    /*
     * Validates assignments in the file
     * @throws UnpackableException when assignments in the file are invalid
     */
    private static void validateAssignments(String[] assignments)
            throws UnpackableException {

        int titleNum = 0;
        int authorNum = 0;
        int livesNum = 0;
        int levelNum = 0;
        int scoreNum = 0;
        int hunterNum = 0;
        int blinkyNum = 0;
        int inkyNum = 0;
        int pinkyNum = 0;
        int clydeNum = 0;

        for (String assignment : assignments) {
            switch (assignment.split("=", 2)[0].trim()) {
                case "title":
                    title = validateString(assignment);
                    titleNum++;
                    break;
                case "author":
                    author = validateString(assignment);
                    authorNum++;
                    break;
                case "lives":
                    lives = validateInteger(assignment);
                    livesNum++;
                    break;
                case "level":
                    level = validateInteger(assignment);
                    levelNum++;
                    break;
                case "score":
                    score = validateInteger(assignment);
                    scoreNum++;
                    break;
                case "hunter":
                    hunter = validateHunter(assignment);
                    hunterNum++;
                    break;
                case "blinky":
                    validateGhost(assignment);
                    blinkyNum++;
                    break;
                case "inky":
                    validateGhost(assignment);
                    inkyNum++;
                    break;
                case "pinky":
                    validateGhost(assignment);
                    pinkyNum++;
                    break;
                case "clyde":
                    validateGhost(assignment);
                    clydeNum++;
                    break;
                default:
                    throw new UnpackableException();
            }
        }

        if (!(titleNum == 1 && authorNum == 1 && livesNum == 1
                && levelNum == 1 && scoreNum == 1 && hunterNum == 1
                && blinkyNum == 1 && inkyNum == 1 && pinkyNum == 1
                && clydeNum == 1)) {
            throw new UnpackableException();
        }
    }

    /*
     * Validates entries in the file
     * @return a map containing score entries
     * @throws UnpackableException when score entries are invalid
     */
    private static Map<String, Integer> validateEntries()
            throws UnpackableException {
        Map<String, Integer> scoreMap = new HashMap<>();
        List<String> entryList = new LinkedList<>();
        List<String> keyList = new LinkedList<>();
        List<Integer> valueList = new LinkedList<>();
        for (int i = 1 + boardRows + 15; i < recordsByLine.length; i++) {
            if (!recordsByLine[i].equals("")) {
                entryList.add(recordsByLine[i]);
            }
        }

        for (int i = 0; i < entryList.size(); i++) {
            try {
                keyList.add(entryList.get(i).trim().split(":", 2)[0]);
                valueList.add(Integer.parseInt(entryList.get(i).split(":",
                        2)[1].trim()));
            } catch (Exception e) {
                throw new UnpackableException();
            }
        }

        for (int i = 0; i < keyList.size(); i++) {
            if (keyList.get(i) != null && !keyList.get(i).isBlank()) {
                scoreMap.put(keyList.get(i).trim(), valueList.get(i));
            } else {
                throw new UnpackableException();
            }
        }

        return scoreMap;
    }

    /*
     * Sets hunter details to a hunter
     * @return a hunter with the given details
     */
    private static Hunter setHunter(Hunter hunter, Direction hunterDirection,
            String[] hunterValueArray) throws UnpackableException {

        hunter.setPosition(new Position(
                Integer.parseInt(hunterValueArray[0].trim()),
                Integer.parseInt(hunterValueArray[1].trim())));
        hunter.setDirection(hunterDirection);
        String hunterDurationString = hunterValueArray[3];
        hunter.activateSpecial(validateDuration(hunterDurationString));

        return hunter;
    }

    /*
     * Gets the phase of a ghost
     * @return the phase of a ghost
     */
    private static Phase getPhase(String[] ghostValueArray) {

        switch (ghostValueArray[3].split(":", 2)[0]) {
            case "CHASE":
                return Phase.CHASE;
            case "FRIGHTENED":
                return Phase.FRIGHTENED;
            case "SCATTER":
                return Phase.SCATTER;
            default:
                return null;
        }
    }

    /*
     * Gets the phase duration for a ghost
     * @return the duraion of a phase
     */
    private static int getGhostDuration(String[] ghostValueArray)
            throws UnpackableException {

        String ghostDurationString = ghostValueArray[3].
                split(":", 2)[1];

        return validateDuration(ghostDurationString);
    }

    /*
     * Sets ghosts with the given details
     * @throws UnpackableException when the directions are invalid
     */
    private static void setGhost(PacmanGame pacmanGame,
                                 String[] blinkyValueArray,
                                 String[] inkyValueArray,
                                 String[] pinkyValueArray,
                                 String[] clydeValueArray)
            throws UnpackableException {

        for (Ghost ghost : pacmanGame.getGhosts()) {
            switch (ghost.getType()) {
                case BLINKY:
                    ghost.setPosition(new Position(
                            Integer.parseInt(blinkyValueArray[0]),
                            Integer.parseInt(blinkyValueArray[1])));
                    ghost.setPhase(getPhase(blinkyValueArray),
                            getGhostDuration(blinkyValueArray));
                    ghost.setDirection(validateDirection(blinkyValueArray));
                    break;
                case INKY:
                    ghost.setPosition(new Position(
                            Integer.parseInt(inkyValueArray[0]),
                            Integer.parseInt(inkyValueArray[1])));
                    ghost.setPhase(getPhase(inkyValueArray),
                            getGhostDuration(inkyValueArray));
                    ghost.setDirection(validateDirection(inkyValueArray));
                    break;
                case PINKY:
                    ghost.setPosition(new Position(
                            Integer.parseInt(pinkyValueArray[0]),
                            Integer.parseInt(pinkyValueArray[1])));
                    ghost.setPhase(getPhase(pinkyValueArray),
                            getGhostDuration(pinkyValueArray));
                    ghost.setDirection(validateDirection(pinkyValueArray));
                    break;
                case CLYDE:
                    ghost.setPosition(new Position(
                            Integer.parseInt(clydeValueArray[0]),
                            Integer.parseInt(clydeValueArray[1])));
                    ghost.setPhase(getPhase(clydeValueArray),
                            getGhostDuration(clydeValueArray));
                    ghost.setDirection(validateDirection(clydeValueArray));
                    break;
                default:
                    return;
            }
        }
    }
}
