package edu.chalmers.RunningMan.gameworld;

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

public class GameWorld implements IBulletCollection {

    private List<Bullet> bullets;
    private BulletController bulletController;
    private BulletView bulletView;
    private PlayerController playerController;
    private Weapon weapon;
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


    public GameWorld() {
        startLevel();
    }

    public void startLevel(){
        loadLevel();
        controllers = factory.getControllers();
        controllers.add(playerController);
        controllers.add(levelController);
        controllers.add(bulletController);
    }

    /**
     * Places a bullet correctly in front of the gun when a bullet is fired
     */
    public void placeBullet(){
        if(player.getFacingDirection().xDirection > 0)
            createBullet(24);
        else
            createBullet(-34);
    }


    /**
     * A helper class for placeBullet(), creates the bullet on the position where the bullet is supposed to be placed
     * @param pos a value that changes depends on the players facing direction
     */
    private void createBullet(int pos){
        bullets.add(new Bullet(new Size(10, 10),
                new Position(player.getPosition().getX() + pos + (player.getSize().getWidth()) / 2, player.getPosition().getY() - 6 + (player.getSize().getHeight()) / 2),
                player.getFacingDirection()));
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
            bullets = new ArrayList<>();
            bulletView = new BulletView(bullets);
            bulletController = new BulletController(bullets, bulletView);
            weapon = new Weapon(this);
            player = new Player(weapon, new Position(mapHandler.getPlayerStartPosition()), new Size(50,50), 100);
            playerView = new PlayerView(player);
            mapObjects = mapHandler.getPhysicalObjectsList();
            mapObjects.add(player);
            level = new Level(mapObjects,"level1");
            levelController = new LevelController(level,bullets);
            playerController = new PlayerController(player, playerView);
            factory = new Factory(mapObjects);
            views = factory.getViews();
            levelView = new LevelView(views, player, bulletView);
            levelController = new LevelController(level, bullets);

        } catch(MapHandlerException e) {
            System.out.println("loadLevel in GameWorld");
        }
    }

}
