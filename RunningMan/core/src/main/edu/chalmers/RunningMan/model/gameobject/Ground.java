package edu.chalmers.RunningMan.model.gameobject;

import edu.chalmers.RunningMan.model.Position;
import edu.chalmers.RunningMan.model.Size;

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
