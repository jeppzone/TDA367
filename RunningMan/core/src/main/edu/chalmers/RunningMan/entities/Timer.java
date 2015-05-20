package edu.chalmers.RunningMan.entities;

/**
 * Created by Jesper on 5/14/2015.
 */
public class Timer {
    private float currentTime;
    private float maxTime;
    private boolean hasStarted = false;

    public Timer(float maxTime){
        this.maxTime = maxTime;
        resetTime();
    }

    public float getMaxTime(){
        return maxTime;
    }

    public boolean hasStarted(){
        return hasStarted;
    }
    public int getTimeLeftInteger(){
        return (int)getMaxTime() - getTimeInteger();
    }
    public float getTimeLeftFloat(){
        return getMaxTime() - getTimeFloat();
    }
    public void resetTime(){
        currentTime = 0f;
    }

    public void update(float deltaTime){
        if(hasStarted) {
            currentTime += deltaTime;
        }
    }
    public boolean isTimeUp(){
        return getTimeFloat() >= getMaxTime();
    }
    public int getTimeInteger(){
        return (int)currentTime;
    }
    public float getTimeFloat(){
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
