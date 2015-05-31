package edu.chalmers.RunningMan.controller;

import edu.chalmers.RunningMan.model.gameobject.Steroid;

/**
 * A class for controlling Steroids
 * @author Jesper Olsson
 */
public class SteroidController implements IEntityController {
    private final Steroid steroid;

    public SteroidController(Steroid steroid){
        this.steroid = steroid;
    }

    public void update(float deltaTime){
        if(steroid.isPickedUp()){
           steroid.getTimer().update(deltaTime);
        }
    }
}
