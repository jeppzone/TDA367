package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import edu.chalmers.RunningMan.entities.Level;
import edu.chalmers.RunningMan.entities.Player;

/**
 * Created by Jesper on 5/14/2015.
 */
public class HudView extends Stage {

    private TimeView timeView;
    private KillCountView killCountView;
    private Level level;
    private OrthographicCamera camera;
    private Batch batch;

    public HudView(Level level){
        timeView = new TimeView(level.getTime());
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
        timeView.draw(batch, deltaTime);
        killCountView.draw(batch, deltaTime);
        updateCamera();
    }
}
