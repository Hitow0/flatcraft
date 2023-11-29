package fr.univartois.butinfo.r304.flatcraft.model.movables.mobs;

import java.util.Random;

public class RandomMovement implements IMobStrategy{


    @Override
    public double mobMovement(double current, double speed, long delta, Mob mob, int limitMin, int limitMax) {
        Random random = new Random();
        int proba = random.nextInt(100);
        if (proba < 5){
            double newPos = current;
            mob.setHorizontalSpeed(16);
            for(int i=0; i < 16; i++) {
                newPos = newPos + (delta * speed) / 1000;
            }
            if (newPos > limitMax){
                return limitMax;
            }
            return newPos;
        }
        else if (proba < 10){
            double newPos = current;
            mob.setHorizontalSpeed(-16);
            for(int i=0; i < 16; i++){
                newPos = newPos + (delta * speed) / 1000;
            }
            if (newPos < limitMin){
                return limitMin;
            }
            return newPos;
        } else {
            mob.setHorizontalSpeed(0);
            return current;
        }
    }
}
