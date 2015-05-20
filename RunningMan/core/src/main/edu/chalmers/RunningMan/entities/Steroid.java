package edu.chalmers.RunningMan.entities;

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
    public void acceptVisitor(IVisitor visitor)
    {
        if(!isPickedUp() && visitor instanceof Player) {
            visitor.visit(this);
            setPickedUp(true);
            timer = new Timer(10);
            timer.start();
        }
    }

    public void updateTime(float deltaTime){
        timer.update(deltaTime);
    }

    public Timer getTimer(){
        return timer;
    }

    public boolean isTimeUp(){
        return getTimer().getTimeInteger() >= MAX_TIME;
    }
}
