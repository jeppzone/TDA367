package edu.chalmers.RunningMan.controllers;

import edu.chalmers.RunningMan.entities.Level;
import edu.chalmers.RunningMan.views.LevelView;

/**
 * A class to represent the Controller of a level
 */
public class LevelController {
    private Level level;
    private LevelView levelView;

    public LevelController(Level level, LevelView levelView){
        this.level = level;
        this.levelView = levelView;
    }

    public void update(){
        level.checkCollisions();
        levelView.draw();
    }
}
