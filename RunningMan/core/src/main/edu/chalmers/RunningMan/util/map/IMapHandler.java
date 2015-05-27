package edu.chalmers.RunningMan.util.map;

import edu.chalmers.RunningMan.model.gameobjects.AbstractPhysicalObject;
import edu.chalmers.RunningMan.model.Position;

import java.util.List;

/**
 * A interface for map handlers
 */
public interface IMapHandler {

    public List<AbstractPhysicalObject> getPhysicalObjectsList();

    public Position getPlayerStartPosition();

    public void loadLevel(String levelName) throws MapHandlerException;

}
