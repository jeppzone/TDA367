package edu.chalmers.RunningMan.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import edu.chalmers.RunningMan.model.*;
import edu.chalmers.RunningMan.audio.AudioController;
import edu.chalmers.RunningMan.model.gameobject.*;
import edu.chalmers.RunningMan.util.highscore.HighScore;
import edu.chalmers.RunningMan.util.WindowSize;
import edu.chalmers.RunningMan.view.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ControllerFactory provides controllers, views and models
 */
public class ControllerFactory {
    private final List<AbstractPhysicalObject> mapObjects;
    private final List<Actor> actors;
    private final List<IEntityController> controllers;
    private HelicopterController helicopterController;
    private final String levelName;
    private final List<Enemy> enemies;
    private Player player;
    private Weapon weapon;
    private BulletView bulletView;
    private AudioController audioController;
    private LevelView levelView;
    private Level level;
    private PlayerController playerController;
    private LevelController levelController;
    private static HighScore highScore;

    public ControllerFactory(List<AbstractPhysicalObject> mapObjects, List<Enemy> enemies, String levelName){
        this.mapObjects = mapObjects;
        this.enemies = enemies;
        actors = new ArrayList<>();
        controllers = new ArrayList<>();
        audioController = new AudioController(levelName);
        this.levelName = levelName;
        addMapObjects();
        addPlayer();
        addWeaponAndBullets();
        addLevel();
        addAudioController();
        addEnemies();
        highScore = new HighScore(level);
    }

    private void addMapObjects() {
        for(final AbstractPhysicalObject apo: mapObjects) {
            if(apo.getClass() == Pit.class) {
                Pit pit = (Pit) apo;
                PitView pitView = new PitView(pit, levelName);
                actors.add(pitView);
            } else if(apo.getClass() == Ground.class) {
                Ground ground = (Ground) apo;
                GroundView groundView = new GroundView(ground, levelName);
                actors.add(groundView);
            } else if(apo.getClass() == Steroid.class) {
                Steroid steroid = (Steroid) apo;
                SteroidView steroidView = new SteroidView(steroid);
                controllers.add(new SteroidController(steroid));
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
                helicopterController = new HelicopterController(helicopter);
                controllers.add(helicopterController);
            }
        }
    }

    private void addEnemies(){
        for(final Enemy enemy: enemies){
            enemy.addPropertyChangeListener(audioController);
            EnemyView enemyView = new EnemyView(enemy);
            actors.add(enemyView);
            controllers.add(new EnemyController(enemy, enemyView));
        }
    }

    private void addWeaponAndBullets(){
        weapon = new Weapon(player, new WindowSize());
        WeaponController weaponController = new WeaponController(weapon);
        bulletView = new BulletView(weapon.getBullets());
        BulletController bulletController = new BulletController(weapon.getBullets(), bulletView);
        controllers.add(bulletController);
        controllers.add(weaponController);
    }

    private void addPlayer(){
        player = new Player(new Position(60, 1000)/*mapHandler.getPlayerStartPosition()*/, new Size(50, 50), 100);
        PlayerView playerView = new PlayerView(player, levelName);
        playerController = new PlayerController(player);
        actors.add(playerView);
        controllers.add(playerController);
    }

    private void addLevel(){
        level = new Level(player, mapObjects, enemies, levelName);
        levelController = new LevelController(level, weapon.getBullets());
        levelView = new LevelView(actors, player, bulletView, levelName);
        controllers.add(levelController);
    }

    private void addAudioController(){
        audioController = new AudioController(levelName);
        player.addPropertyChangeListener(audioController);
        weapon.addPropertyChangeListener(audioController);
        level.addPropertyChangeListener(audioController);
    }

    /**
     * @return the controller for the Helicopter"
     */
    public HelicopterController getHelicopterController(){
       return helicopterController;
    }

    /**
     * @return the list of entitycontrollers
     */
    public List<IEntityController> getControllers(){
        return controllers;
    }

    /**
     * @return the view of the level
     */
    public LevelView getLevelView(){
        return levelView;
    }

    /**
     * @return the audiocontroller for the game
     */
    public AudioController getAudioController(){
        return audioController;
    }

    /**
     * @return the level model
     */
    public Level getLevel(){
        return level;
    }

    /**
     * @return the player model
    */
    public Player getPlayer(){
        return player;
    }

    /**
     * @return the playercontroller
     */
    public PlayerController getPlayerController(){
        return playerController;
    }

    /**
     * @return the levelcontroller
     */
    public LevelController getLevelController(){
        return levelController;
    }

    /**
     * @return the highscore object
     */
    public static HighScore getHighScore(){
        return highScore;
    }
}
