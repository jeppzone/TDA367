package edu.chalmers.RunningMan.model.gameobject;

import edu.chalmers.RunningMan.model.Position;
import edu.chalmers.RunningMan.model.Size;
import edu.chalmers.RunningMan.model.Timer;

/**
 * A class to represent the power up steroid
 * @author  Jesper Olsson
 */

public class Steroid extends AbstractPowerUp {

    private static final int MAX_TIME = 10;
    private Timer timer;

    public Steroid(Position position, Size size){
        super(size, position);
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        if(!isPickedUp() && visitor instanceof Player) {
            visitor.visit(this);
            setPickedUp(true);
            timer = new Timer(10);
            timer.start();
        }
    }

    /**
     * Updates the timer of the steroid
     * @param deltaTime the time to increment with
     */
    public void updateTime(float deltaTime){
        timer.update(deltaTime);
    }

    /**
     * @return the steroid's timer object
     */
    public Timer getTimer(){
        return timer;
    }

    /**
     * @return true if the timer has run out of time, false otherwise
     */
    public boolean isTimeUp(){
        return getTimer().getTimeInteger() >= MAX_TIME;
    }
}
