package pacman.ghost;

/**
 * A specified ghost called "Clyde" with the given colour and type
 */
public class Clyde extends Ghost{

    public Clyde(){
        super();
    }

    /**
     * Returns the colour of Clyde
     *
     * @return Clyde's colour
     */
    public String getColour(){
        return "#e78c45";
    }

    /**
     * Returns the type of Clyde
     *
     * @return Clyde's type
     */
    public GhostType getType(){
        return GhostType.CLYDE;
    }


}
