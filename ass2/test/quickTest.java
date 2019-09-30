import pacman.board.BoardItem;
import pacman.board.PacmanBoard;
import pacman.game.Entity;
import pacman.game.GameReader;
import pacman.game.GameWriter;
import pacman.game.PacmanGame;
import pacman.ghost.Blinky;
import pacman.ghost.Ghost;
import pacman.ghost.GhostType;
import pacman.ghost.Phase;
import pacman.hunter.Hungry;
import pacman.hunter.Hunter;
import pacman.hunter.Speedy;
import pacman.score.ScoreBoard;
import pacman.util.Direction;
import pacman.util.Position;
import pacman.util.UnpackableException;

import java.io.*;
import java.util.ArrayList;

public class quickTest {
    public static void main(String[] args) {
        ScoreBoard scoreBoard1 = new ScoreBoard();
        scoreBoard1.increaseScore(10);
        scoreBoard1.setScore("Tony",19);
        scoreBoard1.setScore("Joe",11);

        ScoreBoard scoreBoard2 = new ScoreBoard();
        scoreBoard2.increaseScore(10);
        scoreBoard2.setScore("Joe",11);
        scoreBoard2.setScore("Tony",19);
        System.out.println(scoreBoard1.equals(scoreBoard2));
        System.out.println(scoreBoard1.hashCode());
        System.out.println(scoreBoard2.hashCode());

        PacmanBoard pacmanBoard1 = new PacmanBoard(3,4);
        PacmanBoard pacmanBoard2 = new PacmanBoard(9,9);
        System.out.println(pacmanBoard1.hashCode());
        System.out.println(pacmanBoard2.hashCode());

        System.out.println(pacmanBoard1.equals(pacmanBoard2));
        System.out.println(pacmanBoard2.toString());

        Entity entity =new Entity() {
            @Override
            public void move(PacmanGame game) {

            }
        };
        Entity entity1 = new Entity() {
            @Override
            public void move(PacmanGame game) {

            }
        };
        entity.setPosition(new Position(5,6));
        entity.setDirection(Direction.LEFT);

        entity1.setPosition(new Position(5,6));
        entity1.setDirection(Direction.UP);
        System.out.println(entity.equals(entity1));
        System.out.println(entity.hashCode());
        System.out.println(entity1.hashCode());
        System.out.println(entity1.toString());

        Hunter hunter = new Hunter() {
            @Override
            public boolean isDead() {
                return super.isDead();
            }
        };

        Hunter hunter1 = new Hunter() {
            @Override
            public boolean isDead() {
                return super.isDead();
            }
        };

        Blinky blinky = new Blinky();
        blinky.setPosition(new Position(3,4));
        hunter.setPosition(new Position(3,4));
        hunter1.setPosition(new Position(3,4));
        hunter.setDirection(Direction.DOWN);
        hunter1.setDirection(Direction.DOWN);
        hunter.activateSpecial(20);
        hunter1.activateSpecial(20);
        hunter1.hit(blinky);
        System.out.println(hunter.equals(hunter1));
        System.out.println(hunter.hashCode());
        System.out.println(hunter1.hashCode());
        System.out.println(hunter.toString());


        Position position = new Position(3,4);
        System.out.println(position.toString());

        Hungry hungry = new Hungry();
        hungry.setPosition(new Position(4,5));
        hungry.setDirection(Direction.LEFT);
        hungry.activateSpecial(12);
        System.out.println(hungry.toString());

        Ghost ghost = new Ghost() {
            @Override
            public String getColour() {
                return null;
            }

            @Override
            public GhostType getType() {
                return null;
            }

            @Override
            public Position chaseTarget(PacmanGame game) {
                return null;
            }

            @Override
            public Position home(PacmanGame game) {
                return null;
            }
        };
        Ghost ghost1 = new Ghost() {
            @Override
            public String getColour() {
                return null;
            }

            @Override
            public GhostType getType() {
                return null;
            }

            @Override
            public Position chaseTarget(PacmanGame game) {
                return null;
            }

            @Override
            public Position home(PacmanGame game) {
                return null;
            }
        };

        ghost.setPosition(new Position(9,9));
        ghost1.setPosition(new Position(3,4));
        ghost.setDirection(Direction.LEFT);
        ghost1.setDirection(Direction.LEFT);
        ghost.setPhase(Phase.CHASE, 40);
        ghost1.setPhase(Phase.CHASE, 40);
        System.out.println(ghost.equals(ghost1));
        System.out.println(ghost.toString());
        System.out.println(ghost.hashCode());
        System.out.println(ghost1.hashCode());

        PacmanBoard pacmanBoard = new PacmanBoard(15,15);
        System.out.println(pacmanBoard.toString());
        pacmanBoard.setEntry(new Position(7,7),BoardItem.PACMAN_SPAWN);
        pacmanBoard.setEntry(new Position(6,7),BoardItem.GHOST_SPAWN);
        PacmanGame pacmanGame = new PacmanGame("AAA", "Joe", new Speedy(), pacmanBoard);
        System.out.println(pacmanGame.getTitle());

        Speedy speedy = (Speedy) pacmanGame.getHunter();
        System.out.println(speedy.getPosition().toString());
        speedy.setDirection(Direction.UP);
        speedy.setPosition(new Position(7,7));
        speedy.activateSpecial(2);
        speedy.move(pacmanGame);
        speedy.move(pacmanGame);



        System.out.println(speedy.getPosition().toString());
        System.out.println(pacmanGame.getHunter());

        Ghost ghost2 = new Ghost() {
            @Override
            public String getColour() {
                return null;
            }

            @Override
            public GhostType getType() {
                return null;
            }

            @Override
            public Position chaseTarget(PacmanGame game) {
                return null;
            }

            @Override
            public Position home(PacmanGame game) {
                return null;
            }
        };

        for (Direction direction:Direction.values()){
            System.out.println(direction);
        }
        System.out.println("---------------------------------------------------------");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("default.map"));
            PacmanGame pacmanGame1 = GameReader.read(bufferedReader);
//            pacmanGame1.getGhosts().remove(0);
            //System.out.println(pacmanGame1.getScores().getEntriesByName());

//            for (int y = 0; y < pacmanGame1.getBoard().getHeight(); y++){
//                for (int x = 0; x < pacmanGame1.getBoard().getWidth(); x++){
//                    System.out.print(pacmanGame1.getBoard().getEntry(new Position(x,y)).getChar());
//                }
//                System.out.println();
//            }

            StringWriter writer = new StringWriter();
            GameWriter.write(writer,pacmanGame1);
            System.out.println(writer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnpackableException e) {
            e.printStackTrace();
        }

        var items = new ArrayList<>();
    }

}
