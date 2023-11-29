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

/**
 * L'interface {@link GameMap} définit le contrat à remplir par les différentes
 * implantations possibles d'une carte du jeu.
 *
 * @author Daniel Le Berre
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public interface GameMap {

    /**
     * Donne la hauteur de cette carte, mesurée en nombre de cellules.
     *
     * @return La hauteur de cette carte.
     */
    int getHeight();

    /**
     * Donne la largeur de cette carte, mesurée en nombre de cellules.
     *
     * @return La largeur de cette carte.
     */
    int getWidth();

    /**
     * Donne la hauteur à laquelle se situe la surface du sol dans cette map,
     * mesurée en nombre de cellules.
     *
     * @return La hauteur du sol.
     */
    int getSoilHeight();

    /**
     * Donne la cellule à la position donnée sur cette carte.
     *
     * @param row La ligne de la cellule à récupérer.
     * @param column La colonne de la cellule à récupérer.
     *
     * @return La cellule à la position donnée.
     *
     * @throws IllegalArgumentException Si la position donnée est en dehors de cette
     *         carte.
     */
    Cell getAt(int row, int column);

    /**
     * Modifie la cellule à la position donnée sur cette carte.
     *
     * @param row La ligne de la cellule à modifier.
     * @param column La colonne de la cellule à modifier.
     * @param cell La nouvelle cellule à placer.
     *
     * @throws IllegalArgumentException Si la position donnée est en dehors de cette
     *         carte.
     */
    void setAt(int row, int column, Cell cell);

}
