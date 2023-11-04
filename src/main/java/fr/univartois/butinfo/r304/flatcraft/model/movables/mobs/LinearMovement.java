package fr.univartois.butinfo.r304.flatcraft.model.movables.mobs;

public class LinearMovement implements IMobStrategy{

    Mob mob;

    @Override
    public double mobMovement(double current, double speed, long delta, Mob mob) {
        this.mob = mob;
        this.mob.setHorizontalSpeed(-3*16);
        return current + (delta * speed) / 1000;

    }
}
