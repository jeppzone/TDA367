package edu.chalmers.RunningMan.controllers;

import edu.chalmers.RunningMan.model.gameobjects.AbstractPhysicalObject;
import edu.chalmers.RunningMan.model.gameobjects.Level;
import edu.chalmers.RunningMan.model.gameobjects.Enemy;
import edu.chalmers.RunningMan.model.gameobjects.Player;
import edu.chalmers.RunningMan.utils.map.MapHandler;
import edu.chalmers.RunningMan.utils.map.MapHandlerException;
import edu.chalmers.RunningMan.model.Timer;
import edu.chalmers.RunningMan.audio.AudioController;
import edu.chalmers.RunningMan.views.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * The class that controls the flow of the game
 */

public class GameController implements PropertyChangeListener {

    private Player player;
    private Level level;
    private List<IEntityController> controllers;
    private MapHandler mapHandler;
    private List<AbstractPhysicalObject> mapObjects;
    private LevelView levelView;
    private ControllerFactory factory;
    private AudioController audioController;
    private HudView hudView;
    private PropertyChangeSupport pcs;
    private Timer timerSinceDeath;
    private HelicopterController helicopterController;
    private String levelName;
    private Timer loadTimer;
    private List<Enemy> enemies;
    private LevelController levelController;
    private PlayerController playerController;

    private static final float DEATH_ANIMATION_TIME = 1.15f;

    public GameController(String levelName) {
        this.levelName = levelName;
        startLevel();
    }

    /**
     * Updates the game
     * @param deltaTime the difference in time
     */
    public void update(float deltaTime) {
        loadTimer.update(deltaTime); // Check to see if the game is done loading
        if(loadTimer.isTimeUp()) {
            if (!player.isDead() && !player.hasFinishedLevel()) {
                updateControllers(deltaTime);
            } else {
                updateDeathTimer(deltaTime);
                checkDeathTimer();
                updateRemainingControllers(deltaTime);
                playerController.reset();
            }
            drawViews();
        }
    }

    private void startLevel(){
        loadTimer = new Timer(4);
        loadTimer.start();
        loadLevel();
        controllers = factory.getControllers();
        pcs = new PropertyChangeSupport(this);
        timerSinceDeath = new Timer(DEATH_ANIMATION_TIME);
        helicopterController = factory.getHelicopterController();
    }

    private void loadLevel() {
        try {
            mapHandler = new MapHandler(levelName);
            mapObjects = mapHandler.getPhysicalObjectsList();
            enemies = mapHandler.getEnemies();
            factory = new ControllerFactory(mapObjects, enemies, levelName);
            audioController = factory.getAudioController();
            player = factory.getPlayer();
            player.addPropertyChangeListener(this);
            playerController = factory.getPlayerController();
            level = factory.getLevel();
            levelView = factory.getLevelView();
            levelController = factory.getLevelController();
            level.addPropertyChangeListener(this);
            player.addPropertyChangeListener(level);
            hudView = new HudView(level);
            audioController.playMusic();
        } catch (MapHandlerException e) {
            System.out.println("loadLevel in GameController");
        }
    }

    /**
     * Updates all controllers in the game
     * @param deltaTime the time difference
     */
    private void updateControllers(float deltaTime){
        for (IEntityController controller : controllers) {
            controller.update(deltaTime);
        }
    }

    /**
     * The controllers that should be updated after
     * the level is finished/player is dead/time is up
     * @param deltaTime the time difference
     */
    private void updateRemainingControllers(float deltaTime){
        helicopterController.update(deltaTime);
        playerController.update(deltaTime);
        levelController.update(deltaTime);
    }

    /**
     * Updates the time since the player died or finished the level. During this timer the
     * death animation or finish animation will be played
     * @param deltaTime the time difference
     */
    private void updateDeathTimer(float deltaTime){
        timerSinceDeath.start();
        timerSinceDeath.update(deltaTime);
    }

    /**
     * Checks if the timer since the player's death or since the player finished
     * the level is up. If it is, change screens accordingly
     */
    private void checkDeathTimer(){
        if (timerSinceDeath.isTimeUp()) {
            audioController.stopMusic();
            if (player.isDead()) {
                pcs.firePropertyChange("mainMenu", null, null);
            } else {
                setHighScores();
                pcs.firePropertyChange("showHighScore", null, null);

            }
            timerSinceDeath.resetTime();
        }
    }

    private void drawViews(){
        levelView.draw();
        hudView.draw();
    }

    private void setHighScores(){
        level.setPlayerScore();
    }


    public void addPropertyChangeListener(PropertyChangeListener listener){
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener){
        pcs.removePropertyChangeListener(listener);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        pcs.firePropertyChange(evt.getPropertyName(), null, null);
    }
}
