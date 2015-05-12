package edu.chalmers.RunningMan.controllers;

import edu.chalmers.RunningMan.entities.Bullet;
import edu.chalmers.RunningMan.entities.Level;
import edu.chalmers.RunningMan.views.LevelView;

import java.util.List;

/**
 * A class to represent the Controller of a level
 * @author Jesper Olsson
 */
public class LevelController implements IEntityController {
    private Level level;
    private LevelView levelView;

    public LevelController(Level level, LevelView levelView){
        this.level = level;
        this.levelView = levelView;
    }

    /**
     *
     * @param deltaTime
     */

    public void update(float deltaTime){
        for(int i = 0; i < bullets.size(); i ++ ){
            if(bullets.get(i).isOutOfBounds()){
                bullets.remove(i);
            }
        }
        level.checkCollisions(bullets);
    }
}
