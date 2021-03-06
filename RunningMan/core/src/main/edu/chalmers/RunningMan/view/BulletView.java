package edu.chalmers.RunningMan.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import edu.chalmers.RunningMan.model.gameobject.Bullet;

import java.util.List;

/**
 * A class to represent the view of the bullet.
 */

public class BulletView extends Actor{
    private final List<Bullet> bullets;

    private final Texture bulletLeft, bulletRight;

    private final static String SPRITE_BULLET_RIGHT = "bulletleft.png";
    private final static String SPRITE_BULLET_LEFT = "bulletright.png";

    private TextureRegion[] bulletLeftSprites,bulletRightSprites;
    private Animation bulletLeftAnimation, bulletRightAnimation;

    private float stateTime;

    public BulletView(List<Bullet> bullets){
        this.bullets = bullets;
        try {
            bulletLeft = new Texture(Gdx.files.internal(SPRITE_BULLET_LEFT));
            bulletRight = new Texture(Gdx.files.internal(SPRITE_BULLET_RIGHT));
        }catch (Exception e){
            throw new NullPointerException("could not load bullet image in" + this.getClass().toString());
        }
        bulletLeftSprites = TextureRegion.split(bulletLeft, 20, 10)[0];
        bulletRightSprites = TextureRegion.split(bulletRight,20,10)[0];

        bulletLeftAnimation = new Animation(1/12f, bulletLeftSprites);
        bulletRightAnimation = new Animation(1/12f, bulletRightSprites);

    }

    /**
     * Draws the bullet
     */
    public void draw(Batch batch, float deltaTime){
        batch.begin();
        for(Bullet bullet: bullets) {
            batch.draw(getCurrentSprite(bullet), bullet.getPosition().getX(), bullet.getPosition().getY(), bullet.getSize().getWidth(), bullet.getSize().getHeight());
        }

        batch.end();
    }

    /**
     * Get the current sprite to be drawn
     * @return currentFrame
     */
    private TextureRegion getCurrentSprite(Bullet bullet) {
        stateTime += Gdx.graphics.getDeltaTime();

        if(bullet.getVelocity() > 0){
            return bulletLeftAnimation.getKeyFrame(stateTime, true);

        }else {
            return bulletRightAnimation.getKeyFrame(stateTime, true);
        }
    }
}
