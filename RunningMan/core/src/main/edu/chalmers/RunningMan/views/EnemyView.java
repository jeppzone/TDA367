package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chalmers.RunningMan.entities.Enemy;

/**
 * Created by Jesper on 4/25/2015.
 */
public class    EnemyView {
    private final Enemy enemy;
    private final SpriteBatch sb;
    private final Texture stand;

    public EnemyView(Enemy enemy){
        this.enemy = enemy;
        sb = new SpriteBatch();
        stand = new Texture(Gdx.files.internal("core/assets/pacman.png"));
    }

    public void draw(){
        sb.begin();
        sb.draw(stand, enemy.getPosition().getX(), enemy.getPosition().getY(), enemy.getSize().getWidth(), enemy.getSize().getHeight());
        sb.end();
    }
}
