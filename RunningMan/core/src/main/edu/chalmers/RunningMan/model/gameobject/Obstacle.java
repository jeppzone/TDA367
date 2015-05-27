package edu.chalmers.RunningMan.model.gameobject;

import edu.chalmers.RunningMan.model.Position;
import edu.chalmers.RunningMan.model.Size;

/**
 * A class to represent Obstacle gameobject.
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
