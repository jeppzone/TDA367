package edu.chalmers.RunningMan.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 * Created by Armand on 2015-04-20.
 */
public class Enemy implements ILivingEntity {

    private int maxHp;
    private int hp;
    private boolean isDead;
    private int speed;
    private boolean isFlinching;
    private long flinchTime;
    BodyDef bdef;
    Body body;
    FixtureDef fdef;


    public Enemy(int hp ){
        setHp(hp);
        bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.KinematicBody;
        body = world.createBody(bdef); // vi måste skapa en world i Level World world se
        fdef = new FixtureDef();
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public int getMaxHp() {
        return this.maxHp;
    }
    @Override
    public void setHp(int newHp) {
        if (newHp <= 0) {
            hp = 0;
            isDead = true;
        } else {
            hp = newHp;
        }
    }

    @Override
    public void takeDamage(int hpDecreased) {
        hp = hp - hpDecreased;
        setHp(hp);

    }




}
