package edu.chalmers.RunningMan.entities;

/**
 * Created by Jesper on 4/22/2015.
 */
public class Physics {
    private static final float GRAVITY_CONSTANT = 9.82f;

    public static float getNewVelocity(float currentVelocityY, int deltaTime){
        float nextVelocity = currentVelocityY + GRAVITY_CONSTANT * deltaTime;
        if(nextVelocity > 1f){
            return 1f;
        }else{
            return nextVelocity;
        }
    }
}
