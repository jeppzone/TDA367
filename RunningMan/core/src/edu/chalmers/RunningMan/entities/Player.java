package edu.chalmers.RunningMan.entities;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Player model
 */
public class Player implements ILivingEntity {

    private final int maxHp = 100;
    private Body body;
    private int hp;
    private int killCount = 0;
    private int score = 0;
    private String playerName;
    private Weapon weapon;
    private boolean isDead;

    public Player(int hp, Weapon weapon) {

    }


    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int newHp) {
        if (newHp <= 0) {
            hp = 0;
            isDead = true;
        } else if (newHp > getMaxHp()) {
            hp = getMaxHp();
        } else {
            hp = newHp;
        }
    }

    @Override
    public int getMaxHp() {
        return maxHp;
    }

    @Override
    public void takeDamage(int hpDecreased) {
        setHp(hp - hpDecreased);
    }
}W