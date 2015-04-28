package edu.chalmers.RunningMan.entities;

/**
 * Created by Jesper on 4/28/2015.
 */
public abstract class AbstractCollectibleObject extends AbstractPhysicalObject {
    protected boolean pickedUp;
    public AbstractCollectibleObject(Size size, Position position){
        super(size ,position);
        setPickedUp(false);
    }
    public void setPickedUp(boolean pickedUp){
        this.pickedUp = pickedUp;
    }
    public boolean isPickedUp(){
        return pickedUp;
    }
    abstract public void acceptVisitor(IVisitor visitor);

}
