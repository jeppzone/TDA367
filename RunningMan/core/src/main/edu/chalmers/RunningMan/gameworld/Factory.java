package edu.chalmers.RunningMan.gameworld;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.scenes.scene2d.Actor;
import edu.chalmers.RunningMan.controllers.*;
import edu.chalmers.RunningMan.entities.*;
import edu.chalmers.RunningMan.views.*;

import java.util.ArrayList;
import java.util.List;

public class Factory {
    private List<AbstractPhysicalObject> mapObjects;
    private List<Actor> actors;
    private List<IEntityController> controllers;
    private AudioController audioController;

    public Factory(List<AbstractPhysicalObject> mapObjects){
        this.mapObjects = mapObjects;
        actors = new ArrayList<>();
        controllers = new ArrayList<>();
        audioController = new AudioController();
        addViewsAndControllers();
    }

    private void addViewsAndControllers() {
        for(final AbstractPhysicalObject apo: mapObjects) {

            if(apo.getClass() == Pit.class) {
                Pit pit = (Pit) apo;
                PitView pitView = new PitView(pit);
                controllers.add(new PitController(pit, pitView));
                actors.add(pitView);
            } else if(apo.getClass() == Ground.class) {
                Ground ground = (Ground) apo;
                GroundView groundView = new GroundView(ground);
                controllers.add(new GroundController(ground, groundView));
                actors.add(groundView);
            } else if(apo.getClass() == Enemy.class) {
                Enemy enemy = (Enemy) apo;
                enemy.addPropertyChangeListener(audioController);
                EnemyView enemyView = new EnemyView(enemy);
                controllers.add(new EnemyController(enemy, enemyView));
                actors.add(enemyView);
            } else if(apo.getClass() == Steroid.class) {
                Steroid steroid = (Steroid) apo;
                SteroidView steroidView = new SteroidView(steroid);
                controllers.add(new SteroidController(steroid, steroidView));
                actors.add(steroidView);
            } else if(apo.getClass() == Obstacle.class) {
                Obstacle obstacle = (Obstacle) apo;
                ObstacleView obstacleView = new ObstacleView(obstacle);
                actors.add(obstacleView);
            }else if(apo.getClass() == Helicopter.class){
                Helicopter finishObject = (Helicopter) apo;
                HelicopterView helicopterView = new HelicopterView(finishObject);
                actors.add(helicopterView);
                controllers.add(new HelicopterController(finishObject, helicopterView));
            }
        }
    }

    public List<Actor> getViews(){
        return actors;
    }
    public List<IEntityController> getControllers(){
        return controllers;
    }
}
