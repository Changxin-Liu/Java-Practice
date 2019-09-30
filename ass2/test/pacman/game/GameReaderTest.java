package pacman.game;

import org.junit.Test;
import pacman.ghost.Ghost;
import pacman.ghost.GhostType;
import pacman.util.Position;
import pacman.util.UnpackableException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class GameReaderTest {


    @Test
    public void read() throws UnpackableException, IOException {
        String gameReaderTest = ";" + System.lineSeparator() +
                ";" + System.lineSeparator() +
                ";" + System.lineSeparator() +
                "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";
        BufferedReader bufferedReader = new BufferedReader
                (new StringReader(gameReaderTest));


        PacmanGame pacmanGame;

        try {
            pacmanGame = GameReader.read(bufferedReader);

        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }

        assertEquals("GameReaderTest", pacmanGame.getTitle());
        assertEquals("Changxin Liu s4524500", pacmanGame.getAuthor());
        assertEquals("8,7", new Position(pacmanGame.getBoard().getWidth(),
                pacmanGame.getBoard().getHeight()).toString());
        assertEquals(999, pacmanGame.getScores().getScore());
        assertEquals("5,4,DOWN,15,HUNGRY", pacmanGame.getHunter().toString());
        for (Ghost ghost : pacmanGame.getGhosts()) {
            if (ghost.getType() == GhostType.BLINKY) {
                assertEquals("3,2,LEFT,FRIGHTENED:12", ghost.toString());
            } else if (ghost.getType() == GhostType.INKY) {
                assertEquals("4,5,RIGHT,CHASE:10", ghost.toString());
            } else if (ghost.getType() == GhostType.PINKY) {
                assertEquals("1,5,UP,SCATTER:2", ghost.toString());
            } else if (ghost.getType() == GhostType.CLYDE) {
                assertEquals("3,3,UP,SCATTER:8", ghost.toString());
            }
        }

        assertEquals("[Ben : 32, Joe : 22, Tony : 99]",
                pacmanGame.getScores().getEntriesByName().toString());
        assertEquals("XXXXXXXX\n" +
                "X010000X\n" +
                "XP00001X\n" +
                "X000X00X\n" +
                "X$00000X\n" +
                "XB0000bX\n" +
                "XXXXXXXX", pacmanGame.getBoard().toString());
    }

    @Test (expected = UnpackableException.class)
    public void read2() throws UnpackableException, IOException {
        String test2 = "" + System.lineSeparator() +
                "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scres]" + System.lineSeparator() +
                "Ben : 32" + System.lineSeparator() +
                "Joe : 22" + System.lineSeparator() +
                "Tony : 99";
        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test2));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read3() throws UnpackableException, IOException {
        String test3 = "[Bord]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test3));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read4() throws UnpackableException, IOException {
        String test4 = "[Board]" + System.lineSeparator() +
                "8@,7@" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test4));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read5() throws UnpackableException, IOException {
        String test5 = ";" + System.lineSeparator() +
                ";" + System.lineSeparator() +
                ";" + System.lineSeparator() +
                "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP0001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test5));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read6() throws UnpackableException, IOException {
        String test6 = ";" + System.lineSeparator() +
                ";" + System.lineSeparator() +
                ";" + System.lineSeparator() +
                "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test6));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read7() throws UnpackableException, IOException {
        String test7 = ";" + System.lineSeparator() +
                ";" + System.lineSeparator() +
                ";" + System.lineSeparator() +
                "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB00?0bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test7));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read8() throws UnpackableException, IOException {
        String test8 = ";" + System.lineSeparator() +
                ";" + System.lineSeparator() +
                ";" + System.lineSeparator() +
                "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test8));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read9() throws UnpackableException, IOException {
        String test9 = ";" + System.lineSeparator() +
                ";" + System.lineSeparator() +
                ";" + System.lineSeparator() +
                "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Gme]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test9));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read10() throws UnpackableException, IOException {
        String test10 = ";" + System.lineSeparator() +
                ";" + System.lineSeparator() +
                ";" + System.lineSeparator() +
                "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test10));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read11() throws UnpackableException, IOException {
        String test11 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "tit le = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test11));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read12() throws UnpackableException, IOException {
        String test12 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "autho;r = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test12));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read13() throws UnpackableException, IOException {
        String test13 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "livges = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test13));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read14() throws UnpackableException, IOException {
        String test14 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 1:0" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test14));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read15() throws UnpackableException, IOException {
        String test15 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = -3" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test15));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read16() throws UnpackableException, IOException {
        String test16 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "levvel = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test16));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read17() throws UnpackableException, IOException {
        String test17 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5p" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test17));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read18() throws UnpackableException, IOException {
        String test18 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = -5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test18));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read19() throws UnpackableException, IOException {
        String test19 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score1 = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test19));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read20() throws UnpackableException, IOException {
        String test20 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 99?9" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test20));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read21() throws UnpackableException, IOException {
        String test21 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = -999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test21));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read22() throws UnpackableException, IOException {
        String test22 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hu/nter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test22));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read23() throws UnpackableException, IOException {
        String test23 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,down,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test23));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read24() throws UnpackableException, IOException {
        String test24 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUN?GRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test24));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read25() throws UnpackableException, IOException {
        String test25 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "Inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test25));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read26() throws UnpackableException, IOException {
        String test26 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE,10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test26));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read27() throws UnpackableException, IOException {
        String test27 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,frightened:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test27));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read28() throws UnpackableException, IOException {
        String test28 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:-12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test28));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read29() throws UnpackableException, IOException {
        String test29 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3 ,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test29));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read30() throws UnpackableException, IOException {
        String test30 = ";" + System.lineSeparator() +
                ";" + System.lineSeparator() +
                ";" + System.lineSeparator() +
                "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER: 8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test30));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read31() throws UnpackableException, IOException {
        String test31 = ";" + System.lineSeparator() +
                ";" + System.lineSeparator() +
                ";" + System.lineSeparator() +
                "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER :2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test31));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read32() throws UnpackableException, IOException {
        String test32 = ";" + System.lineSeparator() +
                ";" + System.lineSeparator() +
                ";" + System.lineSeparator() +
                "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,scatter:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";
        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test32));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read33() throws UnpackableException, IOException {
        String test33 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test33));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read34() throws UnpackableException, IOException {
        String test34 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Score]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test34));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read35() throws UnpackableException, IOException {
        String test35 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "lives = 10" + System.lineSeparator() +
                "level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:3a2" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test35));

        try{
            PacmanGame pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read36() throws UnpackableException, IOException {
        String test36 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author   = Changxin Liu s4524500" + System.lineSeparator() +
                "lives =  10" + System.lineSeparator() +
                "level = 5 " + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4 ,5, RIGHT,CHASE: 10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben: 32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99" + System.lineSeparator();

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test36));
        PacmanGame pacmanGame;
        try {
            pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e) {
            throw new UnpackableException();
        } catch (IOException e) {
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read37() throws UnpackableException, IOException {
        String test37 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author   = Changxin Liu s4524500" + System.lineSeparator() +
                "lives =  10" + System.lineSeparator() +
                "level = 5 " + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4 ,5, RIGHT,CHASE: 10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:-32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99";

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test37));
        PacmanGame pacmanGame;
        try {
            pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e) {
            throw new UnpackableException();
        } catch (IOException e) {
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read38() throws UnpackableException, IOException {
        String test38 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author   = Changxin Liu s4524500" + System.lineSeparator() +
                "lives =  10" + System.lineSeparator() +
                "level = 5 " + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4 ,5, RIGHT,CHASE: 10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                ":32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99" + System.lineSeparator();

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test38));
        PacmanGame pacmanGame;
        try {
            pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e) {
            throw new UnpackableException();
        } catch (IOException e) {
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read39() throws UnpackableException, IOException {
        String test39 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = GameReaderTest" + System.lineSeparator() +
                "author   = Changxin Liu s4524500" + System.lineSeparator() +
                "lives =  10" + System.lineSeparator() +
                "level = 5 " + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4 ,5, RIGHT,CHASE: 10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben = :32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99" + System.lineSeparator();

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test39));
        PacmanGame pacmanGame;
        try {
            pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e) {
            throw new UnpackableException();
        } catch (IOException e) {
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read40() throws UnpackableException, IOException {
        String test40 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = " + System.lineSeparator() +
                "author   = Changxin Liu s4524500" + System.lineSeparator() +
                "lives =  10" + System.lineSeparator() +
                "level = 5 " + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4 ,5, RIGHT,CHASE: 10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99" + System.lineSeparator();

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test40));
        PacmanGame pacmanGame;
        try {
            pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e) {
            throw new UnpackableException();
        } catch (IOException e) {
            throw new IOException();
        }
    }

    @Test (expected = UnpackableException.class)
    public void read41() throws UnpackableException, IOException {
        String test41 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = " + System.lineSeparator() +
                "author   = Changxin Liu s4524500" + System.lineSeparator() +
                "lives =  10" + System.lineSeparator() +
                "level = 5 " + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4 ,5, RIGHT,CHASE: 10" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99" + System.lineSeparator();

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test41));
        PacmanGame pacmanGame;
        try {
            pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e) {
            throw new UnpackableException();
        } catch (IOException e) {
            throw new IOException();
        }
    }

    @Test
    public void read42() throws UnpackableException, IOException {
        String gameReaderTest42 =
                "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title   = GameReaderTest" + System.lineSeparator() +
                "blinky = 3,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "lives =    10" + System.lineSeparator() +
                "  level = 5" + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter =    5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = 4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "clyde =    3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "author = Changxin Liu s4524500" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "  Ben:  32" + System.lineSeparator() +
                "Joe?:22" + System.lineSeparator() +
                "Tony:-99";
        BufferedReader bufferedReader = new BufferedReader
                (new StringReader(gameReaderTest42));


        PacmanGame pacmanGame;

        try {
            pacmanGame = GameReader.read(bufferedReader);

        } catch (UnpackableException e){
            throw new UnpackableException();
        } catch (IOException e){
            throw new IOException();
        }

        assertEquals("GameReaderTest", pacmanGame.getTitle());
        assertEquals("Changxin Liu s4524500", pacmanGame.getAuthor());
        assertEquals("8,7", new Position(pacmanGame.getBoard().getWidth(),
                pacmanGame.getBoard().getHeight()).toString());
        assertEquals(999, pacmanGame.getScores().getScore());
        assertEquals("5,4,DOWN,15,HUNGRY", pacmanGame.getHunter().toString());
        for (Ghost ghost : pacmanGame.getGhosts()) {
            if (ghost.getType() == GhostType.BLINKY) {
                assertEquals("3,2,LEFT,FRIGHTENED:12", ghost.toString());
            } else if (ghost.getType() == GhostType.INKY) {
                assertEquals("4,5,RIGHT,CHASE:10", ghost.toString());
            } else if (ghost.getType() == GhostType.PINKY) {
                assertEquals("1,5,UP,SCATTER:2", ghost.toString());
            } else if (ghost.getType() == GhostType.CLYDE) {
                assertEquals("3,3,UP,SCATTER:8", ghost.toString());
            }
        }

        assertEquals("[Ben : 32]",
                pacmanGame.getScores().getEntriesByName().toString());
        assertEquals("XXXXXXXX\n" +
                "X010000X\n" +
                "XP00001X\n" +
                "X000X00X\n" +
                "X$00000X\n" +
                "XB0000bX\n" +
                "XXXXXXXX", pacmanGame.getBoard().toString());
    }

    @Test (expected = UnpackableException.class)
    public void read43() throws UnpackableException, IOException {
        String test41 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = Tont" + System.lineSeparator() +
                "author   = Changxin Liu s4524500" + System.lineSeparator() +
                "lives =  10" + System.lineSeparator() +
                "level = 5 " + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = -4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 13,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99" + System.lineSeparator();

        BufferedReader bufferedReader1 = new BufferedReader(new StringReader(test41));
        PacmanGame pacmanGame;
        try {
            pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e) {
            throw new UnpackableException();
        } catch (IOException e) {
            throw new IOException();
        }
    }

    @Test (expected = IOException.class)
    public void read44() throws UnpackableException, IOException {
        String test41 = "[Board]" + System.lineSeparator() +
                "8,7" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "X010000X" + System.lineSeparator() +
                "XP00001X" + System.lineSeparator() +
                "X000X00X" + System.lineSeparator() +
                "X$00000X" + System.lineSeparator() +
                "XB0000bX" + System.lineSeparator() +
                "XXXXXXXX" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Game]" + System.lineSeparator() +
                "title = Tont" + System.lineSeparator() +
                "author   = Changxin Liu s4524500" + System.lineSeparator() +
                "lives =  10" + System.lineSeparator() +
                "level = 5 " + System.lineSeparator() +
                "score = 999" + System.lineSeparator() +
                "hunter = 5,4,DOWN,15,HUNGRY" + System.lineSeparator() +
                "inky = -4,5,RIGHT,CHASE:10" + System.lineSeparator() +
                "blinky = 13,2,LEFT,FRIGHTENED:12" + System.lineSeparator() +
                "clyde = 3,3,UP,SCATTER:8" + System.lineSeparator() +
                "pinky = 1,5,UP,SCATTER:2" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "[Scores]" + System.lineSeparator() +
                "Ben:32" + System.lineSeparator() +
                "Joe:22" + System.lineSeparator() +
                "Tony:99" + System.lineSeparator();

        BufferedReader bufferedReader1 = new BufferedReader(new FileReader("Tony.map"));
        PacmanGame pacmanGame;
        try {
            pacmanGame = GameReader.read(bufferedReader1);
        } catch (UnpackableException e) {
            throw new UnpackableException();
        } catch (IOException e) {
            throw new IOException();
        }
    }
}
