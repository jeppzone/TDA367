package edu.chalmers.RunningMan.views;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import edu.chalmers.RunningMan.entities.Player;

/**
 * @author Jesper
 */
public class KillCountView {
    private int killCount;
    private Player player;
    private BitmapFont font;
    private CharSequence stringKillCount;

    public KillCountView(Player player){
        this.player = player;
    }

    public void draw(Batch batch, float deltaTime){
        font = new BitmapFont();
        stringKillCount = player.getKillCount() +"";
        batch.begin();
        font.draw(batch, stringKillCount, 400, Gdx.graphics.getHeight()- 20);
        batch.end();


    }
}
