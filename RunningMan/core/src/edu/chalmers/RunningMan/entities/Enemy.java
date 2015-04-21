package edu.chalmers.RunningMan.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import static edu.chalmers.RunningMan.utils.B2DVariables.PPM;
import edu.chalmers.RunningMan.utils.Direction;
/**
 * A class to model an Enemy
 */
public class Enemy implements ILivingEntity {

    private int maxHp = 20;
    private int hp;
    private boolean isDead;
    private boolean isFlinching;
    private long flinchTime; // = 20?
    private BodyDef bDef;
    private Body body;
    private FixtureDef fDef;
    private World world;
    private Direction direction;


    public Enemy(World w, Vector2 position){
        world = w;
        isDead = false;
        isFlinching = false;
        hp = maxHp;
        isDead = false;
        init(position);

    }

    /**
     * Creating a body, shape and fixture for the player
     * @param pos the position where the Enemy should be created
     */
    public void init(Vector2 pos){

        /* Creating body definition */
        bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.KinematicBody;
        body = world.createBody(bDef); // vi måste skapa en world i Level World world se
        fDef = new FixtureDef();
        bDef.position.set(pos.x/PPM, pos.y/PPM);

        /*Creating body structure*/
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(100f/PPM,100f/PPM);

        /*Creating the fixture of the body*/

        FixtureDef fDef = new FixtureDef();
        fDef.shape = ps;

        body.createFixture(fDef);

    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public int getMaxHp() {
        return this.maxHp;
    }

    public Direction getDirection(){
        return direction;
    }
    public Body getBody(){
        return body;
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

    public void setDirection(Direction dir){
        direction = dir;
    }

    public void destroyBody(){
        world.destroyBody(body);
        body.setActive(false);
    }

    public boolean isDead(){
        return isDead;
    }

    @Override
    public void takeDamage(int hpDecreased) {
        setHp( hp - hpDecreased);

    }
}
