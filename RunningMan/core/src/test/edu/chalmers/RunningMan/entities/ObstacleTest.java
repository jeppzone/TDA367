package edu.chalmers.RunningMan.entities;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ObstacleTest extends Assert {

    private Obstacle obstacle;
    private Size size = new Size(50,50);
    private Position position = new Position(50,50);

    @Before
    public void setUp(){
        obstacle = new Obstacle(new Position(50,50),new Size(50,50));
    }

    @Test
    public void testGetSize(){
        assertEquals(size, obstacle.getSize());
    }

    @Test
    public void testSetPosition(){
        obstacle.setPosition(position);
        assertEquals(position, obstacle.getPosition());
    }
}