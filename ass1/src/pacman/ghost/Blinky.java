package pacman.ghost;

/**
 * A specified ghost called "Blinky" with the give colour and type
 */
public class Blinky extends Ghost{

    public Blinky(){
        super();
    }

    /**
     * Returns the colour of Blinky
     *
     * @return Blinky's colour
     */
    public String getColour(){
        return "#d54e53";
    }

    /**
     * Returns the type of Blinky
     *
     * @return Blinky's type
     */
    public GhostType getType(){
        return GhostType.BLINKY;
    }

}
