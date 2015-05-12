package edu.chalmers.RunningMan.entities;

/**
 * Created by Jesper on 5/7/2015.
 */
public class FinishObject extends AbstractPhysicalObject {

    public FinishObject(Position position, Size size){
        super(size, position);
    }
    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor. visit(this);
    }
}
