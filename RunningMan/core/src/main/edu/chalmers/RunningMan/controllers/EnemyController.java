package edu.chalmers.RunningMan.controllers;

import edu.chalmers.RunningMan.entities.Enemy;
import edu.chalmers.RunningMan.views.EnemyView;

/**
 * Created by Jesper on 4/25/2015.
 */
public class EnemyController implements IController {
    private Enemy enemy;
    private EnemyView enemyView;

    public EnemyController(Enemy enemy, EnemyView enemyView){
        this.enemy = enemy;
        this.enemyView = enemyView;
    }

    public void update(float deltaTime){
        enemy.move(deltaTime);
        enemyView.draw();
    }
}
