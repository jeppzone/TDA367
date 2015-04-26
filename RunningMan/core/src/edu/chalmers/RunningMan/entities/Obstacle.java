package edu.chalmers.RunningMan.entities;

/**
 * A class to represent Obstacle objects.
 * @author Jesper Olsson
 */
public class Obstacle extends AbstractPhysicalObject {
    public Obstacle(Position position, Size size){
        super(size, position);
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visit(this);
    }
}
