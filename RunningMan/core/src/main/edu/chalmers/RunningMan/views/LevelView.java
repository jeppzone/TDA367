package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import edu.chalmers.RunningMan.gameworld.Factory;
import edu.chalmers.RunningMan.entities.Level;
import edu.chalmers.RunningMan.entities.Player;

import java.util.List;

/**
 * Created by Jesper on 5/12/2015.
 */
public class LevelView extends Stage {
    private Level level;
    private List<Actor> actors;
    private Batch batch;
    private Player player;
    private OrthographicCamera camera;
    private PlayerView playerView;
    private Factory factory;

    public LevelView(Level level, Player player, BulletView bulletView, Factory factory){
        this.level = level;
        this.player = player;
        this.factory = factory;
        System.out.println(factory);
        actors = factory.getViews();
        actors.add(bulletView);
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
        if(player.getPosition().getX() > 640);
            camera.position.set(player.getPosition().getX(), Gdx.graphics.getHeight() / 2, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        for(Actor actor: actors){
            actor.draw(batch, Gdx.graphics.getDeltaTime());
        }


    }
}
