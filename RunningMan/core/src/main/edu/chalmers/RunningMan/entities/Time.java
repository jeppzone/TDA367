package edu.chalmers.RunningMan.entities;

/**
 * Created by Jesper on 5/14/2015.
 */
public class Time {
    private float currentTime;
    private float maxTime;

    public Time(float maxTime){
        this.maxTime = maxTime;
        currentTime = 0;
    }

    public double getMaxTime(){
        return maxTime;
    }

    public void resetTime(){
        currentTime = 0;
    }

    public void update(float deltaTime){
        currentTime += deltaTime;
    }

    public int getTimeInteger(){
        return (int)currentTime;
    }
    public double getTimeFloat(){
        return currentTime;
    }

    @Override
    public String toString(){
        return "Current time is: " + getTimeInteger();
    }
}
