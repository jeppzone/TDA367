package edu.chalmers.RunningMan.entities;

public class Gravity {
    private final float GRAVITY_CONSTANT;

    public Gravity(float gravity){
        GRAVITY_CONSTANT = gravity;
    }

    public float getGravity(){
        return GRAVITY_CONSTANT;
    }

    public float getNewVelocity(float yVelocity, float delta){
        final float TERMINAL_VELOCITY = 5f;
        final float nextYVelocity =  yVelocity+this.getGravity() * delta;
        if(nextYVelocity > TERMINAL_VELOCITY){
            return TERMINAL_VELOCITY;
        }else{
            return nextYVelocity;
        }
    }

    public float getNewYPosition(float yPosition, float yVelocity, float delta){
        return (float) (yPosition + yVelocity * delta + 0.5 * this.getGravity() * delta * delta);

    }
}