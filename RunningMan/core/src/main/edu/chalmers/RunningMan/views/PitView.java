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
    private final static String TILES_LOCATION = "core/assets/tilesets/";

    public PitView(Pit pit, String levelName) {
        this.pit = pit;
        try {
            texture = new Texture(Gdx.files.internal(TILES_LOCATION + levelName +"pitfall.png"));
        }catch (Exception e){
            throw new NullPointerException("Could not load pitfall image in pitfallview:"+TILES_LOCATION + levelName+
                    "pitfall.png does not exist");
        }

    }

    public void draw(Batch batch, float deltaTime){
        batch.begin();
        batch.draw(texture, pit.getPosition().getX(), pit.getPosition().getY(), pit.getSize().getWidth(), pit.getSize().getHeight());
        batch.end();
    }
}
