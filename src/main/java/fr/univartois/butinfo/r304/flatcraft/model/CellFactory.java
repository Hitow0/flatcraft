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

import fr.univartois.butinfo.r304.flatcraft.view.ISpriteStore;

/**
 * L'interface {@link CellFactory} permet de créer les différentes cellules composant la
 * carte du jeu Flatcraft.
 *
 * @author Daniel Le Berre
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public interface CellFactory {

    /**
     * Associe à cette fabrique l'instance de {@link ISpriteStore} permettant de créer les
     * images des cellules à créer.
     *
     * @param spriteStore L'instance de {@link ISpriteStore} gérant les images.
     */
    void setSpriteStore(ISpriteStore spriteStore);

    /**
     * Crée une cellule de ciel.
     * Il peut s'agir de ciel bleu ou d'un nuage, par exemple.
     *
     * @return La cellule créée.
     */
    Cell createSky();

    /**
     * Crée une cellule représentant la surface du sol.
     * Il peut s'agir de pelouse ou d'eau, par exemple.
     *
     * @return La cellule créée.
     */
    Cell createSoilSurface();

    /**
     * Crée une cellule représentant le sous-sol.
     * Il peut s'agir de terre, ou de minerai à aller chercher en profondeur, par exemple.
     *
     * @return La cellule créée.
     */
    Cell createSubSoil(int surfaceSoilHeight, int j);

    /**
     * Crée une cellule représentant le tronc d'un arbre.
     *
     * @return La cellule créée.
     */
    Cell createTrunk();

    /**
     * Crée une cellule représentant les feuilles d'un arbre.
     *
     * @return La cellule créée.
     */
    Cell createLeaves();

    /**
     * Créer une nouvelle cellule comportant le niveau de cassure.
     * @param toDig : la cellule qui est minée
     * @param breakingLevel : le niveau de cassage de cette cellule.
     * @return La cellule créée.
     */
    Cell changeBreakingLevel(Cell toDig, int breakingLevel);
}
