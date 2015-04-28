package edu.chalmers.RunningMan.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;

import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import edu.chalmers.RunningMan.entities.AbstractLivingObject;
import edu.chalmers.RunningMan.entities.AbstractPhysicalObject;
import edu.chalmers.RunningMan.entities.Position;

import java.util.List;

/**
 * Created by JohanTobin on 2015-04-22.
 */
public class MapHandler implements IMapHandler {

    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private final static String MAP_LOCATION = "core/assets/levels";
    private Position startPosition;
    private List<AbstractPhysicalObject> physicalObjects;
    private List<AbstractLivingObject> livingObjects;

    public MapHandler(String levelName)throws MapHandlerException {
        try{

            loadLevel(levelName);

        } catch (Exception e){

            throw new MapHandlerException(e);

        }
    }

    //Loads the Level
    public final void loadLevel(String levelName) {
        try {

            tiledMap = new TmxMapLoader().load("core/assets/levels/" + levelName + ".tmx");
        } catch (Exception e) {

            System.out.println("Cannot find file: core/assets/levels/" + levelName + ".tmx");
            Gdx.app.exit();

        }
    }



    //Gets the startposition of the player
    public Position getStartPosition() {

        return startPosition;
    }

    public void createObjectList(){

        MapObjects badassenemy = tiledMap.getLayers().get("badassenemy").getObjects();

        /*
        for(int i = 0; ){
            livingObjects.add(badassenemy.)
        }
        */

    }

    public void createLayerList(){

        TiledMapTileLayer waterLayer = (TiledMapTileLayer) tiledMap.getLayers().get("water");
        waterLayer.getWidth();
        

        //TiledMapTileLayer.Cell cell = waterLayer.getCell(col,row);

        final int layerAmount = tiledMap.getLayers().getCount();


        MapLayer groundLayer = tiledMap.getLayers().get("Ground");

        Array<MapObject> objects = new Array<MapObject>();
        for (MapObject obj : groundLayer.getObjects()) {
            if ("Ground".equals(obj.getProperties().get("type", String.class)) ) {
                //physicalObjects.add(obj);
            }
        }


    }

    public void renderMap(){

        tiledMapRenderer = new OrthoCachedTiledMapRenderer(tiledMap);
        tiledMapRenderer.render();

    }
}
