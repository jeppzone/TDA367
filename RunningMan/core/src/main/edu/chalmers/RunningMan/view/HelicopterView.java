package edu.chalmers.RunningMan.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import edu.chalmers.RunningMan.model.gameobject.Helicopter;

/**
 * A view for the helicopter
 */
public class HelicopterView extends Actor {
    private final Helicopter finishObject;
    private float stateTime;
    private final SpriteBatch sb;
    private final Texture chopperHover;
    private TextureRegion[] chopperHoverSprites;
    private Animation chopperHoverAnimation;
    private final static String SPRITE_CHOPPERHOVER = "chopperhover.png";

    public HelicopterView(Helicopter finishObject){
        this.finishObject = finishObject;
        try{
            chopperHover = new Texture(SPRITE_CHOPPERHOVER);
        }catch (Exception e){
            throw new NullPointerException("Could not find chopperhover.png in in "
                    + this.getClass().toString());
        }

        chopperHoverSprites = TextureRegion.split(chopperHover, 95, 62)[0];
        chopperHoverAnimation = new Animation(1/8f, chopperHoverSprites);

        sb = new SpriteBatch();
    }

    @Override
    public void draw(Batch batch, float deltaTime){
        batch.begin();
        batch.draw(getCurrentSprite(), finishObject.getPosition().getX(), finishObject.getPosition().getY(), finishObject.getSize().getWidth(), finishObject.getSize().getHeight());
        batch.end();
    }

    private TextureRegion getCurrentSprite(){
        stateTime += Gdx.graphics.getDeltaTime();
        return chopperHoverAnimation.getKeyFrame(stateTime, true);
    }
}
