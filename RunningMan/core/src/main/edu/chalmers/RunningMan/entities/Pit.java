package edu.chalmers.RunningMan.entities;

/**
 * Created by Kvist1 on 2015-05-12.
 */
public class Pit extends AbstractPhysicalObject {

    public Pit(Position position, Size size) {
        super(size, position);
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visit(this);
    }
}
