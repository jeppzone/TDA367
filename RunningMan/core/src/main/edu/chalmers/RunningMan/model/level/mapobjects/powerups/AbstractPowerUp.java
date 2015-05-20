package edu.chalmers.RunningMan.model.level.mapobjects.powerups;

import edu.chalmers.RunningMan.model.AbstractPhysicalObject;
import edu.chalmers.RunningMan.model.Position;
import edu.chalmers.RunningMan.model.Size;
import edu.chalmers.RunningMan.model.level.mapobjects.IVisitor;
import edu.chalmers.RunningMan.model.Timer;

/**
 * Abstract class for collectible objects
 * @author Jesper Olsson
 */
public abstract class AbstractPowerUp extends AbstractPhysicalObject implements ICollectible {
    protected boolean pickedUp;
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
    public abstract void updateTime(float deltaTime);

    public abstract boolean isTimeUp();

    public abstract Timer getTimer();
    abstract public void acceptVisitor(IVisitor visitor);

}
