package edu.chalmers.RunningMan.controller;

import edu.chalmers.RunningMan.model.gameobjects.Helicopter;
import edu.chalmers.RunningMan.view.HelicopterView;

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
        if(helicopter.shouldFlyAway()){
            helicopter.move(deltaTime);
        }
    }
}

