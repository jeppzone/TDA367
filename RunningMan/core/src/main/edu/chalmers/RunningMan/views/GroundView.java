package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import edu.chalmers.RunningMan.model.level.mapobjects.Ground;

/**
 * Created by Kvist1 on 2015-05-04.
 */
public class GroundView extends Actor{

    private Ground ground;
    private Texture texture;
    private final static String TILES_LOCATION = "core/assets/tilesets/";

    public GroundView(Ground ground, String levelName) {
        this.ground = ground;
        try {
            texture = new Texture(Gdx.files.internal(TILES_LOCATION + levelName + "ground.png"));
        }catch (Exception e){
            throw new NullPointerException("could not load ground image in GroundView:" + TILES_LOCATION +
                    levelName + "ground.png");
        }
    }

    public void draw(Batch batch, float deltaTime){
        batch.begin();
        batch.draw(texture, ground.getPosition().getX(), ground.getPosition().getY(), ground.getSize().getWidth(), ground.getSize().getHeight());
        batch.end();
    }
}
