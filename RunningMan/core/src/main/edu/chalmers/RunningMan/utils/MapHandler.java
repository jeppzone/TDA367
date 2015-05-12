package edu.chalmers.RunningMan.utils;

import com.badlogic.gdx.maps.tiled.TiledMap;

import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import edu.chalmers.RunningMan.entities.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A class which handles the map.
 */
public class MapHandler implements IMapHandler {

    private TiledMap tileMap;
    private TiledMapRenderer tiledMapRenderer;

    private final static String MAP_LOCATION = "core/assets/maps/";

    private Position playerStartPosition;
    private List<AbstractPhysicalObject> physicalObjects;
    private int tilePixelSize, mapWidth, mapHeight;

    public MapHandler(String levelName)throws MapHandlerException {
        try{
            loadLevel(levelName);
        } catch (Exception e){
            throw new MapHandlerException(e);
        }
    }

    /**
     * Loads the level
     * @param levelName The name of the level
     * @throws MapHandlerException if level file doesn't exist
     */
    public final void loadLevel(String levelName) throws MapHandlerException {
        File file = new File(MAP_LOCATION + levelName + ".tmx");
        if(!file.exists()) throw new MapHandlerException();
        tileMap = new TmxMapLoader().load("core/assets/maps/" + levelName + ".tmx");
        tilePixelSize = (Integer) tileMap.getProperties().get("tilewidth");
        mapHeight = (Integer) tileMap.getProperties().get("height");
        mapWidth = (Integer) tileMap.getProperties().get("width");
        physicalObjects = new ArrayList<AbstractPhysicalObject>();
        createObjectList();

    }

    /**
     * @return The starting position of the player
     */
    public Position getPlayerStartPosition() {
        return playerStartPosition;
    }

    /**
     * @return A list of all physical objects in the map
     */
    public List<AbstractPhysicalObject> getPhysicalObjectsList() {
        return physicalObjects;
    }

    /**
     * Creates a list of all the map objects
     */
    private void createObjectList(){

        // retrieve and store each type of objects from the map in respective layer
        TiledMapTileLayer groundLayer = (TiledMapTileLayer) tileMap.getLayers().get("ground");
        TiledMapTileLayer enemiesLayer = (TiledMapTileLayer) tileMap.getLayers().get("enemies");
        TiledMapTileLayer steroidsLayer = (TiledMapTileLayer) tileMap.getLayers().get("steroids");
        TiledMapTileLayer startPositionLayer = (TiledMapTileLayer) tileMap.getLayers().get("startPosition");
        TiledMapTileLayer pitLayer = (TiledMapTileLayer) tileMap.getLayers().get("pitfalls");
        TiledMapTileLayer finishLayer = (TiledMapTileLayer) tileMap.getLayers().get("finishlevel");

        // go through all the cells in all the layers (all map cells)
        for(int row = 0; row < mapHeight; row++) {
            for(int col = 0; col < mapWidth; col++) {
                Position position = new Position((col + 0.5f) * tilePixelSize, (row + 0.5f) * tilePixelSize);
                Size size = new Size(tilePixelSize);

                // get cell
                TiledMapTileLayer.Cell groundCell = groundLayer.getCell(col, row);
                TiledMapTileLayer.Cell enemyCell = enemiesLayer.getCell(col, row);
                TiledMapTileLayer.Cell steroidCell = steroidsLayer.getCell(col, row);
                TiledMapTileLayer.Cell startPositionCell = startPositionLayer.getCell(col, row);
                TiledMapTileLayer.Cell pitCell = pitLayer.getCell(col, row);
                TiledMapTileLayer.Cell finishCell = finishLayer.getCell(col, row);

                // check if cell exists
                if(pitCell != null && pitCell.getTile() != null) {
                    physicalObjects.add(new Pit(position, size));
                } else if(groundCell != null && groundCell.getTile() != null) {
                    physicalObjects.add(new Ground(position, size));
                } else if(finishCell != null && finishCell.getTile() != null){
                    physicalObjects.add(new FinishObject(position,new Size(95,62)));
                } else if(enemyCell != null && enemyCell.getTile() != null) {
                    physicalObjects.add(new Enemy(position, new Size(45, 55), 100));
                } else if(steroidCell != null && steroidCell.getTile() != null) {
                    physicalObjects.add(new Steroid(position, size));
                } else if(startPositionCell != null && startPositionCell.getTile() != null) {
                    playerStartPosition = position;
                    playerStartPosition.setY(position.getY() + 10);
                }

            }
        }
    }

    /**
     * Render the map
     */
    public void renderMap(){

        tiledMapRenderer = new OrthoCachedTiledMapRenderer(tileMap);
        tiledMapRenderer.render();

    }
}