package edu.chalmers.RunningMan.util.map;

import edu.chalmers.RunningMan.model.gameobject.AbstractPhysicalObject;
import edu.chalmers.RunningMan.model.Position;

import java.util.List;

/**
 * A interface for map handlers
 */
public interface IMapHandler {

    List<AbstractPhysicalObject> getPhysicalObjectsList();

    Position getPlayerStartPosition();

    void loadLevel(String levelName) throws MapHandlerException;

}
