import pacman.board.BoardItem;
import pacman.board.PacmanBoard;
import pacman.game.Entity;
import pacman.ghost.Blinky;
import pacman.ghost.Ghost;
import pacman.ghost.GhostType;
import pacman.ghost.Phase;
import pacman.hunter.Hungry;
import pacman.hunter.Phasey;
import pacman.hunter.Phil;
import pacman.hunter.Speedy;
import pacman.score.ScoreBoard;
import pacman.util.Direction;
import pacman.util.Position;

import java.lang.reflect.Array;
import java.util.Collection;

import java.util.*;

public class PositionTest {
    public static void main(String[] args){
        ScoreBoard scoreboard = new ScoreBoard();
        scoreboard.setScore("!!!oe",123);
        scoreboard.setScore("!!!",123);
        scoreboard.setScore("/';lcx",984);
        scoreboard.setScore("l1c0x",984);
        scoreboard.setScore("l?c?x",984);
        scoreboard.setScore("Lcx",984);
        scoreboard.setScore("lcX",984);
        scoreboard.setScore("Ben",234);
        scoreboard.setScore("Ben",-1);
        scoreboard.setScore("ben joe",234);
        scoreboard.setScore("0Ben",235);
        scoreboard.setScore("Tony", 345);
        scoreboard.setScore(null,104);
        scoreboard.setScore("tony",0);
        scoreboard.setScore("Nora",-100);
        System.out.println(scoreboard);
        scoreboard.increaseScore(10);
        System.out.println(scoreboard.getScore());
        scoreboard.reset();
        System.out.println(scoreboard.getScore());
        System.out.println((scoreboard.getEntriesByName()));
        System.out.println(scoreboard.getEntriesByScore());
        System.out.println();

        System.out.println("-----------------------------------");

        PacmanBoard pacmanBoard = new PacmanBoard(9,9);
        System.out.println(pacmanBoard.isEmpty());
        pacmanBoard.setEntry(new Position(5,5), BoardItem.DOT);
        pacmanBoard.setEntry(new Position(5,5), BoardItem.BIG_DOT);
        System.out.println(pacmanBoard.getEntry(new Position(5,5)));
        System.out.println(pacmanBoard.isEmpty());
        System.out.println("-----------------------------------");


        Map<String, Integer> scores = new HashMap<>();
        scores.put("!!!oe",123);
        scores.put("!!!",123);
        scores.put("/';lcx",984);
        scores.put("l1c0x",984);
        scores.put("l?c?x",984);
        scores.put("Lcx",984);
        scores.put("lcX",984);
        scores.put("Ben",234);
        scores.put("ben",234);
        scores.put("0Ben",235);
        scores.put("Tony", 345);
        scores.put(null,104);
        scores.put("tony",0);
        scores.put("Nora",-100);
        scores.put(null,null);
        scores.put("nora",null);


        ScoreBoard scoreBoard1 = new ScoreBoard();
        scoreBoard1.setScores(scores);
        System.out.println(scoreBoard1.getEntriesByName());
        System.out.println(scoreBoard1.getEntriesByScore());

        System.out.println("-------------------------------------");

        Map<String, Integer> scores1 = null;
        ScoreBoard scoreBoard2 = new ScoreBoard();
        scoreBoard2.setScores(scores1);
        System.out.println(scoreBoard2.getEntriesByName());
        System.out.println(scoreBoard2.getEntriesByScore());

        System.out.println(Direction.DOWN.offset().getX());
        System.out.println(Direction.DOWN.offset().getY());

        double x = 3.5 + 5.0 / 10 * 2.0 ;
        System.out.println(x);


    }




}
