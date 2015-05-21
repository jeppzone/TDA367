package edu.chalmers.RunningMan.model.level.mapobjects;

import edu.chalmers.RunningMan.model.AbstractPhysicalObject;
import edu.chalmers.RunningMan.model.IVisitor;
import edu.chalmers.RunningMan.model.Position;
import edu.chalmers.RunningMan.model.Size;

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
