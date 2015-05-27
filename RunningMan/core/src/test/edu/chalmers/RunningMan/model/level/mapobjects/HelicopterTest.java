package edu.chalmers.RunningMan.model.level.mapobjects;

import edu.chalmers.RunningMan.model.Position;
import edu.chalmers.RunningMan.model.Size;
import edu.chalmers.RunningMan.model.gameobject.Helicopter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class HelicopterTest extends Assert {

    private Helicopter helicopter;
    private Size size = new Size(100,100);
    private Position position = new Position(100,100);
    private static final float DELTATIME = 2f;

    @Before
    public void setUp() {
        helicopter = new Helicopter(new Position(100,100),new Size(100,100));
    }

    @Test
    public void testGetSize(){
        assertEquals(size, helicopter.getSize());
    }

    @Test
    public void testMove() {
        final float posX = helicopter.getPosition().getX();
        helicopter.move(DELTATIME);
        assertTrue(helicopter.getPosition().getX() > posX);
    }

    @Test
    public void testSetPosition(){
        helicopter.setPosition(position);
        assertEquals(position, helicopter.getPosition());
    }

    @Test
    public void testGetHitbox(){
        final Rectangle rec = new Rectangle((int)position.getX(), (int)position.getY(),
                (int)size.getWidth(), (int)size.getHeight());
        assertEquals(rec, helicopter.getHitbox());
    }
}