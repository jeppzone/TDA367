package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chalmers.RunningMan.entities.HighScore;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Jesper Olsson
 */
public class HighScoreView extends HudFont {

    private List<Integer> scores;
    private SpriteBatch batch;
    private String levelName;

    public HighScoreView(List<Integer> scores, String levelName){
        this.scores = scores;
        Collections.sort(scores, Collections.reverseOrder());
        Collections.reverseOrder();
        this.levelName = levelName;
        batch = new SpriteBatch();
        generateFont();
    }

    public void draw(){
        int counter = 1;
        batch.begin();
        font.draw(batch, "HighScore for " + levelName, Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() - 50);
        for(Integer score: scores){
            font.draw(batch , counter + ")  " +  score.toString(), Gdx.graphics.getWidth()/2 - 50, Gdx.graphics.getHeight() - 125 -  counter*100);
            counter++;
        }
        batch.end();
    }
}
