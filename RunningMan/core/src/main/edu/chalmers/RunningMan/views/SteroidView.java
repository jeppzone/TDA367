package edu.chalmers.RunningMan.views;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chalmers.RunningMan.entities.Steroid;

public class SteroidView {
    private Steroid steroid;
    private SpriteBatch sb;
    private Texture texture;

    public SteroidView(Steroid steroid){
        this.steroid = steroid;
        sb = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("core/assets/spruta.png"));
    }

    public void draw(){
        sb.begin();
        sb.draw(texture, steroid.getPosition().getX(), steroid.getPosition().getY(), steroid.getSize().getWidth(), steroid.getSize().getHeight());
        sb.end();
    }

}
