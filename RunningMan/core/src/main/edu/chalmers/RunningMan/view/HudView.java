package edu.chalmers.RunningMan.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import edu.chalmers.RunningMan.model.gameobject.Level;

/**
 * The view for the HUD.
 * @author Jesper Olsson
 */
public class HudView extends Stage {

    private final TimerView timerView;
    private final KillCountView killCountView;
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
