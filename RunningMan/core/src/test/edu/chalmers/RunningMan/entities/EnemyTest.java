package edu.chalmers.RunningMan.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class EnemyTest extends Assert {

    private Enemy enemy;
    private float testStartVelocity, velocity = -100;
    private Position position = new Position(10,10);
    private static final float DELTATIME = 2f;
    private final Size size = new Size(10f, 10f);

    @Before
    public void setUp(){
        enemy = new Enemy(position, size, 10);
        testStartVelocity = enemy.getVelocity();
    }

    @Test
    public void testGetPosition(){
        assertEquals(position, enemy.getPosition());
    }

    @Test
    public void testMove() {
        final float posX = enemy.getPosition().getX();
        enemy.move(DELTATIME);
        assertTrue(enemy.getPosition().getX() < posX);
    }

    @Test
    public void testChangeDirection(){
        enemy.changeDirection();
        assertEquals(enemy.getVelocity(),velocity*-1, 0);
    }

    @Test
    public void testGetVelocity() {
        assertEquals(testStartVelocity, velocity,0);
    }

    @Test
    public void testSetPosition(){
        position = new Position(40f, 40f);
        enemy.setPosition(position);
        assertEquals(position, enemy.getPosition());
    }

    @Test
    public void testGetSize(){
        assertEquals(size, enemy.getSize());
    }

    @Test
    public void testSetX(){
        enemy.setX(10f);
        enemy.setX(position.getX());
        assertEquals(position.getX(), enemy.getPosition().getX(), 0);
    }

    @Test
    public void testSetY(){
        enemy.setY(15f);
        enemy.setY(position.getY());
        assertEquals(position.getY(), enemy.getPosition().getY(), 0);
    }

    @Test
    public void testGetHitbox(){
        final Rectangle rec = new Rectangle((int)position.getX(), (int)position.getY(),
                (int)size.getWidth(), (int)size.getHeight());
        assertEquals(rec, enemy.getHitbox());
    }
}