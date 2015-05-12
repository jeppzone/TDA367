package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import edu.chalmers.RunningMan.entities.Ground;
import edu.chalmers.RunningMan.entities.Pit;

/**
 * Created by Kvist1 on 2015-05-12.
 */
public class PitView extends Actor{

    private Pit pit;
    private Texture texture;

    public PitView(Pit pit) {
        this.pit = pit;
        texture = new Texture(Gdx.files.internal("core/assets/pitfall.png"));
    }

    public void draw(Batch batch, float deltaTime){
        batch.begin();
        batch.draw(texture, pit.getPosition().getX(), pit.getPosition().getY(), pit.getSize().getWidth(), pit.getSize().getHeight());
        batch.end();
    }
}
