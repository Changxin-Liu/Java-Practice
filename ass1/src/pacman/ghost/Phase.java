package pacman.ghost;

/**
 * Definition of Phase available in the game.
 */
public enum Phase {

    /**
     * a specified phase called CHASE
     */
    CHASE(20),

    /**
     * a specified phase called FRIGHTENED
     */
    FRIGHTENED(10),

    /**
     * a specified phase called SCATTER
     */
    SCATTER(30);

    // the duration of the phase
    private int duration;

    // Constructs a phase with given duration
    // @parameter the duration of a phase
    private Phase(int duration){
        this.duration = duration;
    }

    /**
     * Returns the duration of a phase
     *
     * @return one phase's duration
     */
    public int getDuration(){
        return this.duration;
    }

}
