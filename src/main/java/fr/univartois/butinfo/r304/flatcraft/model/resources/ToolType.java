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

/**
 * L'énumération {@link ToolType} permet de préciser le type d'outils nécessaire pour
 * récupérer une ressource depuis la carte du jeu.
 *
 * @author Daniel Le Berre
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public enum ToolType {

    /**
     * Le type d'outils nécessaire pour récupérer un élément qui ne requiert pas d'outil
     * particulier pour être récupéré.
     */
    NO_TOOL,

    /**
     * Le type d'outils nécessaire pour récupérer un élément qui requiert un outil de
     * force moyenne pour être récupéré.
     */
    MEDIUM_TOOL,

    /**
     * Le type d'outils nécessaire pour récupérer un élément qui requiert un outil de
     * force élevée pour être récupéré.
     */
    HARD_TOOL;

}
