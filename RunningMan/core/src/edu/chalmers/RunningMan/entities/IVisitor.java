package edu.chalmers.RunningMan.entities;

/**
 * @author Jesper Olsson
 */
public interface IVisitor {
    void visit(Enemy e);
    void visit(Player p);
    void visit(Obstacle g);
    void visit(Bullet b);
    void visit(Steroid s);
}
