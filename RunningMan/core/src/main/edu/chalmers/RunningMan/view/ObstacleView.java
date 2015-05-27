package edu.chalmers.RunningMan.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import edu.chalmers.RunningMan.model.gameobjects.Obstacle;

/**
 * A class to represent the view of obstacle gameobjects.
 */
public class ObstacleView extends Actor {
    private Obstacle obstacle;
    private Texture texture;
    private final static String TILES_LOCATION = "core/assets/tilesets/";

    public ObstacleView(Obstacle obstacle, String levelName){
        this.obstacle = obstacle;
        try {
            texture = new Texture(Gdx.files.internal(TILES_LOCATION + levelName + "obstacle.png"));
        }catch (Exception e){
            texture = new Texture(Gdx.files.internal(TILES_LOCATION + "obstacle.png"));
            e.printStackTrace();
        }
    }

    /**
     * Draws the object
     */
    public void draw(Batch batch, float deltaTime){
        batch.begin();
        batch.draw(texture, obstacle.getPosition().getX(), obstacle.getPosition().getY(), obstacle.getSize().getWidth(), obstacle.getSize().getHeight());
        batch.end();
    }
}
