package edu.chalmers.RunningMan.entities;

/**
 * @author Jesper Olsson
 */
public interface IVisitor {
    public void visit(Enemy e);
    public void visit(Player p);
    public void visit(Obstacle g);
}
