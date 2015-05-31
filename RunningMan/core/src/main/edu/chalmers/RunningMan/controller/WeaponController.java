package edu.chalmers.RunningMan.controller;

import edu.chalmers.RunningMan.model.gameobject.Weapon;
import edu.chalmers.RunningMan.util.input.Input;

/**
 * A class for controlling the weapon
 */
public class WeaponController implements IEntityController {
    private final Weapon weapon;

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
