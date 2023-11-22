/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * Il est fourni "tel quel", sans garantie d’aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d’adéquation
 * à un usage particulier et d’absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d’auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d’un contrat, d’un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d’autres éléments du logiciel.
 *
 * (c) 2023 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.flatcraft.model;

import java.util.List;

import javafx.animation.AnimationTimer;

/**
 * La classe {@link FlatcraftAnimation} implante l'animation permettant de simuler le
 * temps qui passe pendant une partie du jeu Flatcraft.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
final class FlatcraftAnimation extends AnimationTimer {

    /**
     * Le temps "humain" représentant une heure dans le jeu.
     * On considère ici qu'une minute équivaut à une heure dans le jeu.
     */
    private static final long ONE_HOUR = 60_000;

    /**
     * La partie de Flatcraft en cours.
     */
    private final FlatcraftGame game;

    /**
     * La liste des objets pouvant se déplacer dans le jeu.
     */
    private final List<IMovable> movableObjects;

    /**
     * Le timestamp de la dernière mise à jour de l'état du jeu.
     */
    private long previousTimestamp;

    /**
     * Le timestamp de la dernière mise à jour de l'heure du jeu.
     */
    private long previousHour;

    /**
     * Crée une nouvelle instance de FlatcraftAnimation.
     *
     * @param game La partie de Flatcraft en cours.
     * @param movableObjects La liste des objets pouvant se déplacer dans le jeu.
     */
    public FlatcraftAnimation(FlatcraftGame game, List<IMovable> movableObjects) {
        this.game = game;
        this.movableObjects = movableObjects;
    }

    /*
     * (non-Javadoc)
     *
     * @see javafx.animation.AnimationTimer#start()
     */
    @Override
    public void start() {
        previousTimestamp = -1;
        previousHour = -1;
        super.start();
    }

    /*
     * (non-Javadoc)
     *
     * @see javafx.animation.AnimationTimer#handle(long)
     */
    @Override
    public void handle(long now) {
        // Lors de la première mise à jour, on se contente de conserver le timestamp.
        if (previousTimestamp < 0) {
            previousTimestamp = now;
            previousHour = now;
            return;
        }

        // On détermine le temps écoulé depuis la dernière mise à jour.
        long delta = (now - previousTimestamp) / 1000000;
        previousTimestamp = now;

        // On met à jour l'état du jeu.
        moveObjects(delta);
        updateHour(now);
    }

    /**
     * Met à jour la position des différents objets du jeu.
     *
     * @param delta Le temps écoulé depuis la dernière mise à jour.
     */
    private void moveObjects(long delta) {
        for (IMovable movable : movableObjects) {
            movable.move(delta);
        }
    }

    /**
     * Fait passer le jeu à l'heure suivante lorsque nécessaire.
     *
     * @param now L'heure actuelle.
     */
    private void updateHour(long now) {
        long delay = (now - previousHour) / 1000000;
        if (delay >= ONE_HOUR) {
            game.oneHour();
            previousHour = now;
        }
    }

}
