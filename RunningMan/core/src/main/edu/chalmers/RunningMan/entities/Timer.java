package edu.chalmers.RunningMan.entities;

/**
 * Created by Jesper on 5/14/2015.
 */
public class Timer {
    private float currentTime;
    private final float maxTime;
    private boolean hasStarted = false;

    public Timer(float maxTime){
        this.maxTime = maxTime;
        resetTime();
    }

    /**
     * @return the time at which the timer starts
     */
    public float getMaxTime(){
        return maxTime;
    }

    /**
     * @return true if the timer has started
     */
    public boolean hasStarted(){
        return hasStarted;
    }

    /**
     * @return the current time left of the timer rounded down to an integer
     */
    public int getTimeLeftInteger(){
        return (int)getMaxTime() - getTimeInteger();
    }

    /**
     * @return the exact current time left of the timer
     */
    public float getTimeLeftFloat(){
        return getMaxTime() - getTimeFloat();
    }

    /**
     * Sets the time counter to zero
     */
    public void resetTime(){
        currentTime = 0f;
    }

    /**
     * Incremets the time, if the timer has started
     * @param deltaTime the time to increment with
     */
    public void update(float deltaTime){
        if(hasStarted) {
            currentTime += deltaTime;
        }
    }

    /**
     * @return true if the timer has run out, false otherwise
     */
    public boolean isTimeUp(){
        return getTimeFloat() >= getMaxTime();
    }

    /**
     * @return the current time rounded down to integer
     */
    public int getTimeInteger(){
        return (int)currentTime;
    }

    /**
     * @return the exact current time
     */
    public float getTimeFloat(){
        return currentTime;
    }

    /**
     * Lets the timer know that it is supposed to start counting
     */
    public void start(){
        hasStarted = true;
    }

    @Override
    public String toString(){
        return "Current time is: " + getTimeInteger();
    }
}
