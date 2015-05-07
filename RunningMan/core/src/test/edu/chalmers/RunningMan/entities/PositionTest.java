package edu.chalmers.RunningMan.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jesper Olsson
 */
public class PositionTest extends Assert{

    private Position position;

    @Before
    public void setUp(){
        position = new Position(100, 150);
    }

    @Test
    public void testCopyConstructorX(){
        final Position copyPosition = new Position(position);
        assertEquals(position.getX(), copyPosition.getX(), 0);
    }

    @Test
    public void testCopyConstructorY(){
        final Position copyPosition = new Position(position);
        assertEquals(position.getY(), copyPosition.getY(), 0);
    }

    @Test(expected = NullPointerException.class)
    public void testCopyConstructorNull(){
        final Position nullPosition = null;
        new Position(nullPosition);
    }

    @Test
    public void testGetX(){
        assertEquals(100, position.getX(), 0);
    }

    @Test
    public void testGetY(){
        assertEquals(150, position.getY(), 0);
    }

    @Test
    public void testSetX(){
        position.setX(50);
        assertEquals(50, position.getX(), 0);
    }

    @Test
    public void testSetY(){
        position.setY(300);
        assertEquals(300, position.getY(), 0);
    }

    @Test
    public void testSetNegativeX(){
        position.setX(-100);
        assertEquals(0, position.getX(), 0);
    }

    @Test
    public void testSetNegativeY(){
        position.setY(-100);
        assertEquals(0, position.getY(), 0);
    }

    @Test
    public void testEqualsWithNull(){
        assertFalse(position.equals(null));
    }

    @Test
    public void testEqualsWithSameReference(){
        assertTrue(position.equals(position));
    }

    @Test
    public void testEqualsWithDifferentX(){
        final Position otherPosition = new Position(position.getX() -50f, position.getY() );
        assertFalse(position.equals(otherPosition));
    }

    @Test
    public void testEqualsWIthDifferentY(){
        final Position otherPosition = new Position(position.getX(), position.getY() - 20f);
        assertFalse(position.equals(otherPosition));
    }

    @Test
    public void testEqualsWithSameCoordinates(){
        final Position otherPosition = new Position(position);
        assertTrue(position.equals(otherPosition));
    }

    @Test
    public void testHashCode(){
        final Position otherPosition = new Position(position);
        assertEquals(position.hashCode(), otherPosition.hashCode(), 0);
    }
}
