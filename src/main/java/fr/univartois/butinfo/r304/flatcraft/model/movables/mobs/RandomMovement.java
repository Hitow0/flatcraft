package fr.univartois.butinfo.r304.flatcraft.model.movables.mobs;

import java.util.Random;

public class RandomMovement implements IMobStrategy{


    @Override
    public double mobMovement(double current, double speed, long delta, Mob mob, int limitMin, int limitMax) {
        mob.setHorizontalSpeed(0);
        Random random = new Random();
        int proba = random.nextInt(10);
        if (proba < 2){
            mob.setHorizontalSpeed(2*16);
            double newPos = current;
            for (int i = 0; i <5; i++) {
                newPos = newPos + (delta * speed) / 1000;
                System.out.println(newPos);
            }
            if (newPos > limitMax){
                mob.setHorizontalSpeed(0);
                return limitMax;
            }
            mob.setHorizontalSpeed(0);
            return newPos;
        }
        else if (proba < 4){

            mob.setHorizontalSpeed(-2*16);
            double newPos = current;
            for (int i = 0; i <5; i++) {
                newPos = newPos + (delta * speed) / 1000;
                System.out.println(newPos);
            }
            if (newPos < limitMin){
                mob.setHorizontalSpeed(0);
                return limitMin;
            }
            mob.setHorizontalSpeed(0);
            return newPos;
        }
        return current;
    }
}
