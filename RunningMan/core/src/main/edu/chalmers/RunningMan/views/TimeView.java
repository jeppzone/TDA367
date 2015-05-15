package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import edu.chalmers.RunningMan.entities.Time;


/**
 * Created by Jesper on 5/14/2015.
 */
public class TimeView extends Actor {
    private Time time;
    private BitmapFont font;
    private CharSequence stringTime;

    public TimeView(Time time){
        this.time = time;
    }

    public void draw(Batch batch, float deltaTime){
        time.update(deltaTime);
        font = new BitmapFont();
        stringTime = (int)time.getMaxTime() - time.getTimeInteger() +"";
        batch.begin();
        font.draw(batch, stringTime, 300, Gdx.graphics.getHeight() - 20);
        batch.end();
    }
}
