package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import edu.chalmers.RunningMan.model.Timer;


/**
 * Created by Jesper on 5/14/2015.
 */
public class TimerView extends HudFont {
    private Timer timer;
    private CharSequence stringTime;

    public TimerView(Timer timer){
        this.timer = timer;
        generateFont();
    }

    public void draw(Batch batch, float deltaTime){
        timer.update(deltaTime);
        stringTime = timer.getTimeLeftInteger()+ "";
        batch.begin();
        font.draw(batch, "Timer: " +stringTime, 100, Gdx.graphics.getHeight() - 20);
        batch.end();
    }
}
