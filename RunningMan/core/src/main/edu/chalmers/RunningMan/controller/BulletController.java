package edu.chalmers.RunningMan.controller;

import edu.chalmers.RunningMan.model.gameobject.Bullet;
import edu.chalmers.RunningMan.view.BulletView;

import java.util.List;

/**
 * A class for controlling the bullet
 */
public class BulletController implements IEntityController {

    private BulletView bulletView;
    List<Bullet> bullets;

    public BulletController(List<Bullet> bullets, BulletView bulletView){
        this.bullets = bullets;
        this.bulletView = bulletView;
    }

    @Override
    public void update(float deltaTime) {
        for(Bullet bullet: bullets){
            bullet.moveBullet(deltaTime);
        }

    }
}
