package edu.chalmers.RunningMan.entities;
import static edu.chalmers.RunningMan.utils.B2DVariables.PPM;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import edu.chalmers.RunningMan.utils.Direction;

/**
 * A class to model a player
 */
public class Player implements ILivingEntity {

    private final int maxHp = 100;
    private int hp;
    private int killCount = 0;
    private int score = 0;
    private int nbrOfJumps = 0;
    private Weapon weapon;
    private Body body;
    private World world;
    private Direction direction;
    private boolean isDead;

    public Player(World w, Weapon weapon, Vector2 position) {
        this.world = w;
        this.hp = maxHp;
        this.weapon = weapon;
        this.isDead = false;
        init(position);
    }

    /**
     * Creating a body, shape and fixture for the player
     * @param pos the position where the player should be created
     */
    public void init(Vector2 pos){
        /* Creating the definition of the body*/
        BodyDef bDef = new BodyDef();
        bDef.type = BodyType.DynamicBody;
        bDef.position.set(pos.x/PPM, pos.y/PPM);
        body = world.createBody(bDef);

        /*Creating the structure of the body*/
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(100f/PPM, 100f/PPM);

        /*Creating the fixture of the body*/
        FixtureDef fDef = new FixtureDef();
        fDef.shape = ps;

        body.createFixture(fDef);
    }

    /**
     * @return the direction that the player is heading
     */
    public Direction getDirection(){
        return this.direction;
    }

    public Body getBody(){
        return this.body;
    }

    public void destroyBody(){
        world.destroyBody(body);
        body.setActive(false);
    }

    /**
     * Method to check if player is dead
     * @return true if hp = 0, false otherwise
     */
    public boolean isDead(){
        return this.isDead;
    }

    /**
     * Method to check if player is in the air
     * @return true if player is not in contact with any ground, false otherwise
     */
    public boolean isInAir(){
        return body.getLinearVelocity().y > 0.001;
    }


    public void incrementScore(int amount){
        this.score += amount;
    }

    /**
     * A method to be called when player kills an enemy
     */
    public void incrementKillCount(){
        this.killCount += 1;
    }

    /**
     * A method to be called whenever the player jumps
     * Increments the number of jumps
     */
    public void incrementNbrOfJumps(){
        this.nbrOfJumps += 1;
    }

    /**
     * A method to be called whenever the player gets contact with the ground
     * Resets the jump counter
     */
    public void setGroundContact(){
        this.nbrOfJumps = 0;
    }
    public void setDirection(Direction dir){
        this.direction = dir;
    }
    
    @Override
    public int getHp() {
        return this.hp;
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
        return this.maxHp;
    }

    @Override
    public void takeDamage(int hpDecreased) {
        setHp(hp - hpDecreased);
    }   
}