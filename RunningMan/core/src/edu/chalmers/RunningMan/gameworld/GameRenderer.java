package edu.chalmers.RunningMan.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import static edu.chalmers.RunningMan.utils.Constants.V_WIDTH;
import static edu.chalmers.RunningMan.utils.Constants.V_HEIGHT;

/**
 * Created by JohanTobin on 2015-04-22.
 */
public class GameRenderer {

    private GameWorld gameWorld;
    private OrthographicCamera cam;

    public GameRenderer(GameWorld gameWorld){
        this.gameWorld = gameWorld;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
    }

    public void render() {

        Gdx.app.log("GameRenderer", "render");

    }
}
