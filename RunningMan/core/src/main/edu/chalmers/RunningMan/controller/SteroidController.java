package edu.chalmers.RunningMan.controller;

import edu.chalmers.RunningMan.model.gameobject.Steroid;
import edu.chalmers.RunningMan.view.SteroidView;

/**
 * A class for controlling Steroids
 * @author Jesper Olsson
 */
public class SteroidController implements IEntityController {
    private Steroid steroid;
    private SteroidView steroidView;

    public SteroidController(Steroid steroid, SteroidView steroidView){
        this.steroid = steroid;
        this.steroidView = steroidView;
    }

    public void update(float deltaTime){
        if(steroid.isPickedUp()){
           steroid.getTimer().update(deltaTime);
        }
    }
}
