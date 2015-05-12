package edu.chalmers.RunningMan.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jesper Olsson
 */
public class LevelTest extends Assert {

    private Level level;
    private Player player;
    private final String name = "Beach";
    private List<AbstractPhysicalObject> mapObjects;

    @Before
    public void setUp(){
        this.player = new Player(null, new Position(400, 0), new Size(50,50),100);
        mapObjects = new ArrayList<>();
        this.level = new Level(mapObjects, name);
    }

    @Test
    public void testGetName(){
        final Level level = new Level(mapObjects, name);
        assertEquals(name, level.getLevelName());
    }

    @Test
    public void testIsCollidingSameObject(){
        final Rectangle rectangle = new Rectangle(20, 20);

        assertFalse(level.isColliding(rectangle, rectangle));
    }

    @Test
    public void testIsCollidingObjectNull(){
        final Rectangle rectangle = new Rectangle(20, 20);

        assertFalse(level.isColliding(rectangle, null));
    }

    @Test
    public void testIsCollidingBothNull(){
        assertFalse(level.isColliding(null, null));
    }

    @Test
    public void testIsCollidingDifferentObjects(){
        final Rectangle firstRectangle = new Rectangle(20, 20);
        final Rectangle secondRectangle = new Rectangle(20, 39);

        assertTrue(level.isColliding(firstRectangle, secondRectangle));
    }

    @Test
    public void testCheckCollisionsWithCollisions(){
        final Enemy enemy = new Enemy(new Position(0, 0), new Size(20 ,20), 1);
        final float velocityBeforeCollision = enemy.getVelocity();
        final Obstacle obstacle = new Obstacle(new Position(0, 0), new Size(15, 15));
        final List<AbstractPhysicalObject> mapObjects1 = new ArrayList<>();
        mapObjects1.add(obstacle);
        mapObjects1.add(enemy);
        final Level level1 = new Level(mapObjects1,name);
        level1.checkCollisions();

        assertNotEquals(velocityBeforeCollision, enemy.getVelocity());
    }

    @Test
    public void testCheckCollisionsWithoutCollisions(){
        final Enemy enemy = new Enemy(new Position(0, 0), new Size(20 ,20), 1);
        final float velocityBeforeCollision = enemy.getVelocity();
        final Obstacle obstacle = new Obstacle(new Position(100, 100), new Size(15, 15));
        final List<AbstractPhysicalObject> mapObjects1 = new ArrayList<>();
        mapObjects1.add(obstacle);
        mapObjects1.add(enemy);
        final Level level1 = new Level(mapObjects1, name);
        level1.checkCollisions();

        assertEquals(velocityBeforeCollision, enemy.getVelocity(), 0);
    }

}
