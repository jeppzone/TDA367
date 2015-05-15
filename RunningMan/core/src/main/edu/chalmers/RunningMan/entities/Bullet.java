package edu.chalmers.RunningMan.entities;

import com.badlogic.gdx.Gdx;

/**
 * A class to represent the bullet
 */

public class Bullet extends AbstractPhysicalObject{

    private final float BULLET_SPEED = 400f;
    private boolean bulletExists = true;
    private final int lastMovedDirection;
    private float initialXPosition = getPosition().getX();

    public Bullet(Size size, Position position, int lastMovedDirection){
        super(size,position);
        this.lastMovedDirection = lastMovedDirection;
    }

    /**
     * Gets the bulletspeed, negative or positive depending on direction
     * @return bulletspeed
     */
    public float getVelocity(){
        return BULLET_SPEED * lastMovedDirection;
    }

    private void setBulletGone(){
        bulletExists = false;
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
                > Gdx.graphics.getWidth()/2 ;
    }
    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visit(this);
    }

}
