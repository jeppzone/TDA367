package edu.chalmers.RunningMan.model.gameobject;

import edu.chalmers.RunningMan.model.ICollectible;
import edu.chalmers.RunningMan.model.Position;
import edu.chalmers.RunningMan.model.Size;
import edu.chalmers.RunningMan.model.Timer;

/**
 * Abstract class for collectible gameobject
 * @author Jesper Olsson
 */
public abstract class AbstractPowerUp extends AbstractPhysicalObject implements ICollectible {
    private boolean pickedUp;

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
