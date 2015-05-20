package edu.chalmers.RunningMan.controllers;

import edu.chalmers.RunningMan.model.level.mapobjects.livingentities.objects.Player;
import edu.chalmers.RunningMan.utils.input.Input;
import edu.chalmers.RunningMan.views.PlayerView;


/**
 * A class for controlling player
 */
public class PlayerController implements IEntityController {

    private Player player;
    private PlayerView playerView;

    public PlayerController(Player player, PlayerView playerView){
        this.player = player;
        this.playerView = playerView;
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
        //playerView.draw();

    }
}
