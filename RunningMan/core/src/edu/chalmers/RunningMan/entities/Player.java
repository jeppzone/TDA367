package edu.chalmers.RunningMan.entities;

/**
 * A class to model a player
 */
public class Player extends AbstractLivingObject {

    private int killCount = 0;
    private int score = 0;
    private Weapon weapon;
    private int velocityX;
    private int velocityY;


    public Player(Weapon weapon, Position position, Size size, int maxHp) {
        super(size, position, maxHp);
        this.weapon = weapon;
    }

    public int getScore(){
        return score;
    }

    /**
     * Method to check if player is in the air
     * @return true if player is not in contact with any ground, false otherwise
     */

    public void incrementScore(int amount){
        this.score += amount;
    }

    /**
     * A method to be called when player kills an enemy
     */
    public void incrementKillCount() {
        this.killCount += 1;
    }


    public void acceptVisitor(IVisitor visitor) {

    }
}