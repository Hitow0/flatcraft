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
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableMap;

/**
 * L'interface {@link IFlatcraftController} définit le contrat à remplir par tout
 * contrôleur du jeu Flatcraft.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public interface IFlatcraftController {

    /**
     * Associe à ce contrôleur la partie du jeu Flatcraft en cours.
     *
     * @param game La partie en cours.
     */
    void setGame(FlatcraftGame game);

    /**
     * Prépare l'affichage du jeu avant que celui-ci ne démarre.
     *
     * @param map La carte du jeu.
     */
    void prepare(GameMap map);

    /**
     * Lie le temps écoulé depuis le début de la partie à son affichage dans la vue.
     *
     * @param timeProperty La propriété stockant le temps écoulé.
     */
    void bindTime(IntegerProperty timeProperty);

    /**
     * Lie les points d'expérience du joueur à leur affichage dans la vue.
     *
     * @param xpProperty La propriété stockant les points d'expérience du joueur.
     */
    void bindXP(IntegerProperty xpProperty);

    /**
     * Lie les points de vie du joueur à leur affichage dans la vue.
     *
     * @param healthProperty La propriété stockant les points de vie du joueur.
     */
    void bindHealth(IntegerProperty healthProperty);

    /**
     * Lie le niveau actuel à son affichage dans la vue.
     *
     * @param levelProperty La propriété stockant le niveau actuel.
     */
    void bindLevel(IntegerProperty levelProperty);

    /**
     * Lie l'inventaire du joueur à son affichage dans la vue.
     *
     * @param playerInventory L'inventaire du joueur.
     */
    void bindInventory(ObservableMap<Resource, Integer> playerInventory);

    /**
     * Ajoute un objet mobile à l'affichage du jeu.
     *
     * @param movable L'objet à afficher.
     */
    void addMovable(IMovable movable);

    /**
     * Affiche un message d'erreur sur l'interface utilisateur.
     *
     * @param message Le message d'erreur à afficher.
     */
    void displayError(String message);

}
