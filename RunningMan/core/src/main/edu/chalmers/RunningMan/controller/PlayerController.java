package edu.chalmers.RunningMan.controller;

import edu.chalmers.RunningMan.model.gameobject.Player;
import edu.chalmers.RunningMan.util.input.Input;
import edu.chalmers.RunningMan.view.PlayerView;


/**
 * A class for controlling player
 */
public class PlayerController implements IEntityController {

    private Player player;

    public PlayerController(Player player){
        this.player = player;
    }

    public void handleKeyboardInput(float deltaTime){
        if(player.hasLandedFirstTime()) {
            if (Input.isPressed(Input.JUMPBTN) && Input.isPressed(Input.RIGHTBTN)) {
                player.jump(deltaTime);
                player.moveRight(deltaTime);
            } else if (Input.isPressed(Input.JUMPBTN) && Input.isPressed(Input.LEFTBTN)) {
                player.jump(deltaTime);
                player.moveLeft(deltaTime);
            } else if (Input.isPressed(Input.JUMPBTN)) {
                player.jump(deltaTime);
            } else if (Input.isPressed(Input.RIGHTBTN)) {
                player.moveRight(deltaTime);
            } else if (Input.isPressed(Input.LEFTBTN)) {
                player.moveLeft(deltaTime);
            }
        }

        player.applyForce(deltaTime);
    }


    public void update(float deltaTime) {
        handleKeyboardInput(deltaTime);
        player.update(deltaTime);
    }

    public void reset(){
        Input.resetKeys();
    }
}
