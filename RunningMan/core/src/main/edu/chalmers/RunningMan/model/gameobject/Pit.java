package edu.chalmers.RunningMan.model.gameobject;

import edu.chalmers.RunningMan.model.Position;
import edu.chalmers.RunningMan.model.Size;

/**
 * A class to represent the pit fall
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
