package edu.chalmers.RunningMan.entities;
/**
 * @author Jesper Olsson
 */
public abstract class AbstractPhysicalObject implements IVisitable {

    private Size size;
    private Position position;

    public AbstractPhysicalObject(Size size, Position position){
        this.size = size;
        this.position = position;
    }

    public Size getSize(){
        return this.size;
    }

    public Position getPosition(){
        return this.position;
    }

    public void setX(float x){
        this.position.setX(x);
    }

    public void setY(float y){
        this.position.setY(y);
    }

    public void setPosition(Position position){
        if(position == null){
            throw new NullPointerException("AbstractPhysicalObject: setPosition: parameter position null");
        }else{
            this.position.setX(position.getX());
            this.position.setY(position.getY());
        }
    }
}
