package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import edu.chalmers.RunningMan.entities.Player;
import com.badlogic.gdx.graphics.g2d.TextureRegion;



public class PlayerView{

    private final Player player;
    private final Sprite sprite;
    //private final TextureRegion standingLeft;
    //private final TextureRegion standingRight;
    private final Texture stand;



    public PlayerView(Player player, Sprite sprite){
        this.player = player;
        this.sprite = sprite;
        Image img = new Image();
        stand = new Texture(Gdx.files.internal("tesPic.png"));
        sprite = new Sprite();
    }

    public void draw(){

    }

}
