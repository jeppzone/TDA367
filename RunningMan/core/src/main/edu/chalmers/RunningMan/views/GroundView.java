package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import edu.chalmers.RunningMan.entities.Ground;

/**
 * Created by Kvist1 on 2015-05-04.
 */
public class GroundView extends Actor{

    private Ground ground;
    private Texture texture;

    public GroundView(Ground ground) {
        this.ground = ground;
        texture = new Texture(Gdx.files.internal("core/assets/tile_test.png"));
    }

    public void draw(Batch batch, float deltaTime){
        batch.begin();
        batch.draw(texture, ground.getPosition().getX(), ground.getPosition().getY(), ground.getSize().getWidth(), ground.getSize().getHeight());
        batch.end();
    }
}
