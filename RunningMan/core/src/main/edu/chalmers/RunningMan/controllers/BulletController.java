package edu.chalmers.RunningMan.controllers;

import edu.chalmers.RunningMan.entities.Bullet;
import edu.chalmers.RunningMan.views.BulletView;

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
            bulletView.draw();
        }

    }
}
