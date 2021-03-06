package edu.chalmers.RunningMan.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import edu.chalmers.RunningMan.model.gameobject.Pit;

/**
 * View for the pit fall
 */
public class PitView extends Actor{

    private final Pit pit;
    private Texture texture;
    private final static String TILES_LOCATION = "tilesets/";

    public PitView(Pit pit, String levelName) {
        this.pit = pit;
        try {
            texture = new Texture(Gdx.files.internal(TILES_LOCATION + levelName +"pitfall.png"));
        }catch (Exception e){
            texture = new Texture(Gdx.files.internal(TILES_LOCATION  +"pitfall.png"));
            e.printStackTrace();
        }

    }

    public void draw(Batch batch, float deltaTime){
        batch.begin();
        batch.draw(texture, pit.getPosition().getX(), pit.getPosition().getY(), pit.getSize().getWidth(), pit.getSize().getHeight());
        batch.end();
    }
}
