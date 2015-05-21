package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import edu.chalmers.RunningMan.model.level.mapobjects.livingentities.objects.Enemy;

/**
 * A class to represent the view of the enemy.
 */
public class EnemyView extends Actor{
    private final Enemy enemy;

    private final Texture enemyWalkingleft, enemyWalkingright, enemyShotInBack,enemyShotInFront;

    private final static String SPRITE_ENEMY_WALK_LEFT_SHEET = "core/assets/enemyleft.png";
    private final static String SPRITE_ENEMY_WALK_RIGHT_SHEET = "core/assets/enemyright.png";
    private final static String SPRITE_ENEMY_SHOT_IN_BACK = "core/assets/enemyshotinback.png";
    private final static String SPRITE_ENEMY_SHOT_IN_FRONT = "core/assets/enemyshotinfront.png";

    private TextureRegion[] enemyWalkLeftSprites,enemyWalkRightSprites, enemyShotInFrontSprite,enemyShotInBackSprite;
    private Animation enemyWalkLeftAnimation,enemyWalkRightAnimation,enemyShotInBackAnimation,enemyShotInFrontAnimation;

    private float stateTime, deathTime;

    public EnemyView(Enemy enemy){
        this.enemy = enemy;

        try {
            enemyWalkingleft = new Texture(Gdx.files.internal(SPRITE_ENEMY_WALK_LEFT_SHEET));
            enemyWalkingright = new Texture(Gdx.files.internal(SPRITE_ENEMY_WALK_RIGHT_SHEET));
            enemyShotInBack = new Texture(Gdx.files.internal(SPRITE_ENEMY_SHOT_IN_BACK));
            enemyShotInFront = new Texture(Gdx.files.internal(SPRITE_ENEMY_SHOT_IN_FRONT));
        }catch (Exception e){
            throw new NullPointerException("Could not load enemy image in " + this.getClass().toString());
        }
        enemyWalkLeftSprites = TextureRegion.split(enemyWalkingleft, 63, 90)[0];
        enemyWalkRightSprites = TextureRegion.split(enemyWalkingright,63,90)[0];
        enemyShotInBackSprite = TextureRegion.split(enemyShotInBack,63,90)[0];
        enemyShotInFrontSprite = TextureRegion.split(enemyShotInFront,70,90)[0];

        enemyWalkLeftAnimation = new Animation(1/12f, enemyWalkLeftSprites);
        enemyWalkRightAnimation = new Animation(1/12f, enemyWalkRightSprites);
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

        if(enemy.isShotInback()){
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
