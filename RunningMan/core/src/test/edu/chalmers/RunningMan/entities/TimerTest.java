package edu.chalmers.RunningMan.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Jesper on 5/16/2015.
 */
public class TimerTest extends Assert {
    private static final float DELTATIME = 0.005f;
    private static final float MAXTIME = 0.004f;
    private Timer timer;

    @Before
    public void setUp(){
        timer = new Timer(MAXTIME);

    }

    @Test
    public void testGetMaxTime(){
        assertEquals(MAXTIME, timer.getMaxTime(), 0);
    }

    @Test
    public void testStartTime(){
        timer.start();
        assertTrue(timer.hasStarted());
    }

    @Test
    public void testUpdateTime(){
        timer.start();
        timer.update(DELTATIME);
        assertTrue(timer.getTimeFloat() > 0);
    }

    @Test
    public void testGetTimeLeftInt(){
        assertEquals(timer.getTimeLeftInteger(), (int) timer.getMaxTime() - timer.getTimeInteger(), 0);
    }

    @Test
    public void testGetTimeLeftFloat(){
        timer.start();
        timer.update(DELTATIME);
        assertEquals(timer.getTimeLeftFloat(), timer.getMaxTime() - timer.getTimeFloat(), 0);
    }

    @Test
    public void testIsTimeUpTrue(){
        timer.start();
        timer.update(DELTATIME);
        assertTrue(timer.isTimeUp());
    }

    @Test
    public void testIsTimeUpFalse(){
        final Timer tempTimer = new Timer(MAXTIME + 0.002f);
        tempTimer.start();
        tempTimer.update(DELTATIME);
        assertFalse(tempTimer.isTimeUp());
    }

    @Test
    public void testGetTimeInteger(){
        timer.start();
        timer.update(1.2f);
        assertEquals(timer.getTimeInteger(), 1);
    }

    @Test
    public void testGetTimeFloat(){
        timer.start();
        timer.update(DELTATIME);
        assertEquals(DELTATIME, timer.getTimeFloat(), 0);
    }
}
