package edu.chalmers.RunningMan.controller;

import edu.chalmers.RunningMan.model.gameobject.Helicopter;
import edu.chalmers.RunningMan.view.HelicopterView;

/**
 * Created by JohanTobin on 2015-05-12.
 */
public class HelicopterController implements IEntityController{
    private Helicopter helicopter;

    public HelicopterController(Helicopter helicopter){
        this.helicopter = helicopter;
    }

    public void update(float deltaTime){
        if(helicopter.shouldFlyAway()){
            helicopter.move(deltaTime);
        }
    }
}

