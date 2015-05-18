package edu.chalmers.RunningMan.entities;

/**
 * Created by Jesper on 4/22/2015.
 */
public class Size implements ISize {
    private float width;
    private float height;

    public Size(float size) {
        this.width = size;
        this.height = size;
    }

    public Size(float width, float height){
        this.width = width;
        this.height = height;
    }

    public Size(Size size){
        if(size == null){
            throw new NullPointerException("Size constructor: parameter size null");
        }else{
            this.width = size.getWidth();
            this.height = size.getHeight();
        }
    }

    public float getWidth(){
        return this.width;
    }

    public float getHeight(){
        return this.height;
    }

    public float getArea(){
        return this.width * this.height;
    }

    public int hashCode(){
        return 17 * Float.floatToIntBits(getWidth()) * Float.floatToIntBits(getHeight());
    }

    public boolean equals(Object rhs){
        if(this == rhs){
            return true;
        }else if(rhs == null || rhs.getClass() != getClass()){
            return false;
        }else{
            Size tmp = (Size) rhs;
            return tmp.getWidth() == getWidth() && tmp.getHeight() == getHeight();
        }
    }
}
