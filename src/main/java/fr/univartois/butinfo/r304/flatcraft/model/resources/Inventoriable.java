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

package fr.univartois.butinfo.r304.flatcraft.model.resources;

import fr.univartois.butinfo.r304.flatcraft.view.Sprite;

/**
 * L'interface {@link Inventoriable} définit le contrat des éléments pouvant être placés
 * dans l'inventaire.
 * Il peut s'agir (entre autres) de ressources récupérées sur la carte, d'outils, etc.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public interface Inventoriable {

    /**
     * Donne le nom unique identifiant le type de cet élément.
     *
     * @return Le nom de cet élément.
     */
    String getName();

    /**
     * Donne le sprite représentant cet élément.
     *
     * @return Le sprite représentant cet élément.
     */
    Sprite getSprite();

    /**
     * Donne le type d'outils nécessaire pour extraire cet élément de la carte.
     *
     * @return Le type d'outils à utiliser.
     */
    ToolType getToolType();

    /**
     * Exécute l'action permise par cet élément.
     * Cette méthode est sans effet sur les ressources qui ne proposent pas d'action.
     */
    void execute();

}
