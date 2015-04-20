package edu.chalmers.RunningMan.entities;

/**
 * Player model
 */
public class Player {

    private int hp;
    private Weapon weapon;

    public Player(int hp, Weapon weapon) {
        this.hp = hp;
        this.weapon = weapon;
    }

    public void move() {}
    public void jump() {}
    public void shoot() {}
}
