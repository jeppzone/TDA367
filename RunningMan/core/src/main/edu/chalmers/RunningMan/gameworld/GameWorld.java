package edu.chalmers.RunningMan.gameworld;

import com.badlogic.gdx.scenes.scene2d.Actor;
import edu.chalmers.RunningMan.controllers.*;
import edu.chalmers.RunningMan.entities.*;
import edu.chalmers.RunningMan.utils.MapHandler;
import edu.chalmers.RunningMan.utils.MapHandlerException;
import edu.chalmers.RunningMan.utils.WindowSize;
import edu.chalmers.RunningMan.views.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * Created by JohanTobin on 2015-04-22.
 */

public class GameWorld implements PropertyChangeListener {

    private BulletController bulletController;
    private BulletView bulletView;
    private PlayerController playerController;
    private Weapon weapon;
    private WeaponController weaponController;
    private Player player;
    private PlayerView playerView;
    private Level level;
    private LevelController levelController;
    private List<IEntityController> controllers;
    private MapHandler mapHandler;
    private List<AbstractPhysicalObject> mapObjects;
    private LevelView levelView;
    private List<Actor> views;
    private Factory factory;
    private AudioController audioController;
    private HudView hudView;
    private PropertyChangeSupport pcs;
    private Time timeSinceDeath;
    private HelicopterController helicopterController;

    private static final float DEATH_ANIMATION_TIME = 1.15f;

    public GameWorld() {
        startLevel();
    }

    public void startLevel(){
        loadLevel();
        controllers = factory.getControllers();
        controllers.add(playerController);
        controllers.add(levelController);
        controllers.add(bulletController);
        controllers.add(weaponController);
        pcs = new PropertyChangeSupport(this);
        timeSinceDeath = new Time(DEATH_ANIMATION_TIME);
        helicopterController = factory.getHelicopterController();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener){
        pcs.removePropertyChangeListener(listener);
    }

    public void update(float deltaTime) {
        if(!player.isDead() && !player.hasFinishedLevel()) {
            updateControllers(deltaTime);
        }else{
            timeSinceDeath.start();
            timeSinceDeath.update(deltaTime);
            updateRemainingControllers(deltaTime);
            if(timeSinceDeath.isTimeUp()){
                audioController.stopIntroMusic();
                if(player.isDead()){
                    pcs.firePropertyChange("dead", null, null);
                }else{
                    pcs.firePropertyChange("finish", null, null);
                    level.addScore();
                    System.out.println(level.getHighScores().get(0));
                }
                timeSinceDeath.resetTime();
                }
            }
        levelView.draw();
        hudView.draw();
    }

    private void updateControllers(float deltaTime){
        for (IEntityController controller : controllers) {
            controller.update(deltaTime);
        }
    }

    private void updateRemainingControllers(float deltaTime){
        helicopterController.update(deltaTime);
        playerController.update(deltaTime);
        levelController.update(deltaTime);
    }

    public final void loadLevel() {

        try {
            audioController = new AudioController();
            mapHandler = new MapHandler("level1");
            player = new Player( new Position(mapHandler.getPlayerStartPosition()), new Size(50,50), 100);
            playerView = new PlayerView(player);
            playerController = new PlayerController(player, playerView);
            player.addPropertyChangeListener(audioController);
            player.addPropertyChangeListener(this);
            weapon = new Weapon(player, new WindowSize());
            weapon.addPropertyChangeListener(audioController);
            bulletView = new BulletView(weapon.getBullets());
            bulletController = new BulletController(weapon.getBullets(), bulletView);
            weaponController = new WeaponController(weapon);
            mapObjects = mapHandler.getPhysicalObjectsList();
            mapObjects.add(player);
            factory = new Factory(mapObjects);
            views = factory.getViews();
            level = new Level(mapObjects,"level1");
            levelController = new LevelController(level, weapon.getBullets());
            levelView = new LevelView(views, player, bulletView,"level1");
            level.addPropertyChangeListener(this);
            level.addPropertyChangeListener(audioController);
            levelController = new LevelController(level,weapon.getBullets());
            player.addPropertyChangeListener(level);
            hudView = new HudView(level);
            audioController.playIntroMusic();


        } catch(MapHandlerException e) {
            System.out.println("loadLevel in GameWorld");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        pcs.firePropertyChange(evt.getPropertyName(), null, null);
    }
}
