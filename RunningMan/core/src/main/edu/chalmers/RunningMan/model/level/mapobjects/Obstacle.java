package edu.chalmers.RunningMan.model.level.mapobjects;

import edu.chalmers.RunningMan.model.AbstractPhysicalObject;
import edu.chalmers.RunningMan.model.Position;
import edu.chalmers.RunningMan.model.Size;

/**
 * A class to represent Obstacle objects.
 * @author Jesper Olsson
 */
public class Obstacle extends AbstractPhysicalObject {
    public Obstacle(Position position, Size size){
        super(size, position);
    }

    @Override
    public void acceptVisitor(IVisitor visitor)
    {
        visitor.visit(this);
    }
}
