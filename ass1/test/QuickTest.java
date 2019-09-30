
import pacman.game.Entity;
import pacman.ghost.Blinky;
import pacman.ghost.Pinky;
import pacman.hunter.Hungry;
import pacman.hunter.Hunter;
import pacman.hunter.Phil;
import pacman.util.Direction;
import pacman.util.Position;

public class QuickTest {
    public static void main(String[] args) {

        // PositionTEST
        Position p1 = new Position(0,0);
        Position p2 = new Position(-2,-3);
        Position p3 = new Position(0,0);
        System.out.println(p1.equals(p2));
        System.out.println(p1.equals(p3));
        System.out.println(p1.add(p2).getX());
        System.out.println(p2.multiply(5).getX());
        System.out.println(p1.distance(p2));
        System.out.println(p1.hashCode() == p2.hashCode());
        System.out.println(p1.hashCode() == p3.hashCode());
        System.out.println("-------------------------------------------------");

        // DirectionTest
        System.out.println(Direction.DOWN.offset().getX());
        System.out.println(Direction.DOWN.offset().getY());
        System.out.println(Direction.UP.opposite());
        System.out.println("-------------------------------------------------");

        // GhostTest
        Blinky blinky = new Blinky();
        System.out.println(blinky.getPhase());
        System.out.println(blinky.phaseInfo());
        System.out.println(blinky.getType());
        System.out.println(blinky.getColour());
        System.out.println(blinky.getDirection());
        System.out.println(blinky.getPosition().getX());
        System.out.println(blinky.getPosition().getY());
        System.out.println(blinky.isDead());
        blinky.kill();
        System.out.println(blinky.isDead());
        blinky.reset();
        System.out.println(blinky.isDead());
        Phil phil = new Phil();
        Pinky pinky = new Pinky();

        System.out.println("-------------------------------------------------");



        // HunterTest
        Hungry hungry = new Hungry();
        System.out.println(hungry.getSpecialDurationRemaining());
        System.out.println(hungry.isDead());
        System.out.println(hungry.isSpecialActive());
        System.out.println(hungry.getDirection());
        System.out.println(hungry.getPosition().getX());
        System.out.println(hungry.getPosition().getY());
        hungry.activateSpecial(0);
        System.out.println(hungry.isSpecialActive());
        hungry.activateSpecial(10);
        System.out.println(hungry.isSpecialActive());
        System.out.println(hungry.getSpecialDurationRemaining());
        hungry.hit(blinky);

    }
}
