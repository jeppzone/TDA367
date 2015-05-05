package edu.chalmers.RunningMan.controllers;

import edu.chalmers.RunningMan.entities.Bullet;

import java.util.List;

/**
 * Created by Armand on 2015-05-02.
 */
public class BulletController implements IEntityController {

    List<Bullet> bullets;

    public BulletController(List<Bullet> bullets){
        this.bullets = bullets;
    }

    @Override
    public void update(float deltaTime) {
        for(Bullet bullet: bullets){
            bullet.moveBullet(deltaTime);
        }

    }
}
