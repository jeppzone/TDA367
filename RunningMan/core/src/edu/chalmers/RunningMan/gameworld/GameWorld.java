package edu.chalmers.RunningMan.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import edu.chalmers.RunningMan.controllers.EnemyController;
import edu.chalmers.RunningMan.controllers.PlayerController;
import edu.chalmers.RunningMan.entities.*;
import edu.chalmers.RunningMan.views.EnemyView;
import edu.chalmers.RunningMan.views.PlayerView;

import static edu.chalmers.RunningMan.utils.Constants.V_HEIGHT;
import static edu.chalmers.RunningMan.utils.Constants.V_WIDTH;

/**
 * Created by JohanTobin on 2015-04-22.
 */
public class GameWorld {

    private OrthographicCamera cam;

    private PlayerController playerController;
    private Player player;
    private PlayerView playerView;
    private Enemy enemy;
    private EnemyView enemyView;
    private EnemyController enemyController;

    public GameWorld() {
        cam = new OrthographicCamera();
        cam.setToOrtho(false, V_WIDTH, V_HEIGHT);

        player = new Player(new Weapon(), new Position(0,0), new Size(100,100), 100);
        playerView = new PlayerView(player);
        playerController = new PlayerController(player, playerView);

        enemy = new Enemy(new Position(300,0), new Size(30,30), 10);
        enemyView = new EnemyView(enemy);
        enemyController = new EnemyController(enemy, enemyView);

    }

    public void update(float deltaTime) {

        //Gdx.app.log("GameWorld", "update");

        playerController.update(deltaTime);
        enemyController.update(deltaTime);
    }
}
