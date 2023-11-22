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

package fr.univartois.butinfo.r304.flatcraft.view;

import java.util.NoSuchElementException;

/**
 * L'interface {@link ISpriteStore} permet de créer des instances de {@link Sprite} à
 * partir de leur identifiant.
 * Typiquement, cet identifiant permet d'associer l'image d'un élément du jeu à l'instance
 * de {@link Sprite} qui sera créée.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
@FunctionalInterface
public interface ISpriteStore {

    /**
     * Charge une instance de {@link Sprite} donnée par son identifiant.
     *
     * @param identifier L'identifiant de l'instance de {@link Sprite} à charger.
     *
     * @return L'instance de {@link Sprite} correspondant à l'identifiant donné.
     *
     * @throws NoSuchElementException Si aucune instance de {@link Sprite} ne correspond à
     *         l'identifiant donné.
     */
    Sprite getSprite(String identifier);

    /**
     * Donne la taille des sprites à charger.
     *
     * @return La taille des sprites (en pixels).
     */
    default int getSpriteSize() {
        return 16;
    }

}
