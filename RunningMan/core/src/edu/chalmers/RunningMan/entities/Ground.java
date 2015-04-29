package edu.chalmers.RunningMan.entities;

/**
 * A class to model the ground tile
 */
public class Ground extends AbstractPhysicalObject {

    public Ground(Position position, Size size) {
        super(size, position);
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visit(this);
    }
}
