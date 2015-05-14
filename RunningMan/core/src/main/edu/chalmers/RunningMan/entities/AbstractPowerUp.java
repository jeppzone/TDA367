package edu.chalmers.RunningMan.entities;

/**
 * Abstract class for collectible objects
 * @author Jesper Olsson
 */
public abstract class AbstractPowerUp extends AbstractPhysicalObject implements ICollectible {
    protected boolean pickedUp;
    protected Time time;
    public AbstractPowerUp(Size size, Position position){
        super(size ,position);
        setPickedUp(false);
    }
    public void setPickedUp(boolean pickedUp){
        this.pickedUp = pickedUp;
    }
    public boolean isPickedUp(){
        return pickedUp;
    }
    public void updateTime(float deltaTime){
        if(isPickedUp()){
            time.update(deltaTime);
        }
    }

    public abstract boolean isTimeUp();

    public Time getTime(){
        return time;
    }
    abstract public void acceptVisitor(IVisitor visitor);

}
