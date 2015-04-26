package edu.chalmers.RunningMan.entities;

/**
 * Created by Jesper on 4/22/2015.
 */
public interface IVisitor {
    public void visit(Enemy e);
    public void visit(Player p);
    public void visit(Obstacle g);
}
