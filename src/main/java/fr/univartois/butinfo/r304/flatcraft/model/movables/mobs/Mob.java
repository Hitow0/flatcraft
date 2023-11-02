package fr.univartois.butinfo.r304.flatcraft.model.movables.mobs;

import fr.univartois.butinfo.r304.flatcraft.model.FlatcraftGame;
import fr.univartois.butinfo.r304.flatcraft.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;

public class Mob extends AbstractMovable {


    /**
     * Crée une nouvelle instance de Mob.
     *
     * @param game      Le jeu dans lequel le mob évolue.
     * @param xPosition La position en x initiale de le mob.
     * @param yPosition La position en y initiale de le mob.
     * @param sprite    L'instance de {@link Sprite} représentant le mob.
     */
    protected Mob(FlatcraftGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
    }
}
