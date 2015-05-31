package edu.chalmers.RunningMan.controller;


import edu.chalmers.RunningMan.model.gameobject.Enemy;
import edu.chalmers.RunningMan.view.EnemyView;
/**
 * A class for controlling enemies
 * @author Jesper Olsson
 */
public class EnemyController implements IEntityController {
    private final Enemy enemy;
    private final EnemyView enemyView;

    public EnemyController(Enemy enemy, EnemyView enemyView){
        this.enemy = enemy;
        this.enemyView = enemyView;
    }

    public void update(float deltaTime){
        enemy.move(deltaTime);
        if(enemy.getPosition().getX() < 1){
            enemy.changeDirection();
        }
    }
}
