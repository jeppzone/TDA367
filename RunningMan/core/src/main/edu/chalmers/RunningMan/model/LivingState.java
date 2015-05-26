package edu.chalmers.RunningMan.model;


public enum LivingState {

    FACING_RIGHT(1),
    FACING_LEFT(-1),
    MOVING_RIGHT(1),
    MOVING_LEFT(-1),
    JUMPING_RIGHT(1),
    JUMPING_LEFT(-1),
    STANDING(0);


    public final int xDirection; // -1 moving backwords, 1 moving forward

    LivingState(final int xDirection){
        this.xDirection = xDirection;
    }

}
