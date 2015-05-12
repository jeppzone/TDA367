package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import edu.chalmers.RunningMan.entities.Enemy;

/**
 * A class to represent the view of the enemy.
 */
public class EnemyView extends Actor implements IView {
    private final Enemy enemy;
    private final SpriteBatch sb;

    private final Texture enemyWalkingleft, enemyWalkingright;

    private final static String SPRITE_ENEMY_WALK_LEFT_SHEET = "core/assets/enemyleft.png";
    private final static String SPRITE_ENEMY_WALK_RIGHT_SHEET = "core/assets/enemyright.png";


    private TextureRegion[] enemyWalkLeftSprites,enemyWalkRightSprites;
    private Animation enemyWalkLeftAnimation,enemyWalkRightAnimation;

    private float stateTime;

    public EnemyView(Enemy enemy){
        this.enemy = enemy;

        enemyWalkingleft = new Texture(Gdx.files.internal(SPRITE_ENEMY_WALK_LEFT_SHEET));
        enemyWalkingright = new Texture(Gdx.files.internal(SPRITE_ENEMY_WALK_RIGHT_SHEET));

        enemyWalkLeftSprites = TextureRegion.split(enemyWalkingleft, 63, 90)[0];
        enemyWalkRightSprites = TextureRegion.split(enemyWalkingright,63,90)[0];

        enemyWalkLeftAnimation = new Animation(1/12f, enemyWalkLeftSprites);
        enemyWalkRightAnimation = new Animation(1/12f, enemyWalkRightSprites);

        sb = new SpriteBatch();

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
