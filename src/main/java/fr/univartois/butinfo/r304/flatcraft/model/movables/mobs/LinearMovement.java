package fr.univartois.butinfo.r304.flatcraft.model.movables.mobs;

public class LinearMovement implements IMobStrategy{

    Mob mob;

    @Override
    public double mobMovement(double current, double speed, long delta, Mob mob, int limitMin, int limitMax) {
        this.mob = mob;
        this.mob.setHorizontalSpeed(-3*16);
        double newPos = current + (delta * speed) / 1000;
        if (newPos > limitMax + 32){
            this.mob.setHorizontalSpeed(0);
            return limitMax + 32;
        }
        else if (newPos < limitMin - 32){
            this.mob.setHorizontalSpeed(0);
            return limitMin - 32;
        }
        return newPos;

    }
}
