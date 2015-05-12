package edu.chalmers.RunningMan.controllers;

import edu.chalmers.RunningMan.entities.Pit;
import edu.chalmers.RunningMan.views.PitView;

/**
 * Created by Kvist1 on 2015-05-12.
 */

public class PitController implements IEntityController {

    private Pit pit;
    private PitView pitView;

    public PitController(Pit pit, PitView pitView) {
        this.pit = pit;
        this.pitView = pitView;
    }

    @Override
    public void update(float deltaTime) {
        //pitView.draw();
    }
}
