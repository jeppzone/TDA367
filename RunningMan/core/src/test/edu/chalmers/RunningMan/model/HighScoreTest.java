package edu.chalmers.RunningMan.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jesper Olsson
 */
public class HighScoreTest extends Assert {
    private HighScore highScoreLevel1;
    private HighScore highScoreLevel2;
    private String levelName1;
    private String levelName2;

    @Before
    public void setUp(){
        levelName1 = "level1";
        levelName2 = "level2";
        highScoreLevel1 = new HighScore(levelName1);
        highScoreLevel2 = new HighScore(levelName2);
    }

    @Test
    public void testIsHighScoreTrue(){
        assertTrue(highScoreLevel1.isHighScore(200));
    }

    @Test
    public void testIsHighScoreFalse(){
        for(int i = 5; i < 10; i++){
            highScoreLevel1.addScore(i);
        }

        assertFalse(highScoreLevel1.isHighScore(1));
    }

    @Test
    public void testAddScoreHighScore(){
        highScoreLevel1.addScore(5);
        assertEquals((int) highScoreLevel1.getHighScores().get(0), 5);
    }

    @Test
    public void testAddScoreNotHighScore(){
        for(int i  = 10; i < 15; i++){
            highScoreLevel1.addScore(i);
        }
        highScoreLevel1.addScore(3);

        assertNotEquals((int) highScoreLevel1.getHighScores().get(4), 3);
    }

    @Test
    public void testWriteAndSaveToFile(){
        highScoreLevel1.getHighScores().clear();
        highScoreLevel1.addScore(4);
        highScoreLevel1.saveToFile();
        highScoreLevel1.getHighScores().clear();
        highScoreLevel1.loadFromFile();

        assertEquals((int)highScoreLevel1.getHighScores().get(0), 4);
        highScoreLevel1.getHighScores().clear();
    }


}
