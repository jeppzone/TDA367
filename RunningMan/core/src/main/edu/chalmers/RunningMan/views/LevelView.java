package edu.chalmers.RunningMan.views;

import edu.chalmers.RunningMan.entities.Level;
import edu.chalmers.RunningMan.handlers.MapHandler;

/**
 * Class to represent the view of a level
 */
public class LevelView {
    private Level level;
    private MapHandler mapHandler;

    public LevelView(Level level, MapHandler mapHandler){
        this.level = level;
        this.mapHandler = mapHandler;
    }

    public void draw(){
        mapHandler.renderMap();
    }
}
