package edu.chalmers.RunningMan.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import edu.chalmers.RunningMan.model.gameobject.Player;

import java.util.List;

/**
 * A view that draws all the other views in the level
 * @author Jesper Olsson
 */
public class LevelView extends Stage {
    private final List<Actor> views;
    private Batch batch;
    private final Player player;
    private OrthographicCamera camera;
    private final PlayerView playerView;
    private Texture background;

    public LevelView(List<Actor> views, Player player, BulletView bulletView, String levelName) {
        this.player = player;
        this.views = views;
        views.add(bulletView);
        playerView = new PlayerView(player, levelName);
        initCamera();
        try {
            background = new Texture("backgrounds/" + levelName + ".jpg");
        } catch (Exception e) {
            background = new Texture("backgrounds/level1.jpg");
            e.getStackTrace();
        }
    }

    private void initCamera() {
        camera = new OrthographicCamera();
        getViewport().setCamera(camera);
        camera.setToOrtho(false);
    }


    private void updateCamera() {
        if (player.getPosition().getX() > Gdx.graphics.getWidth() / 2) {
            final float lerp = 0.08f;
            Vector3 position = getCamera().position;
            position.x += (player.getPosition().getX() - position.x) * lerp;
            position.y = (Gdx.graphics.getHeight() / 2);
        }
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    public void draw() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        batch = getBatch();

        float backgroundXPosition = (camera.position.x - (Gdx.graphics.getWidth() / 2));
        float backgroundYPosition = (camera.position.y - (Gdx.graphics.getHeight() / 2));

        batch.begin();
        batch.draw(background, backgroundXPosition, backgroundYPosition);
        batch.end();

        playerView.draw(batch, deltaTime);
        updateCamera();
        for (Actor actor : views) {
            actor.draw(batch, deltaTime);
        }
    }

}
