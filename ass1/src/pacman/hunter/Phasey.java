package pacman.hunter;

import pacman.ghost.Ghost;
import pacman.ghost.Phase;

/**
 * A specified hunter whose special allows the hunter to go through a
 * ghost without killing the ghost or being killed by the ghost if the
 * phase type of the ghost is not Phase.FRIGHTENED
 */
public class Phasey extends Hunter {

    public Phasey(){
        super();
    }

    public Phasey(Hunter original){
        super(original);
    }

    /**
     * If Phasey's special has been activated, he can go through any
     * ghost whose phase is not Phase.FRIGHTENED. Otherwise, he will
     * do as a normal hunter.
     *
     * @param ghost the target ghost to check
     */
    @Override
    public void hit(Ghost ghost) {
        if (ghost == null){
            throw new NullPointerException();
        }

        if (!this.isSpecialActive() ||
                ghost.getPhase() == Phase.FRIGHTENED){
            super.hit(ghost);
        }
    }
}
