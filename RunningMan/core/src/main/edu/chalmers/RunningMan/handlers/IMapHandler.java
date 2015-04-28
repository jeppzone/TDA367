package edu.chalmers.RunningMan.handlers;

import edu.chalmers.RunningMan.entities.Position;

/**
 * Created by JohanTobin on 2015-04-22.
 */
public interface IMapHandler {

    Position getStartPosition();

    void loadLevel(String levelName);

}
