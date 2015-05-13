package edu.chalmers.RunningMan.entities;

/**
 * Created by Jesper on 5/7/2015.
 */
public class Helicopter extends AbstractPhysicalObject {

    private boolean flyaway;

    public Helicopter(Position position, Size size){
        super(size, position);
    }
    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor. visit(this);
        flyaway = true;
    }
    public void move(float delta){
        float velocity = 200;
        setX(getPosition().getX() + velocity*delta);
    }

    public boolean getFlyaway(){
        return flyaway;
    }
}
