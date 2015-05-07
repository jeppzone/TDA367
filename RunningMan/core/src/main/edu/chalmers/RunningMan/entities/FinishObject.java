package edu.chalmers.RunningMan.entities;

/**
 * Created by Jesper on 5/7/2015.
 */
public class FinishObject extends AbstractPhysicalObject {

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor. visit(this);
    }

    public FinishObject(Size size, Position position){
        super(size, position);
    }

}
