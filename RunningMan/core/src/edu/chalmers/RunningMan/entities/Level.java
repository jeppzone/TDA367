package edu.chalmers.RunningMan.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import edu.chalmers.RunningMan.RunningMan;
import edu.chalmers.RunningMan.utils.B2DVariables;

import static edu.chalmers.RunningMan.utils.B2DVariables.PPM;

/**
 * Created by JohanTobin on 2015-04-20.
 */
public class Level {
    private Player player;
    //private Enemy enemies;
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private String levelName;
    //private NamedTiledMap namedTiledMap;
    private float groundTileHeight;
    private float groundTileWidth;
    private BodyDef bdef;
    private FixtureDef fdef;
    private PolygonShape shape;
    private World world;
    private Box2DDebugRenderer b2dr;
    private Body playerBody;

    private OrthographicCamera cam;

    public void createMap(String levelName){

        bdef = new BodyDef();
        fdef = new FixtureDef();
        shape = new PolygonShape();

        tempPlat();
        createPlayer();
        setup2dCam();

        /* For using tiled
        //load the map
        tiledMap = new TmxMapLoader().load("core/assets/"+levelName);
        tiledMapRenderer = new OrthoCachedTiledMapRenderer(tiledMap);
        TiledMapTileLayer groundLayer = (TiledMapTileLayer) tiledMap.getLayers().get("ground");

        //Get the width and height of the ground tiles
        groundTileHeight = groundLayer.getTileHeight();
        groundTileWidth = groundLayer.getTileWidth();

        for(int row = 0; row < groundLayer.getHeight(); row++){
            for(int col = 0; col < groundLayer.getWidth(); col++){

                Cell cell = groundLayer.getCell(col, row);

                if(cell == null)
                    continue;
                if(cell == null)
                    continue;

                bdef = new BodyDef();
                bdef.type = BodyType.StaticBody;
                bdef.position.set((col + 0.5f) * groundTileHeight / PPM, (row + 0.5f) * groundTileWidth / PPM);

                ChainShape cs = new ChainShape();

                Vector2[] v = new Vector2[3];
                v[0] = new Vector2(
                        -groundTileHeight / 2 / PPM, -groundTileHeight / 2 / PPM);
                v[1] = new Vector2(
                        -groundTileHeight / 2 / PPM, groundTileHeight / 2 / PPM);
                v[2] = new Vector2(
                        groundTileHeight / 2 / PPM, groundTileWidth / 2 / PPM);
                cs.createChain(v);
                fdef.friction = 0;
                fdef.shape = cs;
                fdef.filter.categoryBits = B2DVariables.GROUND;
				fdef.filter.maskBits = B2DVariables.PLAYER;
                fdef.isSensor = false;
                world.createBody(bdef).createFixture(fdef);
            }
        }
        */

        //Gets the name of the level
        this.levelName = levelName;
    }

    private void tempPlat(){
        //Temp platform
        bdef.position.set(160 / PPM, 120 / PPM);
        bdef.type = BodyType.StaticBody;
        Body body = world.createBody(bdef);

        shape.setAsBox(50 / PPM, 5 / PPM);
        fdef.shape = shape;
        fdef.filter.categoryBits = B2DVariables.GROUND;
        fdef.filter.maskBits = B2DVariables.PLAYER;
        body.createFixture(fdef).setUserData("ground");
    }
    
    private void createPlayer(){
        // create player
        bdef.position.set(160 / PPM, 200 / PPM);
        bdef.type = BodyType.DynamicBody;
        playerBody = world.createBody(bdef);

        shape.setAsBox(5 / PPM, 5 / PPM);
        fdef.shape = shape;
        fdef.filter.categoryBits = B2DVariables.PLAYER;
        fdef.filter.maskBits = B2DVariables.GROUND;
        playerBody.createFixture(fdef).setUserData("player");

        //Create foot sensor
        shape.setAsBox(2 / PPM, 2 / PPM, new Vector2(0, -5 / PPM), 0);
        fdef.shape = shape;
        fdef.filter.categoryBits = B2DVariables.PLAYER;
        fdef.filter.maskBits = B2DVariables.GROUND;
        fdef.isSensor = true;
        playerBody.createFixture(fdef).setUserData("foot");
    }

    private void setup2dCam(){
        // set up box2d cam
        cam = new OrthographicCamera();
        // Waiting on V_WIDTH
        cam.setToOrtho(false, RunningMan.V_WIDTH / PPM, RunningMan.V_HEIGHT / PPM);
    }

    public void cameraView(){
        //Gets the cameraposition
//      cam.position.set(player.getPosition().x * PPM + Game.V_WIDTH / 4, Game.V_HEIGHT / 2);
    }

    public void levelRender(){
        //renders the map and makes the camera follow the player
        tiledMapRenderer.setView(cam);
        tiledMapRenderer.render();
    }

    public void dispose(){

    }

}
