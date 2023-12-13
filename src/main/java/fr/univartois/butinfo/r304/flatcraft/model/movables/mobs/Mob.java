package fr.univartois.butinfo.r304.flatcraft.model.movables.mobs;

import fr.univartois.butinfo.r304.flatcraft.model.FlatcraftGame;
import fr.univartois.butinfo.r304.flatcraft.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;

import java.util.Objects;

public class Mob extends AbstractMovable {

    private final IMobStrategy movement;

    /**
     * Crée une nouvelle instance de Mob.
     *
     * @param game      Le jeu dans lequel le mob évolue.
     * @param xPosition La position en x initiale de le mob.
     * @param yPosition La position en y initiale de le mob.
     * @param sprite    L'instance de {@link Sprite} représentant le mob.
     */
    public Mob(FlatcraftGame game, double xPosition, double yPosition, Sprite sprite, IMobStrategy movement) {
        super(game, xPosition, yPosition, sprite);
        this.movement = movement;
    }

    @Override
    public boolean move(long delta){
        int limitMaxX = game.getWidth() - getWidth();
        double newX = movement.mobMovement(xPosition.get(), horizontalSpeed, delta, this, 0, limitMaxX);
        xPosition.set(newX);
        return (newX != 0) && (newX != limitMaxX);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Mob mob = (Mob) o;
        return Objects.equals(movement, mob.movement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), movement);
    }
}
