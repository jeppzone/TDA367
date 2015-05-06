package edu.chalmers.RunningMan.gameworld;

import com.badlogic.gdx.graphics.OrthographicCamera;
import edu.chalmers.RunningMan.controllers.*;
import edu.chalmers.RunningMan.entities.*;
import edu.chalmers.RunningMan.handlers.MapHandler;
import edu.chalmers.RunningMan.handlers.MapHandlerException;
import edu.chalmers.RunningMan.views.*;

import java.util.ArrayList;
import java.util.List;

import static edu.chalmers.RunningMan.utils.Constants.V_HEIGHT;
import static edu.chalmers.RunningMan.utils.Constants.V_WIDTH;

/**
 * Created by JohanTobin on 2015-04-22.
 */

public class GameWorld implements IBulletCollection {

    private OrthographicCamera cam;
    private List<AbstractPhysicalObject> mapObjects;
    private List<Bullet> bullets;
    private BulletController bulletController;
    private BulletView bulletView;
    private PlayerController playerController;
    private Weapon weapon;
    private Player player;
    private PlayerView playerView;
    private Enemy enemy;
    private EnemyView enemyView;
    private EnemyController enemyController;
    private Obstacle obstacle;
    private ObstacleView obstacleView;
    private ObstacleController obstacleController;
    private Level level;
    private LevelController levelController;
    private Steroid steroid;
    private SteroidView steroidView;
    private SteroidController steroidController;
    private List<IEntityController> controllers;
    private MapHandler mapHandler;
    private List<Enemy> enemies;
    private List<Ground> grounds;



    public GameWorld() {
        cam = new OrthographicCamera();
        cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
        mapObjects = new ArrayList<>();
        controllers = new ArrayList<>();
        loadLevel();

    }

    /**
     * Creates a bullet and adds it to the GameWorld
     */
    public void createBullet(Position position){
        bullets.add(new Bullet(new Size(10,10),position, player.getFacingDirection()));
    }

    public void update(float deltaTime) {

        //Gdx.app.log("GameWorld", "update");
        for(IEntityController controller : controllers) {
            controller.update(deltaTime);
        }

    }

    public final void loadLevel() {

        try {

            mapHandler = new MapHandler("level1");
            bullets = new ArrayList<>();
            bulletView = new BulletView(bullets);
            bulletController = new BulletController(bullets, bulletView);
            weapon = new Weapon(new Size(1,1),new Position(0,0), this);
            player = new Player(weapon, new Position(0,0), new Size(50,50), 100);
            playerView = new PlayerView(player);
            level = new Level(mapHandler.getPhysicalObjectsList(), player, "level1");
            levelController = new LevelController(level);
            addPhysicalObjectViews(mapHandler.getPhysicalObjectsList());
            playerController = new PlayerController(player, playerView);
            controllers.add(playerController);
            controllers.add(bulletController);
            controllers.add(levelController);

        } catch(MapHandlerException e) {

        }
    }

    public void addPhysicalObjectViews(List<AbstractPhysicalObject> apoList) {
        enemies = new ArrayList<Enemy>();
        for(final AbstractPhysicalObject apo: apoList) {
            if(apo.getClass() == Ground.class) {
                Ground ground = (Ground) apo;
                GroundView groundView = new GroundView(ground);
                controllers.add(new GroundController(ground, groundView));
            } else if(apo.getClass() == Enemy.class) {
                Enemy enemy = (Enemy) apo;
                EnemyView enemyView = new EnemyView(enemy);
                controllers.add(new EnemyController(enemy, enemyView));
            } else if(apo.getClass() == Steroid.class) {
                Steroid steroid = (Steroid) apo;
                SteroidView steroidView = new SteroidView(steroid);
                controllers.add(new SteroidController(steroid, steroidView));
            } else if(apo.getClass() == Obstacle.class) {
                Obstacle obstacle = (Obstacle) apo;
                ObstacleView obstacleView = new ObstacleView(obstacle);
                controllers.add(new ObstacleController(obstacle, obstacleView));
            }
        }
    }
}
