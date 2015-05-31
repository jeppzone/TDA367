package edu.chalmers.RunningMan.controller;

import edu.chalmers.RunningMan.model.gameobject.Helicopter;
import edu.chalmers.RunningMan.view.HelicopterView;

/**
 * A class for controlling the helicopter
 */
public class HelicopterController implements IEntityController{
    private final Helicopter helicopter;

    public HelicopterController(Helicopter helicopter){
        this.helicopter = helicopter;
    }

    public void update(float deltaTime){
        if(helicopter.shouldFlyAway()){
            helicopter.move(deltaTime);
        }
    }
}

