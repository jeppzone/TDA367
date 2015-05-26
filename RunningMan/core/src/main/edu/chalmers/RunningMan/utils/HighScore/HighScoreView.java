package edu.chalmers.RunningMan.utils.highscore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chalmers.RunningMan.views.HudFont;

import java.util.List;

/**
 * @author Jesper Olsson
 */
public class HighScoreView extends HudFont {

    private List<Integer> scores;
    private SpriteBatch batch;
    private HighScore highScore;

    public HighScoreView(HighScore highScore){
        this.highScore = highScore;
        scores = highScore.getHighScores();
        batch = new SpriteBatch();
        generateFont();
    }

    public void draw(){
        int counter = 1;
        batch.begin();
        font.draw(batch, "highscore for " + highScore.getLevelName(), Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() - 50);
        for(Integer score: scores){
            font.draw(batch , counter + ")  " +  score.toString(), Gdx.graphics.getWidth()/2 - 50, Gdx.graphics.getHeight() - 125 -  counter*100);
            counter++;
        }
        batch.end();
    }
}
