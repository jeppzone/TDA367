package edu.chalmers.RunningMan.model;

import edu.chalmers.RunningMan.model.Gravity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jesper Olsson
 */
public class GravityTest extends Assert {

    private Gravity gravity;
    private float deltaTime;

    @Before
    public void setUp(){
        gravity = new Gravity(-700f);
        deltaTime = 0.015f;
    }

    @Test
    public void testGetGravity(){
        assertEquals(gravity.getGravity(), -700f, 0);
    }

    @Test
    public void testGetVelocityY(){
        final float velocitY = 200f;

        assertEquals(gravity.getNewVelocity(velocitY, deltaTime), velocitY + gravity.getGravity() * deltaTime, 0);
    }

    @Test
    public void testGetNewYPosition(){
        final float velocityY = 300f;
        final float positionY = 100f;

        assertEquals(gravity.getNewYPosition(positionY, velocityY, deltaTime),
                positionY + velocityY*deltaTime + 0.5*gravity.getGravity()*deltaTime*deltaTime, 0.001);
    }

    @Test
    public void testGetMaximumVelocity(){
        final float velocityY = 400f;

        assertEquals(gravity.getNewVelocity(velocityY, deltaTime), 300f, 0.01f);
    }
}