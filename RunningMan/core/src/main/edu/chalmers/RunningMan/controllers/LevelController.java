package edu.chalmers.RunningMan.controllers;

import edu.chalmers.RunningMan.entities.Level;

/**
 * A class to represent the Controller of a level
 * @author Jesper Olsson
 */
public class LevelController implements IEntityController {
    private Level level;

    public LevelController(Level level){
        this.level = level;
    }

    public void update(float deltaTime){
        level.checkCollisions();
    }
}
