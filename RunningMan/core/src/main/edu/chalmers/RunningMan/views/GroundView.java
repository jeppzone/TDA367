package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chalmers.RunningMan.entities.Ground;

/**
 * Created by Kvist1 on 2015-05-04.
 */
public class GroundView {

    private Ground ground;
    private SpriteBatch sb;
    private Texture texture;

    public GroundView(Ground ground) {
        this.ground = ground;
        sb = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("core/assets/tile_test.png"));
    }

    public void draw(){
        sb.begin();
        sb.draw(texture, ground.getPosition().getX(), ground.getPosition().getY(), ground.getSize().getWidth(), ground.getSize().getHeight());
        sb.end();
    }
}
