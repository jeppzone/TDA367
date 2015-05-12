package edu.chalmers.RunningMan.controllers;

import edu.chalmers.RunningMan.entities.Ground;
import edu.chalmers.RunningMan.views.GroundView;

/**
 * Created by Kvist1 on 2015-05-04.
 */
public class GroundController implements IEntityController {

    private Ground ground;
    private GroundView groundView;

    public GroundController(Ground ground, GroundView groundView) {
        this.ground = ground;
        this.groundView = groundView;
    }

    @Override
    public void update(float deltaTime) {
        //groundView.draw();
    }
}
