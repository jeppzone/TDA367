package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chalmers.RunningMan.entities.Player;




public class PlayerView{

    private final Player player;
    //private final Sprite sprite;
    private final SpriteBatch spriteBatch;
    //private final TextureRegion standingLeft;
    //private final TextureRegion standingRight;
    private final Texture stand;




    public PlayerView(Player player){
        this.player = player;
       // this.sprite = sprite;;
        stand = new Texture(Gdx.files.internal("core/assets/testPic.png"));
      //  sprite = new Sprite();
        spriteBatch = new SpriteBatch();
    }

    public void draw(){
        spriteBatch.begin();
        spriteBatch.draw(stand, player.getPosition().getX(),player.getPosition().getY(), player.getSize().getWidth(), player.getSize().getHeight());
        spriteBatch.end();

    }

}
