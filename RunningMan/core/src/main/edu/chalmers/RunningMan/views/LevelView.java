package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import edu.chalmers.RunningMan.entities.Level;
import edu.chalmers.RunningMan.entities.Player;
import edu.chalmers.RunningMan.entities.Time;

import java.util.List;

/**
 * Created by Jesper on 5/12/2015.
 */
public class LevelView extends Stage{
    private List<Actor> views;
    private Batch batch;
    private Player player;
    private OrthographicCamera camera;
    private PlayerView playerView;

    public LevelView(List<Actor> views, Player player, BulletView bulletView ){
        this.player = player;
        this.views = views;
        views.add(bulletView);
        playerView = new PlayerView(player);
        initCamera();
    }

    private void initCamera(){
        camera = new OrthographicCamera();
        getViewport().setCamera(camera);
        camera.setToOrtho(false);
    }

    private void updateCamera(){
        if(player.getPosition().getX() > 320) {
            final float lerp = 0.1f;
            Vector3 position = getCamera().position;
            position.x+= (player.getPosition().getX() - position.x)*lerp;
            position.y = (Gdx.graphics.getHeight()/2);
        }
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    public void draw(){
        float deltaTime = Gdx.graphics.getDeltaTime();
        batch = getBatch();
        playerView.draw(batch, deltaTime);
        updateCamera();
        for(Actor actor: views){
            actor.draw(batch, deltaTime);
        }
    }

}
