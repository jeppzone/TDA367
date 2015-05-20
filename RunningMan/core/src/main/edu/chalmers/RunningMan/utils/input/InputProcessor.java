package edu.chalmers.RunningMan.utils.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

/**
 * A class to handle the input from the keyboard
 */
public class InputProcessor extends InputAdapter {

    public boolean keyDown(int key) {
        if(key == Keys.LEFT) {
            Input.setKey(Input.LEFTBTN, true);
        }
        if(key == Keys.RIGHT) {
            Input.setKey(Input.RIGHTBTN, true);
        }
        if(key == Keys.UP) {
            Input.setKey(Input.JUMPBTN, true);
        }
        if(key == Keys.X) {
            Input.setKey(Input.SHOOTBTN, true);
        }
        return true;
    }

    public boolean keyUp(int key) {
        if(key == Keys.LEFT) {
            Input.setKey(Input.LEFTBTN, false);
        }
        if(key == Keys.RIGHT) {
            Input.setKey(Input.RIGHTBTN, false);
        }
        if(key == Keys.UP) {
            Input.setKey(Input.JUMPBTN, false);
        }
        if(key == Keys.X) {
            Input.setKey(Input.SHOOTBTN, false);
        }
        return true;
    }
}
