package edu.chalmers.RunningMan.model.gameobjects;

import edu.chalmers.RunningMan.model.ISize;
import edu.chalmers.RunningMan.model.Position;

/**
 * A class to represent the bullet
 */

public class Bullet extends AbstractPhysicalObject {

    private final float BULLET_SPEED = 1000f;
    private final int lastMovedDirection;
    private float initialXPosition = getPosition().getX();
    private ISize windowSize;

    public Bullet(ISize size, Position position, int lastMovedDirection,ISize windowSize){
        super(size,position);
        this.lastMovedDirection = lastMovedDirection;
        this.windowSize = windowSize;
    }

    /**
     * Gets the bulletspeed, negative or positive depending on direction
     * @return bulletspeed
     */
    public float getVelocity(){
        return BULLET_SPEED * lastMovedDirection;
    }

    /**
     * Moves the bullet in the players direction
     * @param deltaTime the time difference
     */
    public void moveBullet(float deltaTime){
            setX(getPosition().getX() + getVelocity() * deltaTime);
    }


    public boolean isOutOfBounds(){
        return getPosition().getX() < 1 || Math.abs(getPosition().getX() - initialXPosition)
                > windowSize.getWidth()/2 ;
    }
    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visit(this);
    }

}
