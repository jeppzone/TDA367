package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chalmers.RunningMan.entities.Player;
import edu.chalmers.RunningMan.utils.PlayerState;

import java.util.List;

/**
 * Created by Jesper on 5/12/2015.
 */
public class OverView extends Stage {
    private List<Actor> actors;
    private Batch batch;
    private Player player;
    private OrthographicCamera camera;
    private PlayerView playerView;

    public OverView(List<Actor> actorList, Player player){
        actors = actorList;
        this.player = player;
        playerView = new PlayerView(player);
        camera = new OrthographicCamera();
        getViewport().setCamera(camera);
        camera.setToOrtho(false);
        System.out.println(Gdx.graphics.getWidth() + " " + Gdx.graphics.getHeight());
    }

    public void draw(){
        batch = getBatch();
        playerView.draw(batch, Gdx.graphics.getDeltaTime());
        //if(player.getPosition().getX() > )
        final float oldX = player.getPosition().getX();
            if(player.getPosition().getX() > 320)
            camera.position.set(player.getPosition().getX(), Gdx.graphics.getHeight() / 2, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        for(Actor actor: actors){
            actor.draw(batch, Gdx.graphics.getDeltaTime());
        }


    }
}
