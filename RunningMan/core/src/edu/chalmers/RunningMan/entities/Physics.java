package edu.chalmers.RunningMan.entities;

/**
 * Created by Jesper on 4/22/2015.
 */
public class Physics {
    private static final float GRAVITY_CONSTANT = 9.82f;

    public static float getNewVelocity(float currentVelocityY, float deltaTime){
        float nextVelocity = currentVelocityY + GRAVITY_CONSTANT * deltaTime;
        if(nextVelocity > 1f){
            return 1f;
        }else{
            return nextVelocity;
        }
    }

    public static float getNewYPosition(float positonY, float velocityY, float deltaTime){
        return positonY + velocityY* deltaTime + 0.5f*GRAVITY_CONSTANT*deltaTime*deltaTime;
    }
}
