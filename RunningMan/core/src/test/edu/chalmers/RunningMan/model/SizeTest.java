package edu.chalmers.RunningMan.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SizeTest extends Assert {

    private Size size;

    @Before
    public void setUp(){
        size = new Size(100,150);
    }

    @Test
    public void testGetWidth() {
        double testWidth = size.getWidth();
        assertEquals(100, testWidth,0);
    }

    @Test
    public void testGetHeight() {
        double testHeight = size.getHeight();
        assertEquals(150, testHeight,0);
    }

    @Test
    public void testGetArea() {
        double testHeight = 100;
        double testWidth = 150;
        double testArea = testHeight*testWidth;
        assertEquals(size.getArea(), testArea, 0);
    }
}