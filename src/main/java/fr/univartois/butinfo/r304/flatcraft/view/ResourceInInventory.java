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

import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * La classe {@link ResourceInInventory} permet de représenter une ressource stockée dans
 * l'inventaire du joueur et affichée sur l'interface graphique.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class ResourceInInventory {

    /**
     * La ressource stockée dans l'inventaire.
     */
    private final Resource resource;

    /**
     * Le nombre d'occurence de la ressource dans l'inventaire.
     */
    private final IntegerProperty count;

    /**
     * Le composant affichant la ressource dans l'interface graphique.
     */
    private final StackPane pane;

    /**
     * Crée une nouvelle instance de ResourceInInventory.
     *
     * @param resource La ressource stockée dans l'inventaire.
     */
    public ResourceInInventory(Resource resource) {
        this.resource = resource;
        this.count = new SimpleIntegerProperty(1);
        this.pane = new StackPane();
        initialize();
    }

    /**
     * Initialise l'affichage de la ressource.
     */
    private void initialize() {
        // On ajoute l'image de la ressource.
        Image image = resource.getSprite().getImage();
        ImageView resourceView = new ImageView(image);
        resourceView.minWidth(image.getWidth());
        resourceView.maxWidth(image.getWidth());
        resourceView.minHeight(image.getHeight());
        resourceView.maxHeight(image.getHeight());
        pane.getChildren().add(resourceView);

        // On ajoute le nombre d'occurence.
        Label countView = new Label();
        countView.setFont(new Font(8));
        countView.setTextFill(Color.WHITE);
        countView.setBackground(
                new Background(new BackgroundFill(Color.RED, new CornerRadii(45), Insets.EMPTY)));
        countView.textProperty().bind(count.asString());
        pane.getChildren().add(countView);
    }

    /**
     * Donne la ressource stockée dans l'inventaire.
     *
     * @return La ressource stockée dans l'inventaire.
     */
    public Resource getResource() {
        return resource;
    }

    /**
     * Donne le compoosant graphique représentant la ressource.
     *
     * @return Le compoosant graphique représentant la ressource.
     */
    public Node getNode() {
        return pane;
    }

    /**
     * Lie le nombre d'occurences de cette ressource avec la propriété donnée en
     * paramètre.
     *
     * @param countProperty La propriété comptant le nombre d'occurences de la ressource
     *        actuellement dans l'inventaire.
     */
    public void bind(ObjectBinding<Integer> countProperty) {
        this.count.bind(countProperty);
    }

}
