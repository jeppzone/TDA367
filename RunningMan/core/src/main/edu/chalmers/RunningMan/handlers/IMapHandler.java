package edu.chalmers.RunningMan.handlers;

import edu.chalmers.RunningMan.entities.AbstractPhysicalObject;
import edu.chalmers.RunningMan.entities.Position;

import java.util.List;

/**
 * A interface for map handlers
 */
public interface IMapHandler {

    public List<AbstractPhysicalObject> getPhysicalObjectsList();

    public Position getPlayerStartPosition();

    public void loadLevel(String levelName) throws MapHandlerException;

}
