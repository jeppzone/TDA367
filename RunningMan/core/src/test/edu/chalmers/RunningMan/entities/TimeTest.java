package edu.chalmers.RunningMan.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Jesper on 5/16/2015.
 */
public class TimeTest extends Assert {
    private static final float DELTATIME = 0.005f;
    private static final float MAXTIME = 0.004f;
    private Time time;

    @Before
    public void setUp(){
        time = new Time(MAXTIME);

    }

    @Test
    public void testGetMaxTime(){
        assertEquals(MAXTIME, time.getMaxTime(), 0);
    }

    @Test
    public void testStartTime(){
        time.start();
        assertTrue(time.hasStarted());
    }

    @Test
    public void testUpdateTime(){
        time.start();
        time.update(DELTATIME);
        assertTrue(time.getTimeFloat() > 0);
    }

    @Test
    public void testGetTimeLeftInt(){
        assertEquals(time.getTimeLeftInteger(), (int) time.getMaxTime() - time.getTimeInteger(), 0);
    }

    @Test
    public void testGetTimeLeftFloat(){
        time.start();
        time.update(DELTATIME);
        assertEquals(time.getTimeLeftFloat(), time.getMaxTime() - time.getTimeFloat(), 0);
    }

    @Test
    public void testIsTimeUpTrue(){
        time.start();
        time.update(DELTATIME);
        assertTrue(time.isTimeUp());
    }

    @Test
    public void testIsTimeUpFalse(){
        final Time tempTime = new Time(MAXTIME + 0.002f);
        tempTime.start();
        tempTime.update(DELTATIME);
        assertFalse(tempTime.isTimeUp());
    }

    @Test
    public void testGetTimeInteger(){
        time.start();
        time.update(1.2f);
        assertEquals(time.getTimeInteger(), 1);
    }

    @Test
    public void testGetTimeFloat(){
        time.start();
        time.update(DELTATIME);
        assertEquals(DELTATIME, time.getTimeFloat(), 0);
    }
}
