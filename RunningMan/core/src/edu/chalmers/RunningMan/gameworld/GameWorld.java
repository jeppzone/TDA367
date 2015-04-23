package edu.chalmers.RunningMan.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import edu.chalmers.RunningMan.controllers.PlayerController;
import edu.chalmers.RunningMan.entities.Player;
import edu.chalmers.RunningMan.entities.Position;
import edu.chalmers.RunningMan.entities.Size;
import edu.chalmers.RunningMan.entities.Weapon;
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

    public GameWorld() {
        cam = new OrthographicCamera();
        cam.setToOrtho(false, V_WIDTH, V_HEIGHT);

        player = new Player(new Weapon(), new Position(0,0), new Size(50,50), 100);
        playerView = new PlayerView(player);
        playerController = new PlayerController(player, playerView);

    }

    public void update(float deltaTime) {

        //Gdx.app.log("GameWorld", "update");

        playerController.update(deltaTime);

    }
}
