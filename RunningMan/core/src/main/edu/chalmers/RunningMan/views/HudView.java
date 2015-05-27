package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import edu.chalmers.RunningMan.model.gameobjects.Level;

/**
 * Created by Jesper on 5/14/2015.
 */
public class HudView extends Stage {

    private TimerView timerView;
    private KillCountView killCountView;
    private OrthographicCamera camera;
    private Batch batch;

    public HudView(Level level){
        timerView = new TimerView(level.getTimer());
        killCountView = new KillCountView(level);
        initCamera();
    }

    private void initCamera(){
        camera = new OrthographicCamera();
        getViewport().setCamera(camera);
        camera.setToOrtho(false);
    }

    private void updateCamera(){
        camera.update();
        batch.setProjectionMatrix(camera.combined);

    }

    public void draw(){
        final float deltaTime = Gdx.graphics.getDeltaTime();
        batch = getBatch();
        timerView.draw(batch, deltaTime);
        killCountView.draw(batch, deltaTime);
        updateCamera();
    }
}
