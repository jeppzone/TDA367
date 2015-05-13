package edu.chalmers.RunningMan.controllers;

import edu.chalmers.RunningMan.entities.Helicopter;
import edu.chalmers.RunningMan.views.FinishObjectView;

/**
 * Created by JohanTobin on 2015-05-12.
 */
public class FinishObjectController implements IEntityController{
    private Helicopter finishObject;
    private FinishObjectView finishObjectView;

    public FinishObjectController(Helicopter finishObject, FinishObjectView finishObjectView){
        this.finishObject = finishObject;
        this.finishObjectView = finishObjectView;
    }

    public void update(float deltaTime){
        if(finishObject.getFlyaway()){
            finishObject.move(deltaTime);
        }
    }
}

