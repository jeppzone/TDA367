package edu.chalmers.RunningMan.entities;

/**
 * A class to represent the power up steroid
 * @author  Jesper Olsson
 */

public class Steroid extends AbstractPhysicalObject {

    public Steroid(Position position, Size size){
        super(size, position);
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visit(this);
    }
}
