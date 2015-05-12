package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chalmers.RunningMan.entities.Ground;
import edu.chalmers.RunningMan.entities.Pit;

/**
 * Created by Kvist1 on 2015-05-12.
 */
public class PitView {

    private Pit pit;
    private SpriteBatch sb;
    private Texture texture;

    public PitView(Pit pit) {
        this.pit = pit;
        sb = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("core/assets/pitfall.png"));
    }

    public void draw(){
        sb.begin();
        sb.draw(texture, pit.getPosition().getX(), pit.getPosition().getY(), pit.getSize().getWidth(), pit.getSize().getHeight());
        sb.end();
    }
}
