package edu.chalmers.RunningMan.gameworld;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import edu.chalmers.RunningMan.controllers.*;
import edu.chalmers.RunningMan.entities.*;
import edu.chalmers.RunningMan.utils.MapHandler;
import edu.chalmers.RunningMan.utils.MapHandlerException;
import edu.chalmers.RunningMan.views.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JohanTobin on 2015-04-22.
 */

public class GameWorld  {

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
    }



    public void update(float deltaTime) {
        //if(/*!player.hasFinishedLevel() &&*/ !player.isDead()) {
            for (IEntityController controller : controllers) {
                controller.update(deltaTime);
           }
            levelView.draw();
        //}else{
        //    startLevel();
        //}
    }

    public final void loadLevel() {

        try {
            mapHandler = new MapHandler("level1");
            player = new Player( new Position(mapHandler.getPlayerStartPosition()), new Size(50,50), 100);
            weapon = new Weapon(player);
            bulletView = new BulletView(weapon.getBullets());
            bulletController = new BulletController(weapon.getBullets(), bulletView);
            weaponController = new WeaponController(weapon);
            playerView = new PlayerView(player);
            mapObjects = mapHandler.getPhysicalObjectsList();
            mapObjects.add(player);
            level = new Level(mapObjects,"level1");
            levelController = new LevelController(level,weapon.getBullets());
            playerController = new PlayerController(player, playerView);
            factory = new Factory(mapObjects);
            views = factory.getViews();
            levelView = new LevelView(views, player, bulletView);
            audioController = new AudioController();
            audioController.playMusic();
            levelController = new LevelController(level, weapon.getBullets());

        } catch(MapHandlerException e) {
            System.out.println("loadLevel in GameWorld");
        }
    }

}
