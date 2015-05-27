package edu.chalmers.RunningMan.view;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import edu.chalmers.RunningMan.model.gameobject.Level;

/**
 * @author Jesper
 */
public class KillCountView extends HudFont {

    private Level level;
    private CharSequence stringKillCount;

    public KillCountView(Level level){
        this.level = level;
        generateFont();
    }

    public void draw(Batch batch, float deltaTime){
        stringKillCount = "Kills: " + level.getEnemiesKilled();
        batch.begin();
        font.draw(batch, stringKillCount, 400, Gdx.graphics.getHeight()- 20);
        batch.end();
    }
}
