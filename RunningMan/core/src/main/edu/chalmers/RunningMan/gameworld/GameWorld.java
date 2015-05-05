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
public class GameWorld {

    private OrthographicCamera cam;
    private List<AbstractPhysicalObject> mapObjects;
    private PlayerController playerController;
    private Player player;
    private PlayerView playerView;
    private Enemy enemy;
    private EnemyView enemyView;
    private EnemyController enemyController;
    private Obstacle obstacle;
    private ObstacleView obstacleView;
    private ObstacleController obstacleController;
    private Level level;
    private LevelView levelView;
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
        mapObjects = new ArrayList<AbstractPhysicalObject>();
        controllers = new ArrayList<IEntityController>();

        /* // test code
        player = new Player(new Weapon(), new Position(0,0), new Size(50,50), 100);
        playerView = new PlayerView(player);
        playerController = new PlayerController(player, playerView);

        enemy = new Enemy(new Position(300,0), new Size(30,60), 10);
        enemyView = new EnemyView(enemy);
        enemyController = new EnemyController(enemy, enemyView);

        obstacle = new Obstacle(new Position(500, 0), new Size(60,60));
        obstacleView = new ObstacleView(obstacle);
        obstacleController = new ObstacleController(obstacle,obstacleView);

        steroid = new Steroid(new Position(400, 0), new Size(50, 50));
        steroidView = new SteroidView(steroid);
        steroidController = new SteroidController(steroid, steroidView);

        level = new Level(mapObjects, player, "Beach");
        levelView = new LevelView(level);
        levelController = new LevelController(level, levelView);

        controllers.add(playerController);
        controllers.add(enemyController);
        controllers.add(steroidController);
        controllers.add(levelController);
        controllers.add(obstacleController);

        mapObjects.add(steroid);
        mapObjects.add(enemy);
        */

        loadLevel();
        //mapObjects.add(obstacle);

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
            player = new Player(new Weapon(), mapHandler.getPlayerStartPosition(), new Size(50, 50), 100);
            playerView = new PlayerView(player);
            level = new Level(mapHandler.getPhysicalObjectsList(), player, "level1");
            levelView = new LevelView(level, mapHandler);
            addPhysicalObjectViews(mapHandler.getPhysicalObjectsList());
            playerController = new PlayerController(player, playerView);
            controllers.add(playerController);

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
            }

        }
    }
}
