package edu.chalmers.RunningMan.controllers;

import edu.chalmers.RunningMan.entities.Steroid;
import edu.chalmers.RunningMan.views.SteroidView;

/**
 * Created by Jesper on 4/28/2015.
 */
public class SteroidController implements IController {
    private Steroid steroid;
    private SteroidView steroidView;

    public SteroidController(Steroid steroid, SteroidView steroidView){
        this.steroid = steroid;
        this.steroidView = steroidView;
    }

    public void update(float deltaTime){
        if(!steroid.isPickedUp()){
            steroidView.draw();
        }
    }
}
