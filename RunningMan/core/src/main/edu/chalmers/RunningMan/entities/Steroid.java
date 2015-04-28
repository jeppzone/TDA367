package edu.chalmers.RunningMan.entities;

/**
 * A class to represent the power up steroid
 * @author  Jesper Olsson
 */

public class Steroid extends AbstractCollectibleObject {

    public Steroid(Position position, Size size){
        super(size, position);
    }

    @Override
    public void acceptVisitor(IVisitor visitor)
    {
        if(!isPickedUp()) {
            visitor.visit(this);
            setPickedUp(true);
        }
    }
}
