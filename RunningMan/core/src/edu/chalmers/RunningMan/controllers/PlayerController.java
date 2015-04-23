package edu.chalmers.RunningMan.controllers;

import com.badlogic.gdx.InputAdapter;
import edu.chalmers.RunningMan.entities.Player;
import edu.chalmers.RunningMan.handlers.Input;
import edu.chalmers.RunningMan.views.PlayerView;

/**
 * Created by JohanTobin on 2015-04-20.
 */
public class PlayerController  {

    private Player player;
    private PlayerView playerView;

    public PlayerController(Player player, PlayerView playerView){
        this.player = player;
        this.playerView = playerView;
    }

    public void handleKeyboardInput(float deltaTime){


        // player jump
        if(Input.isPressed(Input.JUMPBTN)) {
            System.out.println("jump");
            player.jump(deltaTime);
        } else if(Input.isDown(Input.RIGHTBTN)) {
            System.out.println("go right");
            player.moveRight(deltaTime);
        } else if(Input.isDown(Input.LEFTBTN)) {
            System.out.println("go left");
            player.moveLeft(deltaTime);
        } else if(Input.isPressed(Input.SHOOTBTN)) {
            // player.shoot(deltaTime);
        }
        player.applyForce(deltaTime);

    }

    public void update(float deltaTime) {

        handleKeyboardInput(deltaTime);

        // playerView.render();
    }
}
