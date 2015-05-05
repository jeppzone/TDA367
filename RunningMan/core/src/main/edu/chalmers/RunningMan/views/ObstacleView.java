package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chalmers.RunningMan.entities.Obstacle;

/**
 * A class to represent the view of obstacle objects.
 */
public class ObstacleView  {
    private final Obstacle obstacle;
    private final SpriteBatch sb;
    private final Texture rockObstacle;

    public ObstacleView(Obstacle obstacle){
        this.obstacle = obstacle;
        sb = new SpriteBatch();
    }

    /**
     * Draws the object
     */
    public void draw(){
        sb.begin();
        sb.draw(rockObstacle, obstacle.getPosition().getX(), obstacle.getPosition().getY(), obstacle.getSize().getWidth(), obstacle.getSize().getHeight());
        sb.end();
    }
}
