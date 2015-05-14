package edu.chalmers.RunningMan.entities;

/**
 * Created by Jesper on 5/14/2015.
 */
public class Time {
    private float currentTime;

    public Time(){
        currentTime = 0;
    }

    public void update(float deltaTime){
        currentTime += deltaTime;
    }

    public int getTimeInteger(){
        return (int)currentTime;
    }

    @Override
    public String toString(){
        return "Current time is: " + getTimeInteger();
    }
}
