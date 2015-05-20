package edu.chalmers.RunningMan.gameworld;

import com.badlogic.gdx.scenes.scene2d.Actor;
import edu.chalmers.RunningMan.controllers.*;
import edu.chalmers.RunningMan.model.*;
import edu.chalmers.RunningMan.model.level.mapobjects.Ground;
import edu.chalmers.RunningMan.model.level.mapobjects.Helicopter;
import edu.chalmers.RunningMan.model.level.mapobjects.Obstacle;
import edu.chalmers.RunningMan.model.level.mapobjects.Pit;
import edu.chalmers.RunningMan.model.level.mapobjects.powerups.Steroid;
import edu.chalmers.RunningMan.model.level.mapobjects.livingentities.objects.Enemy;
import edu.chalmers.RunningMan.utils.audio.AudioController;
import edu.chalmers.RunningMan.views.*;

import java.util.ArrayList;
import java.util.List;

public class Factory {
    private List<AbstractPhysicalObject> mapObjects;
    private List<Actor> actors;
    private List<IEntityController> controllers;
    private AudioController audioController;
    private HelicopterController helicopterController;
    private String levelName;

    public Factory(List<AbstractPhysicalObject> mapObjects, String levelName){
        this.mapObjects = mapObjects;
        actors = new ArrayList<>();
        controllers = new ArrayList<>();
        audioController = new AudioController(levelName);
        this.levelName = levelName;
        addViewsAndControllers();
    }

    private void addViewsAndControllers() {
        for(final AbstractPhysicalObject apo: mapObjects) {
            if(apo.getClass() == Pit.class) {
                Pit pit = (Pit) apo;
                PitView pitView = new PitView(pit, levelName);
                actors.add(pitView);
            } else if(apo.getClass() == Ground.class) {
                Ground ground = (Ground) apo;
                GroundView groundView = new GroundView(ground, levelName);
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
                ObstacleView obstacleView = new ObstacleView(obstacle, levelName);
                actors.add(obstacleView);
            }else if(apo.getClass() == Helicopter.class){
                Helicopter helicopter = (Helicopter) apo;
                helicopter.addPropertyChangeListener(audioController);
                HelicopterView helicopterView = new HelicopterView(helicopter);
                actors.add(helicopterView);
                helicopterController = new HelicopterController(helicopter, helicopterView);
                controllers.add(helicopterController);
            }
        }
    }
    public HelicopterController getHelicopterController(){
       return helicopterController;
    }
    public List<Actor> getViews(){
        return actors;
    }
    public List<IEntityController> getControllers(){
        return controllers;
    }
}
