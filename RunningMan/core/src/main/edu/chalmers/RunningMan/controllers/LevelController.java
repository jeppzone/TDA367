package edu.chalmers.RunningMan.controllers;

import com.badlogic.gdx.Gdx;
import edu.chalmers.RunningMan.entities.Bullet;
import edu.chalmers.RunningMan.entities.Level;
import edu.chalmers.RunningMan.entities.Player;
import edu.chalmers.RunningMan.views.LevelView;

import java.util.List;

import java.util.List;

/**
 * A class to represent the Controller of a level
 * @author Jesper Olsson
 */
public class LevelController implements IEntityController {
    private Level level;
    private List<Bullet> bullets;
    private Player player;

    public LevelController(Level level, List<Bullet> bullets, Player player){
        this.level = level;
        this.bullets = bullets;
        this.player = player;
    }

    /**
     *
     * @param deltaTime
     */

    public void update(float deltaTime){
        for(int i = 0; i < bullets.size(); i ++ ){
            if(isOutOfBounds(bullets.get(i))){
                bullets.remove(i);
            }
        }
        level.checkCollisions(bullets);
    }
//isOutOfBounds(bullets.get(i))
    /**
     * A methog that checks if a bullets position is out of the game screen
     * @param bullet the bullet which position is being checked.
     * @return true if the bullet is out of the game screen.
     */
    public boolean isOutOfBounds(Bullet bullet){
        if(player.getPosition().getX() < 220){
            return bullet.getPosition().getX() < player.getPosition().getX()- Gdx.graphics.getWidth()/3
                    || bullet.getPosition().getX() > player.getPosition().getX() + Gdx.graphics.getWidth() -40 ;
        }
        return bullet.getPosition().getX() < player.getPosition().getX()- Gdx.graphics.getWidth()/2
                || bullet.getPosition().getX() > player.getPosition().getX() + Gdx.graphics.getWidth()/2 ;

    }

}
