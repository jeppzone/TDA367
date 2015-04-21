package edu.chalmers.RunningMan.handlers;

import edu.chalmers.RunningMan.RunningMan;
import edu.chalmers.RunningMan.states.GameState;
import edu.chalmers.RunningMan.states.Play;

import java.util.Stack;

/**
 * Created by Kvist1 on 2015-04-21.
 */
public class GameStateManager {

    private RunningMan game;

    private Stack<GameState> gameStates;

    // state ID
    public static final int PLAY = 987654;

    public GameStateManager(RunningMan game) {
        this.game = game;
        gameStates = new Stack<GameState>();
        pushState(PLAY);
    }

    public RunningMan game() {
        return game;
    }

    // update current state
    public void update(float deltaTime) {
        gameStates.peek().update(deltaTime);
    }

    // render current state
    public void render() {
        gameStates.peek().render();
    }

    // helper method to make a new GameState
    private GameState getState(int state) {
        if(state == PLAY) return new Play(this);
        return null;
    }

    // set current state
    public void setState(int state) {
        popState();
        pushState(state);
    }

    // push new state on the stack
    public void pushState(int state) {
        gameStates.push(getState(state));
    }

    // remove state from the stack
    public void popState() {
        GameState gs = gameStates.pop();
        gs.dispose();
    }
}
