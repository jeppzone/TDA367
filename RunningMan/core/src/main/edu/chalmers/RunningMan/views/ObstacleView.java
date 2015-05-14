package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import edu.chalmers.RunningMan.entities.Obstacle;

/**
 * A class to represent the view of obstacle objects.
 */
public class ObstacleView extends Actor {
    private final Obstacle obstacle;
    private final SpriteBatch sb;
    private final Texture rockObstacle;

    public ObstacleView(Obstacle obstacle){
        this.obstacle = obstacle;
        sb = new SpriteBatch();
        rockObstacle = new Texture(Gdx.files.internal("core/assets/rockobstacle.png"));
    }

    /**
     * Draws the object
     */
    public void draw(Batch batch, float deltaTime){
        batch.begin();
        batch.draw(rockObstacle, obstacle.getPosition().getX(), obstacle.getPosition().getY(), obstacle.getSize().getWidth(), obstacle.getSize().getHeight());
        batch.end();
    }
}
