package edu.chalmers.RunningMan.entities;

/**
 * A class to represent a Position
 * @author Jesper Olsson
 */
public class Position {
    private float x;
    private float y;

    public Position(float x, float y){
        setX(x);
        setY(y);
    }

    public Position(Position position){
        if(position == null){
            throw new NullPointerException("Position constructor: parameter position null");
        }else{
            setX(position.getX());
            setY(position.getY());
        }
    }

    public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y;
    }

    public void setX(float x){
        if(x < 0){
            this.x = 0f;
        }else {
            this.x = x;
        }
    }

    public void setY(float y){
        if(y < 0){
            this.y = 0f;
        }else {
            this.y = y;
        }
    }

    //TODO HashCode & Equals?
}
