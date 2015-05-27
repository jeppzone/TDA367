package edu.chalmers.RunningMan.controllers;

import edu.chalmers.RunningMan.model.gameobjects.Weapon;
import edu.chalmers.RunningMan.utils.input.Input;

/**
 * Created by Armand on 2015-05-14.
 */
public class WeaponController implements IEntityController {
    private Weapon weapon;

    public WeaponController(Weapon weapon){
        this.weapon = weapon;
    }

    @Override
    public void update(float deltaTime) {
        weapon.update(deltaTime);
        if(Input.isPressed(Input.SHOOTBTN)) {
            weapon.shoot();
        }

    }
}
