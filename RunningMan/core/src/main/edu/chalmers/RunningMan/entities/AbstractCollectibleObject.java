package edu.chalmers.RunningMan.entities;

/**
 * @author Jesper Olsson
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
