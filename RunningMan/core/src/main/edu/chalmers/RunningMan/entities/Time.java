package edu.chalmers.RunningMan.entities;

/**
 * Created by Jesper on 5/14/2015.
 */
public class Time {
    private float currentTime;
    private float maxTime;
    private boolean hasStarted = false;

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
        if(hasStarted) {
            currentTime += deltaTime;
        }
    }
    public boolean isTimeUp(){
        return getTimeInteger() >= getMaxTime();
    }
    public int getTimeInteger(){
        return (int)currentTime;
    }
    public double getTimeFloat(){
        return currentTime;
    }

    public void start(){
        hasStarted = true;
    }

    @Override
    public String toString(){
        return "Current time is: " + getTimeInteger();
    }
}
