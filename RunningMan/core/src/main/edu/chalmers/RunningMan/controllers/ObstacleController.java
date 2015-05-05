package edu.chalmers.RunningMan.controllers;

import edu.chalmers.RunningMan.entities.Obstacle;
import edu.chalmers.RunningMan.views.ObstacleView;

/**
 * A class for controlling obstacle objects
 */
public class ObstacleController implements IEntityController{
    private Obstacle obstacle;
    private ObstacleView obstacleView;

    public ObstacleController(Obstacle obstacle, ObstacleView obstacleView){
        this.obstacle = obstacle;
        this.obstacleView = obstacleView;
    }
    public void update(float deltaTime){
        obstacleView.draw();
    }
}
