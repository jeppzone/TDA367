package edu.chalmers.RunningMan.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import edu.chalmers.RunningMan.model.Timer;


/**
 * The view for the timer
 * @author Jesper Olsson
 */
public class TimerView extends HudFont {
    private final Timer timer;
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
