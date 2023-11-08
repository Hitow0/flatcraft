package fr.univartois.butinfo.r304.flatcraft.model.movables.mobs;

public interface IMobStrategy {

    public double mobMovement(double current, double speed, long delta, Mob mob, int limitMin, int limitMax);

}
