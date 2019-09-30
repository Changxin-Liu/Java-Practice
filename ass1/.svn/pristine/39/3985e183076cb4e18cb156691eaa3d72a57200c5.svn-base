package pacman.board;

import pacman.util.Position;

/**
 * A class representing the pacman game board which is by default
 * surrounded by a BoardItem.WALL
 */
public class PacmanBoard{

    // A 2D array to represent the board
    private BoardItem[][] grid;

    // the width of the board
    private int width;

    // the height of the board
    private int height;

    /**
     * Constructs a pacman board with the given width and height.
     * Initially, the board will be surrounded by BoardItem.WALL and
     * the rest of the space will be BoardItem.NONE
     *
     * @param width the given width
     * @param height the given height
     * @throws IllegalArgumentException when the height or width is less
     * than or equal to zero
     */
    public PacmanBoard(int width,
                       int height)
            throws IllegalArgumentException{

        if (width <= 0 || height <= 0){
            throw new IllegalArgumentException();
        }
        this.width = width;
        this.height = height;
        grid = new BoardItem[this.width][this.height];

        for (int x = 0; x < this.width; x++){
            for (int y = 0; y < this.height; y++){
                if (x == 0 || y == 0 ||
                        x == this.width - 1 || y == this.height - 1){
                    grid[x][y] = BoardItem.WALL;
                }else {
                    grid[x][y] = BoardItem.NONE;
                }
            }
        }
    }

    /**
     * Constructs a pacman board with copying the size from another
     * pacman board. Also, by default the board will be surrounded by
     * BoardItem.WALL and the rest of space will be BoardItem.NONE
     *
     * @param other another pacman board
     * @throws NullPointerException when the copy is null
     */
    public PacmanBoard(PacmanBoard other)
            throws NullPointerException{

        if (other == null){
            throw new NullPointerException();
        }
        this.width = other.getWidth();
        this.height = other.getHeight();
        grid = new BoardItem[this.width][this.height];

        for (int x = 0; x < this.width; x++){
            for (int y = 0; y < this.height; y++){
                if (x == 0 || y == 0 ||
                        x == this.width - 1 || y == this.height - 1){
                    grid[x][y] = BoardItem.WALL;
                }else {
                    grid[x][y] = BoardItem.NONE;
                }
            }
        }
    }

    /**
     * Returns the width of a pacman board
     *
     * @return the pacman board's width
     */
    public int getWidth(){
        return this.width;
    }

    /**
     * Returns the height of a pacman board
     *
     * @return the pacman board's height
     */
    public int getHeight(){
        return this.height;
    }

    /**
     * Sets a tile on the board to an item. There is at most one PACMAN_SPAWN
     * in the board. If putting another one in a different position in the
     * board, the old one should be replaced with NONE. Ghost_SPAWN is the same.
     *
     * @param position the position to put an item
     * @param item the item to put in the board
     * @throws IndexOutOfBoundsException when the position is not in the board
     * @throws NullPointerException when the position or item is null
     */
    public void setEntry(Position position,
                         BoardItem item)
            throws IndexOutOfBoundsException, NullPointerException{

        if (position == null ||
                item == null){
            throw new NullPointerException();
        }

        if (position.getX() >= this.width ||
                position.getY() >= this.height){
            throw new IndexOutOfBoundsException();
        }

        for (int x = 0; x < this.width; x++){
            for(int y = 0; y < this.height; y++){
                if (grid[x][y] == BoardItem.PACMAN_SPAWN &&
                        item == BoardItem.PACMAN_SPAWN){
                    grid[x][y] = BoardItem.NONE;
                }
                if (grid[x][y] == BoardItem.GHOST_SPAWN &&
                        item == BoardItem.GHOST_SPAWN){
                    grid[x][y] = BoardItem.NONE;
                }
            }
        }

        grid[position.getX()][position.getY()] = item;
    }

    /**
     * Returns the item on one particular position
     *
     * @param position the position to get
     * @return the item on that position
     * @throws IndexOutOfBoundsException when the position is not in the board
     * @throws NullPointerException when the position is null
     */
    public BoardItem getEntry(Position position)
            throws IndexOutOfBoundsException, NullPointerException{

        if (position == null){
            throw new NullPointerException();
        }

        if (position.getX() > this.width - 1 ||
                position.getY() > this.height - 1){
            throw new IndexOutOfBoundsException();
        }

        return grid[position.getX()][position.getY()];
    }

    /**
     * Tries to eat the item on one particular position. If the item is
     * BoardItem.DOT, after it is eaten, it will be replaced with
     * BoardItem.NONE, If the item is Board.BIG_DOT, after it is eaten, it
     * will be replaced with BoardItem.BIG_DOT_SPAWN. If the item is anything
     * else, just return the item.
     *
     * @param position the position that the item is on
     * @return the item on or previously on that position
     * @throws IndexOutOfBoundsException when the position is not in the board
     * @throws NullPointerException when the position is null
     */
    public BoardItem eatDot(Position position)
            throws IndexOutOfBoundsException, NullPointerException{

        if (position == null){
            throw new NullPointerException();
        }

        if (position.getX() > this.width - 1 ||
                position.getY() > this.height - 1){
            throw new IndexOutOfBoundsException();
        }

        if (grid[position.getX()][position.getY()] == BoardItem.DOT){
            grid[position.getX()][position.getY()] = BoardItem.NONE;
            return BoardItem.DOT;
        }else if (grid[position.getX()][position.getY()] == BoardItem.BIG_DOT){
            grid[position.getX()][position.getY()] = BoardItem.BIG_DOT_SPAWN;
            return BoardItem.BIG_DOT;
        }

        return grid[position.getX()][position.getY()];
    }

    /**
     * Returns the ghost spawn position
     *
     * @return the position of ghost spawn
     */
    public Position getGhostSpawn(){

        for (int x = 0; x < this.width; x++){
            for (int y = 0; y < this.height; y++){
                if (grid[x][y] == BoardItem.GHOST_SPAWN){
                    return new Position(x, y);
                }
            }
        }
        return null;
    }

    /**
     * Returns the pacman spawn position
     *
     * @return the position of pacman board
     */
    public Position getPacmanSpawn(){

        for (int x = 0; x < this.width; x++){
            for (int y = 0; y < this.height; y++){
                if (grid[x][y] == BoardItem.PACMAN_SPAWN){
                    return new Position(x, y);
                }
            }
        }
        return null;
    }

    /**
     * Check if the board contains any pickup items
     *
     * @return true if the board does not contain any pickup item
     */
    public boolean isEmpty(){

        for (int x = 0; x < this.width; x++){
            for (int y = 0; y < this.height; y++){
                if (grid[x][y] == BoardItem.DOT ||
                        grid[x][y] == BoardItem.BIG_DOT){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Resets the board with default settings
     */
    public void reset(){
        for (int x = 0; x < this.width; x++){
            for (int y = 0; y < this.height; y++){
                if (grid[x][y] == BoardItem.NONE){
                    grid[x][y] = BoardItem.DOT;
                }else if(grid[x][y] == BoardItem.BIG_DOT_SPAWN){
                    grid[x][y] = BoardItem.BIG_DOT;
                }
            }
        }
    }


}
