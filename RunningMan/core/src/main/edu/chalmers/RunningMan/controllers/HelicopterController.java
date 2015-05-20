package edu.chalmers.RunningMan.controllers;

import edu.chalmers.RunningMan.model.level.mapobjects.Helicopter;
import edu.chalmers.RunningMan.views.HelicopterView;

/**
 * Created by JohanTobin on 2015-05-12.
 */
public class HelicopterController implements IEntityController{
    private Helicopter helicopter;
    private HelicopterView helicopterView;

    public HelicopterController(Helicopter helicopter, HelicopterView helicopterView){
        this.helicopter = helicopter;
        this.helicopterView = helicopterView;
    }

    public void update(float deltaTime){
        if(helicopter.getFlyaway()){
            helicopter.move(deltaTime);
        }
    }
}

