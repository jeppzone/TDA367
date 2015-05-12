package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import edu.chalmers.RunningMan.entities.Level;
import edu.chalmers.RunningMan.entities.Player;

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
        camera = new OrthographicCamera();
        getViewport().setCamera(camera);
        camera.setToOrtho(false);
        System.out.println(Gdx.graphics.getWidth() + " " + Gdx.graphics.getHeight());
    }

    public void draw(){
        batch = getBatch();
        playerView.draw(batch, Gdx.graphics.getDeltaTime());
        System.out.println(player.getPosition().getX());
        if(player.getPosition().getX() > 320) {
            camera.position.set(player.getPosition().getX(), Gdx.graphics.getHeight() / 2, 0);
        }
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        for(Actor actor: views){
            actor.draw(batch, Gdx.graphics.getDeltaTime());
        }


    }

}
