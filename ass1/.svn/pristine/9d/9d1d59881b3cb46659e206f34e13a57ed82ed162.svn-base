package pacman.board;

/**
 * Definition of BoardItem available in the game.
 */
public enum BoardItem{

    /**
     * a specified boarditem called NONE
     */
    NONE(true, 0, '0'),

    /**
     * a specified boarditem called WALL
     */
    WALL(false, 0, 'X'),

    /**
     * a specified boarditem called DOT
     */
    DOT(true, 10, '1'),

    /**
     * a specified boarditem called BIG_DOT
     */
    BIG_DOT(true, 15, 'B'),

    /**
     * a specified boarditem called BIG_DOT_SPAWN
     */
    BIG_DOT_SPAWN(true, 0, 'b'),

    /**
     * a specified boarditem called GHOST_SPAWN
     */
    GHOST_SPAWN(true, 0, '$'),

    /**
     * a specified boarditem called PACMAN_SPAWN
     */
    PACMAN_SPAWN(true, 0, 'P');

    // indicates if a boarditem can be gone through
    private boolean pathable;

    // indicates the score of a boarditem
    private int score;

    // indicates the character of a boarditem
    private char aChar;

    // Constructs a boarditem with given attributes
    // @parameter True for pathable means this boarditem can be gone through,
    // false means cannot. Score means the score you will get after interacting
    // with this boarditem. Char means the character key of this boarditem.
    private BoardItem(boolean pathable, int score, char aChar){
        this.pathable = pathable;
        this.score = score;
        this.aChar = aChar;
    }

    /**
     * Returns the score of a boarditem
     *
     * @return a boarditem's score
     */
    public int getScore(){
        return this.score;
    }

    /**
     * Returns if a boarditem can be gone through
     *
     * @return if it is pathable for a boarditem
     */
    public boolean getPathable() {
        return this.pathable;
    }

    /**
     * Returns the character key for a boarditem
     *
     * @return a boarditem's character key
     */
    public char getChar(){
        return this.aChar;
    }

    /**
     * Finds the corresponding boarditem though the given key
     *
     * @param key the key of the boarditem
     * @return corresponding boarditem to the key
     * @throws IllegalArgumentException when the given key has no corresponding
     * boarditem in BoardItem
     */
    public static BoardItem getItem(char key) throws IllegalArgumentException{
        if (key != 'o' &&
                key != 'X' &&
                key != '1' &&
                key != 'B' &&
                key != 'b' &&
                key != '$' &&
                key != 'P'){
            throw new IllegalArgumentException();
        }
        switch (key){
            case 'o':
                return BoardItem.NONE;
            case 'X':
                return BoardItem.WALL;
            case '1':
                return BoardItem.DOT;
            case 'B':
                return BoardItem.BIG_DOT;
            case 'b':
                return BoardItem.BIG_DOT_SPAWN;
            case '$':
                return BoardItem.GHOST_SPAWN;
            case 'P':
                return BoardItem.PACMAN_SPAWN;
                default:
                    return null;
        }
    }
}

