package pacman.board;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import pacman.util.Position;

public class PacmanBoardTest {

    private PacmanBoard pacmanBoard;
    private Position position1;
    private Position position2;
    private Position position3;
    private Position position4;
    private Position position5;
    private Position position6;

    @Before
    public void setUp(){
        pacmanBoard = new PacmanBoard(8,7);
        position1 = new Position(1,2);
        position2 = new Position(2,4);
        position3 = new Position(6,3);
        position4 = new Position(5,6);
        position5 = new Position(7,2);
        position6 = new Position(3,5);

    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorTest1(){
        PacmanBoard pacmanBoard2 = new PacmanBoard(0,1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorTest2(){
        PacmanBoard pacmanBoard2 = new PacmanBoard(-4,1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorTest3(){
        PacmanBoard pacmanBoard2 = new PacmanBoard(2,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorTest4(){
        PacmanBoard pacmanBoard2 = new PacmanBoard(2,-8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorTest5(){
        PacmanBoard pacmanBoard2 = new PacmanBoard(0,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorTest6(){
        PacmanBoard pacmanBoard2 = new PacmanBoard(-3,-5);
    }

    @Test
    public void constructorTest7(){
        for (int x = 0; x < pacmanBoard.getWidth(); x++){
            for (int y = 0; y < pacmanBoard.getHeight(); y++){
                if (x == 0 || y == 0 ||
                        x == pacmanBoard.getWidth() - 1 ||
                        y == pacmanBoard.getHeight() - 1){
                    assertEquals(BoardItem.WALL, pacmanBoard.getEntry(new Position(x,y)));
                }
            }
        }
    }

    @Test
    public void constructorTest8(){
        for (int x = 0; x < pacmanBoard.getWidth(); x++){
            for (int y = 0; y < pacmanBoard.getHeight(); y++){
                if (x != 0 && y != 0 &&
                        x != pacmanBoard.getWidth() - 1 &&
                        y != pacmanBoard.getHeight() - 1){
                    assertEquals(BoardItem.NONE, pacmanBoard.getEntry(new Position(x,y)));
                }
            }
        }
    }

    @Test(expected = NullPointerException.class)
    public void constructorTest9(){
        PacmanBoard pacmanBoard = null;
        PacmanBoard pacmanBoard1 = new PacmanBoard(pacmanBoard);
    }

    @Test
    public void getWidth() {
        assertEquals(8, pacmanBoard.getWidth());
        PacmanBoard pacmanBoard2 = new PacmanBoard(pacmanBoard);
        assertEquals(8, pacmanBoard2.getWidth());


    }

    @Test
    public void getHeight() {
        assertEquals(7,pacmanBoard.getHeight());
        PacmanBoard pacmanBoard2 = new PacmanBoard(pacmanBoard);
        assertEquals(7, pacmanBoard2.getHeight());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setEntry1() {
        Position position1 = new Position(8,7);
        pacmanBoard.setEntry(position1,BoardItem.BIG_DOT);
    }

    @Test(expected = NullPointerException.class)
    public void setEntry2() {
        Position position1 = null;
        pacmanBoard.setEntry(position1,BoardItem.BIG_DOT);
    }

    @Test(expected = NullPointerException.class)
    public void setEntry3() {
        Position position1 = new Position(6,4);
        pacmanBoard.setEntry(position1,null);
    }

    @Test
    public void setEntry4() {
        position1 = new Position(1,2);
        position2 = new Position(2,4);
        position3 = new Position(6,3);
        position4 = new Position(5,6);
        position5 = new Position(7,2);


        pacmanBoard.setEntry(position1, BoardItem.DOT);
        assertEquals(BoardItem.DOT, pacmanBoard.getEntry(position1));
        pacmanBoard.setEntry(position2, BoardItem.BIG_DOT);
        assertEquals(BoardItem.BIG_DOT, pacmanBoard.getEntry(position2));
        pacmanBoard.setEntry(position3, BoardItem.BIG_DOT_SPAWN);
        assertEquals(BoardItem.BIG_DOT_SPAWN, pacmanBoard.getEntry(position3));
        pacmanBoard.setEntry(position4, BoardItem.WALL);
        assertEquals(BoardItem.WALL, pacmanBoard.getEntry(position4));
        pacmanBoard.setEntry(position5, BoardItem.NONE);
        assertEquals(BoardItem.NONE, pacmanBoard.getEntry(position5));


        pacmanBoard.setEntry(position2, BoardItem.GHOST_SPAWN);
        assertEquals(BoardItem.GHOST_SPAWN, pacmanBoard.getEntry(position2));
        pacmanBoard.setEntry(position3, BoardItem.GHOST_SPAWN);
        assertEquals(BoardItem.GHOST_SPAWN, pacmanBoard.getEntry(position3));
        assertEquals(BoardItem.NONE, pacmanBoard.getEntry(position2));

        pacmanBoard.setEntry(position4, BoardItem.PACMAN_SPAWN);
        assertEquals(BoardItem.PACMAN_SPAWN, pacmanBoard.getEntry(position4));
        pacmanBoard.setEntry(position5, BoardItem.PACMAN_SPAWN);
        assertEquals(BoardItem.PACMAN_SPAWN, pacmanBoard.getEntry(position5));
        assertEquals(BoardItem.NONE, pacmanBoard.getEntry(position4));
    }

    @Test(expected = NullPointerException.class)
    public void getEntry1(){
        Position position = null;
        pacmanBoard.getEntry(position);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getEntry2(){
        Position position = new Position(10,2);
        pacmanBoard.getEntry(position);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getEntry3(){
        Position position = new Position(2,10);
        pacmanBoard.getEntry(position);

    }

    public void getEntry4(){
        Position position = new Position(10,10);
        pacmanBoard.getEntry(position);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void eatDot1() {
        Position position = new Position(1,8);
        pacmanBoard.eatDot(position);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void eatDot2() {
        Position position = new Position(8,1);
        pacmanBoard.eatDot(position);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void eatDot3() {
        Position position = new Position(8,8);
        pacmanBoard.eatDot(position);
    }

    @Test(expected = NullPointerException.class)
    public void eatDot4() {
        Position position = null;
        pacmanBoard.eatDot(position);
    }

    @Test
    public void eatDot5() {
        position1 = new Position(1,2);
        position2 = new Position(2,4);
        position3 = new Position(6,3);
        position4 = new Position(5,6);
        position5 = new Position(7,2);

        pacmanBoard.setEntry(position1, BoardItem.DOT);
        assertEquals(BoardItem.DOT,pacmanBoard.getEntry(position1));
        assertEquals(BoardItem.DOT,pacmanBoard.eatDot(position1));
        assertEquals(BoardItem.NONE,pacmanBoard.getEntry(position1));
        pacmanBoard.setEntry(position2, BoardItem.BIG_DOT);
        assertEquals(BoardItem.BIG_DOT,pacmanBoard.getEntry(position2));
        assertEquals(BoardItem.BIG_DOT,pacmanBoard.eatDot(position2));
        assertEquals(BoardItem.BIG_DOT_SPAWN,pacmanBoard.getEntry(position2));
        pacmanBoard.setEntry(position3, BoardItem.WALL);
        assertEquals(BoardItem.WALL,pacmanBoard.getEntry(position3));
        assertEquals(BoardItem.WALL,pacmanBoard.eatDot(position3));
        assertEquals(BoardItem.WALL,pacmanBoard.getEntry(position3));
        pacmanBoard.setEntry(position4,BoardItem.PACMAN_SPAWN);
        assertEquals(BoardItem.PACMAN_SPAWN,pacmanBoard.getEntry(position4));
        assertEquals(BoardItem.PACMAN_SPAWN,pacmanBoard.eatDot(position4));
        assertEquals(BoardItem.PACMAN_SPAWN,pacmanBoard.getEntry(position4));
        pacmanBoard.setEntry(position5,BoardItem.NONE);
        assertEquals(BoardItem.NONE,pacmanBoard.getEntry(position5));
        assertEquals(BoardItem.NONE,pacmanBoard.eatDot(position5));
        assertEquals(BoardItem.NONE,pacmanBoard.getEntry(position5));
        pacmanBoard.setEntry(position5,BoardItem.GHOST_SPAWN);
        assertEquals(BoardItem.GHOST_SPAWN,pacmanBoard.getEntry(position5));
        assertEquals(BoardItem.GHOST_SPAWN,pacmanBoard.eatDot(position5));
        assertEquals(BoardItem.GHOST_SPAWN,pacmanBoard.getEntry(position5));
        pacmanBoard.setEntry(position5,BoardItem.BIG_DOT_SPAWN);
        assertEquals(BoardItem.BIG_DOT_SPAWN,pacmanBoard.getEntry(position5));
        assertEquals(BoardItem.BIG_DOT_SPAWN,pacmanBoard.eatDot(position5));
        assertEquals(BoardItem.BIG_DOT_SPAWN,pacmanBoard.getEntry(position5));
    }

    @Test
    public void getGhostSpawn() {
        assertNull(pacmanBoard.getGhostSpawn());
        pacmanBoard.setEntry(position1,BoardItem.GHOST_SPAWN);
        assertEquals(new Position(1,2),pacmanBoard.getGhostSpawn());
        pacmanBoard.setEntry(position5,BoardItem.GHOST_SPAWN);
        assertEquals(new Position(7,2),pacmanBoard.getGhostSpawn());
        assertEquals(BoardItem.NONE,pacmanBoard.getEntry(position1));
    }

    @Test
    public void getPacmanSpawn() {
        assertNull(pacmanBoard.getPacmanSpawn());
        pacmanBoard.setEntry(position2,BoardItem.PACMAN_SPAWN);
        assertEquals(new Position(2,4),pacmanBoard.getPacmanSpawn());
        pacmanBoard.setEntry(position4,BoardItem.PACMAN_SPAWN);
        assertEquals(new Position(5,6),pacmanBoard.getPacmanSpawn());
        assertEquals(BoardItem.NONE,pacmanBoard.getEntry(position2));
    }


    @Test
    public void isEmpty() {
        assertTrue(pacmanBoard.isEmpty());
        pacmanBoard.setEntry(position1,BoardItem.GHOST_SPAWN);
        assertTrue(pacmanBoard.isEmpty());
        pacmanBoard.setEntry(position2,BoardItem.PACMAN_SPAWN);
        assertTrue(pacmanBoard.isEmpty());
        pacmanBoard.setEntry(position3,BoardItem.BIG_DOT_SPAWN);
        assertTrue(pacmanBoard.isEmpty());
        pacmanBoard.setEntry(position4,BoardItem.DOT);
        assertFalse(pacmanBoard.isEmpty());
        pacmanBoard.setEntry(position5,BoardItem.BIG_DOT);
        assertFalse(pacmanBoard.isEmpty());
        PacmanBoard pacmanBoard1 = new PacmanBoard(5,6);
        pacmanBoard1.setEntry(new Position(0,0),BoardItem.BIG_DOT);
        assertFalse(pacmanBoard1.isEmpty());
    }

    @Test
    public void reset1() {
        pacmanBoard.reset();
        for (int x = 0; x < pacmanBoard.getWidth(); x++){
            for (int y = 0; y < pacmanBoard.getHeight(); y++){
                if (x == 0 || y == 0 ||
                        x == pacmanBoard.getWidth() - 1 ||
                        y == pacmanBoard.getHeight() - 1){
                    assertEquals(BoardItem.WALL,pacmanBoard.getEntry(new Position(x,y)));
                }
            }
        }
        for (int x = 0; x < pacmanBoard.getWidth(); x++){
            for (int y = 0; y < pacmanBoard.getHeight(); y++){
                if (x != 0 && y != 0 &&
                        x != pacmanBoard.getWidth() - 1 &&
                        y != pacmanBoard.getHeight() - 1){
                    assertEquals(BoardItem.DOT,pacmanBoard.getEntry(new Position(x,y)));
                }
            }
        }

    }

    @Test
    public void reset2(){
        for (int x = 0; x < pacmanBoard.getWidth(); x++){
            for (int y = 0; y < pacmanBoard.getHeight(); y++){
                if (x != 0 && y != 0 &&
                        x != pacmanBoard.getWidth() - 1 &&
                        y != pacmanBoard.getHeight() - 1){
                    pacmanBoard.setEntry(new Position(x,y),BoardItem.BIG_DOT_SPAWN);
                }
            }
        }
        pacmanBoard.reset();
        for (int x = 0; x < pacmanBoard.getWidth(); x++){
            for (int y = 0; y < pacmanBoard.getHeight(); y++){
                if (x != 0 && y != 0 &&
                        x != pacmanBoard.getWidth() - 1 &&
                        y != pacmanBoard.getHeight() - 1){
                    assertEquals(BoardItem.BIG_DOT,pacmanBoard.getEntry(new Position(x,y)));
                }
            }
        }
    }

    @Test
    public void reset3(){
        pacmanBoard.setEntry(position5,BoardItem.BIG_DOT_SPAWN);
        pacmanBoard.setEntry(position4,BoardItem.BIG_DOT);
        pacmanBoard.setEntry(position3,BoardItem.WALL);
        pacmanBoard.setEntry(position2,BoardItem.GHOST_SPAWN);
        pacmanBoard.setEntry(position1,BoardItem.PACMAN_SPAWN);
        pacmanBoard.setEntry(position6,BoardItem.DOT);
        pacmanBoard.reset();
        for (int x = 0; x < pacmanBoard.getWidth(); x++){
            for (int y = 0; y < pacmanBoard.getHeight(); y++){
                if (new Position(x,y).equals(position5) || new Position(x,y).equals(position4)){
                    assertEquals(BoardItem.BIG_DOT,pacmanBoard.getEntry(new Position(x,y)));
                }
                if (x != 0 && y != 0 &&
                        x != pacmanBoard.getWidth() - 1 &&
                        y != pacmanBoard.getHeight() - 1) {
                    if (!(new Position(x, y).equals(position3)) &&
                            !(new Position(x, y).equals(position2)) &&
                            !(new Position(x, y).equals(position1))) {
                        assertEquals(BoardItem.DOT, pacmanBoard.getEntry(new Position(x, y)));
                    }
                }
            }
        }
        assertEquals(BoardItem.WALL,pacmanBoard.getEntry(position3));
        assertEquals(BoardItem.GHOST_SPAWN,pacmanBoard.getEntry(position2));
        assertEquals(BoardItem.PACMAN_SPAWN,pacmanBoard.getEntry(position1));
    }
}
