package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import edu.chalmers.RunningMan.entities.Player;

/**
 * A class to represent the view of the Player.
 */
public class PlayerView extends Actor{

    private final Player player;

    private final Texture walkRightSheet, walkLeftSheet, jumpRightTexture, jumpLeftTexture, deadTexture,deadByPitfallTexture;
    private TextureRegion[] walkRightSprites, walkLeftSprites,deathSprite,deathByPitfallSprite;
    private TextureRegion jumpRightSprite, jumpLeftSprite;
    private Animation walkRightAnimation, walkLeftAnimation,dieAnimation,dieByPitfallAnimation;

    private final static String SPRITE_WALK_RIGHT_SHEET = "core/assets/walk_right_sheet_soldier.png";
    private final static String SPRITE_WALK_LEFT_SHEET = "core/assets/walk_left_sheet_soldier.png";
    private final static String SPRITE_JUMP_RIGHT = "core/assets/jump_right_soldier.png";
    private final static String SPRITE_JUMP_LEFT = "core/assets/jump_left_soldier.png";
    private final static String SPRITE_DEAD = "core/assets/soldierdie.png";
    private final static String SPRITE_DEAD_BY_PITFALL = "core/assets/soldierdiebypitfall.png";

    private float stateTime,deathTime;

    public PlayerView(Player player){
        this.player = player;

        walkRightSheet = new Texture(SPRITE_WALK_RIGHT_SHEET);
        walkLeftSheet = new Texture(SPRITE_WALK_LEFT_SHEET);
        jumpRightTexture = new Texture(SPRITE_JUMP_RIGHT);
        jumpLeftTexture = new Texture(SPRITE_JUMP_LEFT);
        deadTexture = new Texture(SPRITE_DEAD);
        deadByPitfallTexture = new Texture(SPRITE_DEAD_BY_PITFALL);

        // TODO how to decide height and width on sprites without hard code it?
        walkRightSprites = TextureRegion.split(walkRightSheet, 58, 60)[0];
        walkLeftSprites = TextureRegion.split(walkLeftSheet, 58, 60)[0];
        jumpRightSprite = new TextureRegion(jumpRightTexture, 58, 60);
        jumpLeftSprite = new TextureRegion(jumpLeftTexture, 58, 60);
        deathSprite = TextureRegion.split(deadTexture,58,60)[0];
        deathByPitfallSprite = TextureRegion.split(deadByPitfallTexture, 58,60)[0];

        // the float argument decides the speed of the animation
        walkRightAnimation = new Animation(1/12f, walkRightSprites);
        walkLeftAnimation = new Animation(1/12f, walkLeftSprites);
        dieAnimation = new Animation(1/6f, deathSprite);
        dieByPitfallAnimation = new Animation(1/6f,deathByPitfallSprite);

    }

    /**
     * Draws the player
     */

    public void draw(Batch batch, float deltaTime) {
        batch.begin();
        batch.draw(getCurrentSprite(), player.getPosition().getX(), player.getPosition().getY(), player.getSize().getWidth(), player.getSize().getHeight());
        batch.end();
    }

    /**
     * Get the current sprite to be drawn
     * @return currentFrame
     */
    private TextureRegion getCurrentSprite() {
        stateTime += Gdx.graphics.getDeltaTime();

        if (player.isDead()) {
            stateTime = 0;
            deathTime += Gdx.graphics.getDeltaTime();
            return dieAnimation.getKeyFrame(deathTime, false);

        } else if(player.isDeadByPitfall()){
            stateTime = 0;
            deathTime += Gdx.graphics.getDeltaTime();
            return dieByPitfallAnimation.getKeyFrame(deathTime,false);
            
        }

        switch (player.getPlayerState()) {

            case MOVING_RIGHT:
                return walkRightAnimation.getKeyFrame(stateTime, true);

            case MOVING_LEFT:
                return walkLeftAnimation.getKeyFrame(stateTime, true);

            case FACING_RIGHT:
                return walkRightSprites[0];

            case FACING_LEFT:
                return walkLeftSprites[5];

            case JUMPING_RIGHT:
                return jumpRightSprite;

            case JUMPING_LEFT:
                return jumpLeftSprite;
            default:
                // default case will not appear
                return walkRightAnimation.getKeyFrame(stateTime, true);
        }

    }
}
