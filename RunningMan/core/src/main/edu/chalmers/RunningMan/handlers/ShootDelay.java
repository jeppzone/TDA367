package edu.chalmers.RunningMan.handlers;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Armand on 2015-05-05.
 */
public class ShootDelay implements Runnable {
    Timer timer;
    TimerTask task;
    @Override
    public void run() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

            }
        },0,500);


    }
}
