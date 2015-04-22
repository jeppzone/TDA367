package edu.chalmers.RunningMan.controllers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import edu.chalmers.RunningMan.entities.Player;

/**
 * Created by JohanTobin on 2015-04-20.
 */
public class PlayerController  {
    private Player player;
    public PlayerController(Player player){
        this.player = player;
    }
    public void handleKeyboardInput(Input input, int deltaTime){
        if(input.isKeyPressed(Input.Keys.LEFT)){
            player.moveLeft(deltaTime);
        }else if(input.isKeyPressed(Input.Keys.RIGHT)){
            player.moveRight(deltaTime);
        }else if(input.isKeyPressed(Input.Keys.SPACE)){
            player.jump(deltaTime);
        }
        player.applyForce(deltaTime);
    }

}
