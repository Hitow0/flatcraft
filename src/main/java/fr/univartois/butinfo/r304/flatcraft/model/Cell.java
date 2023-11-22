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

import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;
import javafx.beans.property.ObjectProperty;

/**
 * La classe {@link Cell} représente une unité d'espace dans la carte du jeu.
 * Elle peut contenir une ressource ou rester vide (par exemple, le ciel, l'air, etc.).
 *
 * @author Daniel Le Berre
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public interface Cell {

    /**
     * Donne la ligne où se trouve cette cellule dans la carte du jeu.
     *
     * @return La ligne où se trouve cette cellule.
     */
    int getRow();

    /**
     * Donne la colonne où se trouve cette cellule dans la carte du jeu.
     *
     * @return La colonne où se trouve cette cellule.
     */
    int getColumn();

    /**
     * Donne le sprite représentant le contenu de cette cellule sur la carte.
     *
     * @return Le sprite représentant cette cellule.
     */
    Sprite getSprite();

    /**
     * Donne la propriété contenant le sprite représentant le contenu de cette cellule sur
     * la carte.
     *
     * @return La propriété contenant le sprite représentant cette cellule.
     */
    ObjectProperty<Sprite> getSpriteProperty();

    /**
     * Donne la ressource présente sur cette cellule sur la carte.
     *
     * @return La ressource présente sur cette cellule sur la carte.
     */
    Resource getResource();

    /**
     * Donne la propriété contenant la ressource présente sur cette cellule sur la carte.
     *
     * @return La propriété contenant la ressource présente sur cette cellule.
     */
    ObjectProperty<Resource> getResourceProperty();

    /**
     * Modifie cette cellule pour qu'elle ait maintenant le contenu de celle donnée en
     * paramètre.
     *
     * @param cell La cellule qui doit remplacer celle-ci.
     */
    void replaceBy(Cell cell);

    /**
     * Déplace un objet mobile du jeu si cette cellule le permet.
     * Par exemple, si une cellule ne contient pas de ressource, l'objet peut "passer à
     * travers" cette cellule.
     *
     * @param movable L'objet mobile à déplacer.
     *
     * @return Si l'objet mobile a été déplacé.
     */
    boolean move(IMovable movable);

    /**
     * Essaye d'extraire la ressource contenue dans cette cellule.
     * Si l'extraction réussit, la ressource est ajoutée à l'inventaire du joueur.
     *
     * @param player Le joueur qui mine et extrait la ressource.
     */
    void dig(IMovable player);

}
