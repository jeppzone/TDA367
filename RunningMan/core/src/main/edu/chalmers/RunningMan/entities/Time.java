package edu.chalmers.RunningMan.entities;

/**
 * Created by Jesper on 5/14/2015.
 */
public class Time {
    private float currentTime;
    private int maxTime;

    public Time(int maxTime){
        this.maxTime = maxTime;
        currentTime = 0;
    }

    public int getMaxTime(){
        return maxTime;
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
