package edu.chalmers.RunningMan.entities;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Player model
 */
public class Player {

    private Body body;
    private int hp;
    private Weapon weapon;

    public Player(Body body, int hp, Weapon weapon) {
        this.body = body;
        this.hp = hp;
        this.weapon = weapon;
    }

    public void move() {}
    public void jump() {}
    public void shoot() {}

    public int getHp(){return hp;}
    public void setHp(int hp) {}
}
