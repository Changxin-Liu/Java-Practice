package pacman.hunter;

import pacman.ghost.Ghost;

/**
 * A specified hunter whose special allows the hunter kill the
 * ghost without them being in a FRIGHTENED state
 */
public class Hungry extends Hunter {


    public Hungry(){
        super();
    }

    public Hungry(Hunter original){
        super(original);
    }

    /**
     * If hungry's special has been activated, he will kill a ghost without
     * them being in a FRIGHTENED state. Otherwise, he will do as a normal
     * hunter.
     *
     * @param ghost the target ghost to check
     */
    @Override
    public void hit(Ghost ghost) {
        if (ghost == null){
            throw new NullPointerException();
        }
        if(this.getPosition().equals(ghost.getPosition())){
            if(this.isSpecialActive() == true){
                ghost.kill();
            }else {
                super.hit(ghost);
            }
        }
    }
}
