package edu.chalmers.RunningMan.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import edu.chalmers.RunningMan.model.gameobject.Player;

/**
 * A class to represent the view of the gameobject.
 */
public class PlayerView extends Actor{

    private final Player player;

    private final Texture walkRightSheet, walkLeftSheet, jumpRightTexture, jumpLeftTexture, deadTexture,deadByPitfallTexture;
    private TextureRegion[] walkRightSprites, walkLeftSprites,deathSprite,deathByPitfallSprite;
    private TextureRegion jumpRightSprite, jumpLeftSprite;
    private Animation walkRightAnimation, walkLeftAnimation,dieAnimation,dieByPitfallAnimation;

    private final static String ANIMATION_LOCATION = "core/assets/animations/";

    private static String SPRITE_WALK_RIGHT_SHEET;
    private static String SPRITE_WALK_LEFT_SHEET;
    private static String SPRITE_JUMP_RIGHT;
    private static String SPRITE_JUMP_LEFT;
    private static String SPRITE_DEAD;
    private static String SPRITE_DEAD_BY_PITFALL;

    private float stateTime,deathTime;

    public PlayerView(Player player, String levelName){
        this.player = player;

        //loads the sprites depending on the level
        try {
            SPRITE_WALK_RIGHT_SHEET = ANIMATION_LOCATION + levelName + "walk_right_sheet_soldier.png";
            SPRITE_WALK_LEFT_SHEET = ANIMATION_LOCATION + levelName + "walk_left_sheet_soldier.png";
            SPRITE_JUMP_RIGHT = ANIMATION_LOCATION + levelName + "jump_right_soldier.png";
            SPRITE_JUMP_LEFT = ANIMATION_LOCATION + levelName + "jump_left_soldier.png";
            SPRITE_DEAD = ANIMATION_LOCATION + levelName + "soldierdie.png";
            SPRITE_DEAD_BY_PITFALL = ANIMATION_LOCATION + levelName + "soldierdiebypitfall.png";
        }catch (Exception e){
            SPRITE_WALK_RIGHT_SHEET = ANIMATION_LOCATION + "walk_right_sheet_soldier.png";
            SPRITE_WALK_LEFT_SHEET = ANIMATION_LOCATION + "walk_left_sheet_soldier.png";
            SPRITE_JUMP_RIGHT = ANIMATION_LOCATION + "jump_right_soldier.png";
            SPRITE_JUMP_LEFT = ANIMATION_LOCATION + "jump_left_soldier.png";
            SPRITE_DEAD = ANIMATION_LOCATION + "soldierdie.png";
            SPRITE_DEAD_BY_PITFALL = ANIMATION_LOCATION + "soldierdiebypitfall.png";
            e.printStackTrace();
        }

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

        if(player.isDeadByPitfall()){
            stateTime = 0;
            deathTime += Gdx.graphics.getDeltaTime();
            return dieByPitfallAnimation.getKeyFrame(deathTime,false);
            
        }else if (player.isDead()) {
            stateTime = 0;
            deathTime += Gdx.graphics.getDeltaTime();
            return dieAnimation.getKeyFrame(deathTime, false);
        }

        switch (player.getAnimationState()) {

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
