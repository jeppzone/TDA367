package edu.chalmers.RunningMan.gameworld;

import com.badlogic.gdx.scenes.scene2d.Actor;
import edu.chalmers.RunningMan.controllers.*;
import edu.chalmers.RunningMan.model.*;
import edu.chalmers.RunningMan.audio.AudioController;
import edu.chalmers.RunningMan.model.objects.*;
import edu.chalmers.RunningMan.utils.highscore.HighScore;
import edu.chalmers.RunningMan.utils.WindowSize;
import edu.chalmers.RunningMan.views.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory that provides models, views and controllers
 */
public class Factory {
    private List<AbstractPhysicalObject> mapObjects;
    private List<Actor> actors;
    private List<IEntityController> controllers;
    private HelicopterController helicopterController;
    private String levelName;
    private List<Enemy> enemies;
    private Player player;
    private Weapon weapon;
    private BulletView bulletView;
    private AudioController audioController;
    private LevelView levelView;
    private Level level;
    private PlayerController playerController;
    private LevelController levelController;
    private static HighScore highScore;

    public Factory(List<AbstractPhysicalObject> mapObjects, List<Enemy> enemies, String levelName){
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
        playerController = new PlayerController(player, playerView);
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

    public HelicopterController getHelicopterController(){
       return helicopterController;
    }

    public List<IEntityController> getControllers(){
        return controllers;
    }

    public LevelView getLevelView(){
        return levelView;
    }

    public AudioController getAudioController(){
        return audioController;
    }

    public Level getLevel(){
        return level;
    }

    public Player getPlayer(){
        return player;
    }

    public PlayerController getPlayerController(){
        return playerController;
    }

    public LevelController getLevelController(){
        return levelController;
    }

    public static HighScore getHighScore(){
        return highScore;
    }
}
