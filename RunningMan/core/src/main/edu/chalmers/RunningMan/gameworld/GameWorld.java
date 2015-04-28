package edu.chalmers.RunningMan.gameworld;

import com.badlogic.gdx.graphics.OrthographicCamera;
import edu.chalmers.RunningMan.controllers.EnemyController;
import edu.chalmers.RunningMan.controllers.LevelController;
import edu.chalmers.RunningMan.controllers.PlayerController;
import edu.chalmers.RunningMan.entities.*;
import edu.chalmers.RunningMan.views.EnemyView;
import edu.chalmers.RunningMan.views.LevelView;
import edu.chalmers.RunningMan.views.PlayerView;
import edu.chalmers.RunningMan.views.SteroidView;

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
    private Level level;
    private LevelView levelView;
    private LevelController levelController;
    private Steroid steroid;
    private SteroidView steroidView;

    public GameWorld() {
        cam = new OrthographicCamera();
        cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
        mapObjects = new ArrayList<AbstractPhysicalObject>();

        player = new Player(new Weapon(), new Position(0,0), new Size(50,50), 100);
        playerView = new PlayerView(player);
        playerController = new PlayerController(player, playerView);

        enemy = new Enemy(new Position(300,0), new Size(30,30), 10);
        enemyView = new EnemyView(enemy);
        enemyController = new EnemyController(enemy, enemyView);

        steroid = new Steroid(new Position(100, 0), new Size(50, 50));
        steroidView = new SteroidView(steroid);

        mapObjects.add(steroid);
        mapObjects.add(enemy);

        level = new Level(mapObjects, player, "Beach");
        levelView = new LevelView(level);
        levelController = new LevelController(level, levelView);

    }

    public void update(float deltaTime) {

        //Gdx.app.log("GameWorld", "update");
        levelController.update();
        steroidView.draw();
        playerController.update(deltaTime);
        enemyController.update(deltaTime);
    }
}
