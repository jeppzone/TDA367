package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import edu.chalmers.RunningMan.entities.Player;

/**
 * A class to represent the view of the Player.
 */
public class PlayerView {

    private final Player player;
    private final SpriteBatch spriteBatch;

    private final Texture walkSheet;
    private TextureRegion[] walkFrames;
    private Animation walkAnimation;

    private float stateTime;

    public PlayerView(Player player){
        this.player = player;

        walkSheet = new Texture("core/assets/walk_sheet_soldier.png");
        walkFrames = TextureRegion.split(walkSheet, 58, 60)[0];
        walkAnimation = new Animation(1/12f, walkFrames);

        spriteBatch = new SpriteBatch();
    }

    /**
     * Draws the player
     */
    public void draw() {
        spriteBatch.begin();
        spriteBatch.draw(getCurrentFrame(), player.getPosition().getX(),player.getPosition().getY(), player.getSize().getWidth(), player.getSize().getHeight());
        spriteBatch.end();

    }

    /**
     * Get the current frame to be drawn
     * @return currentFrame
     */
    private TextureRegion getCurrentFrame() {
        stateTime += Gdx.graphics.getDeltaTime();

        if(player.isOnGround()) {
            return walkAnimation.getKeyFrame(stateTime, true);
        } else {
            // TODO get frame for jump movement, not walkAnimation frame
            return walkAnimation.getKeyFrame(stateTime, true);
        }

    }

}
