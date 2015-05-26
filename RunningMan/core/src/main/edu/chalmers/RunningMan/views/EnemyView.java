package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import edu.chalmers.RunningMan.model.objects.Enemy;

/**
 * A class to represent the view of the enemy.
 */
public class EnemyView extends Actor{
    private final Enemy enemy;

    private final Texture enemyWalkingleft, enemyWalkingright, enemyShotInBack,enemyShotInFront;

    private String enemyType;
    private float animationSpeed;
    private int textureRegionWidth;
    private int textureRegionHeight;
    private int textureRegionShotInFrontWidth;

    private final static String ANIMATION_LOCATION = "core/assets/animations/";

    private String SPRITE_ENEMY_WALK_LEFT_SHEET;
    private String SPRITE_ENEMY_WALK_RIGHT_SHEET;
    private String SPRITE_ENEMY_SHOT_IN_BACK;
    private String SPRITE_ENEMY_SHOT_IN_FRONT;

    private TextureRegion[] enemyWalkLeftSprites,enemyWalkRightSprites, enemyShotInFrontSprite,enemyShotInBackSprite;
    private Animation enemyWalkLeftAnimation,enemyWalkRightAnimation,enemyShotInBackAnimation,enemyShotInFrontAnimation;

    private float stateTime, deathTime;

    public EnemyView(Enemy enemy){
        this.enemy = enemy;

        if(enemy.isBoss()){
            enemyType = "boss";
            animationSpeed = 1/6f;
            textureRegionWidth = 83;
            textureRegionHeight = 124;
            textureRegionShotInFrontWidth = 83;
         }else{
            enemyType = "enemy";
            animationSpeed = 1/12f;
            textureRegionWidth = 63;
            textureRegionHeight = 90;
            textureRegionShotInFrontWidth = 70;
        }

        try {

            SPRITE_ENEMY_WALK_LEFT_SHEET = ANIMATION_LOCATION + enemyType + "left.png";
            SPRITE_ENEMY_WALK_RIGHT_SHEET = ANIMATION_LOCATION + enemyType + "right.png";
            SPRITE_ENEMY_SHOT_IN_BACK = ANIMATION_LOCATION + enemyType + "shotinback.png";
            SPRITE_ENEMY_SHOT_IN_FRONT = ANIMATION_LOCATION + enemyType + "shotinfront.png";
        }catch (Exception e){
            e.printStackTrace();
        }

        enemyWalkingleft = new Texture(Gdx.files.internal(SPRITE_ENEMY_WALK_LEFT_SHEET));
        enemyWalkingright = new Texture(Gdx.files.internal(SPRITE_ENEMY_WALK_RIGHT_SHEET));
        enemyShotInBack = new Texture(Gdx.files.internal(SPRITE_ENEMY_SHOT_IN_BACK));
        enemyShotInFront = new Texture(Gdx.files.internal(SPRITE_ENEMY_SHOT_IN_FRONT));

        enemyWalkLeftSprites = TextureRegion.split(enemyWalkingleft, textureRegionWidth, textureRegionHeight)[0];
        enemyWalkRightSprites = TextureRegion.split(enemyWalkingright,textureRegionWidth,textureRegionHeight)[0];
        enemyShotInBackSprite = TextureRegion.split(enemyShotInBack,textureRegionWidth,textureRegionHeight)[0];
        enemyShotInFrontSprite = TextureRegion.split(enemyShotInFront,textureRegionShotInFrontWidth,textureRegionHeight)[0];

        enemyWalkLeftAnimation = new Animation(animationSpeed, enemyWalkLeftSprites);
        enemyWalkRightAnimation = new Animation(animationSpeed, enemyWalkRightSprites);
        enemyShotInBackAnimation = new Animation(1/6f, enemyShotInBackSprite);
        enemyShotInFrontAnimation = new Animation(1/6f, enemyShotInFrontSprite);

    }

    /**
     * Draws the enemy
     */
    public void draw(Batch batch, float deltaTime){
        batch.begin();
        batch.draw(getCurrentSprite(), enemy.getPosition().getX(), enemy.getPosition().getY(), enemy.getSize().getWidth(), enemy.getSize().getHeight());
        batch.end();
    }

    /**
     * Get the current sprite to be drawn
     * @return currentFrame
     */
    private TextureRegion getCurrentSprite() {
        stateTime += Gdx.graphics.getDeltaTime();

        if(enemy.isShotInBack()){
            stateTime = 0;
            deathTime += Gdx.graphics.getDeltaTime();
            return enemyShotInBackAnimation.getKeyFrame(deathTime, false);

        }else if(enemy.isShotInFront()){
            stateTime = 0;
            deathTime += Gdx.graphics.getDeltaTime();
            return enemyShotInFrontAnimation.getKeyFrame(deathTime, false);

        }

        switch (enemy.getEnemyState()) {
            case MOVING_RIGHT:
                return enemyWalkRightAnimation.getKeyFrame(stateTime, true);
            case MOVING_LEFT:
                return enemyWalkLeftAnimation.getKeyFrame(stateTime, true);
            case STANDING:
                //nothing yet
                return enemyWalkLeftAnimation.getKeyFrame(stateTime, true);
            default:
                // default case will not appear
                return enemyWalkLeftAnimation.getKeyFrame(stateTime, true);
        }
    }
}
