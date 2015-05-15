package edu.chalmers.RunningMan.views;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import edu.chalmers.RunningMan.entities.Player;

/**
 * @author Jesper
 */
public class KillCountView extends HudFont {
    private int killCount;
    private Player player;
    private CharSequence stringKillCount;

    public KillCountView(Player player){
        this.player = player;
        generateFont();
    }

    public void draw(Batch batch, float deltaTime){
        stringKillCount = "Kills: " + player.getKillCount();
        batch.begin();
        font.draw(batch, stringKillCount, 400, Gdx.graphics.getHeight()- 20);
        batch.end();


    }
}
