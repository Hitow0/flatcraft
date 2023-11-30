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

package fr.univartois.butinfo.r304.flatcraft.controller;

import java.util.Optional;

import fr.univartois.butinfo.r304.flatcraft.model.FlatcraftGame;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;

/**
 * La classe {@link FurnaceController} fournit le contrôleur permettant de gérer le
 * fourneau, dans lequel l'utilisateur peut fabriquer de nouvelles ressources à partir de
 * celles collectées dans le jeu.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class FurnaceController {

    /**
     * La partie de Flatcraft en cours.
     */
    private FlatcraftGame game;

    /**
     * Le combustible et la ressource déposée dans le fourneau.
     */
    private Resource[] resources = new Resource[2];

    /**
     * La grille représentant le fourneau dans lequel les ressources sont déposées.
     */
    @FXML
    private GridPane furnaceGrid;

    /**
     * La vue pour le combustible déposé dans le fourneau.
     */
    @FXML
    private ImageView fuelView;

    /**
     * La vue pour la ressource déposée dans le fourneau.
     */
    @FXML
    private ImageView resourceView;

    /**
     * Le produit obtenu à l'issue de la cuisson.
     */
    private Resource product;

    /**
     * La vue représentant la ressource produite à l'issue de la cuisson.
     */
    @FXML
    private ImageView productView;

    /**
     * Le bouton permettant d'effacer les ressources déposées dans le fourneau.
     */
    @FXML
    private Button cookButton;

    /**
     * Le bouton permettant d'ajouter la ressource produite à l'inventaire du joueur.
     */
    @FXML
    private Button addButton;

    /**
     * Le bouton permettant d'effacer les ressources ajoutées à la table de craft.
     */
    @FXML
    private Button clearButton;

    /**
     * Initialise ce contrôleur.
     */
    @FXML
    private void initialize() {
        fuelView.setPickOnBounds(true);
        dropResource(fuelView, 0);
        resourceView.setPickOnBounds(true);
        dropResource(resourceView, 1);
    }

    /**
     * Dépose (ou pas) une ressource dans un emplacement du fourneau.
     *
     * @param imageView La vue affichant l'image de la ressource.
     * @param index L'index où la ressource a été déposée.
     */
    private void dropResource(ImageView imageView, int index) {
        // On accepte tous les modes de transfert.
        imageView.setOnDragOver(event -> {
            if ((event.getGestureSource() != imageView) &&
                    event.getDragboard().hasString() && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.ANY);
            }
            event.consume();
        });

        // On affiche une prévisualisation du dépôt lorsque l'on survole la case.
        imageView.setOnDragEntered(event -> {
            if ((event.getGestureSource() != imageView) &&
                    event.getDragboard().hasString() && event.getDragboard().hasImage()) {
                imageView.setImage(event.getDragboard().getImage());
                imageView.setOpacity(0.2);
            }
            event.consume();
        });

        // On dépose la ressource au moment voulu.
        imageView.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            boolean success = false;

            if (dragboard.hasString() && dragboard.hasImage()) {
                Optional<Resource> resource = game.getPlayer().getInventoryResourceByName(dragboard.getString());
                if (resource.isPresent()) {
                    resources[index] = resource.get();
                    cookButton.setDisable(false);
                    clearButton.setDisable(false);
                    success = true;
                    imageView.setDisable(true);
                }
            }
            event.setDropCompleted(success);
            if(event.isDropCompleted()) {
                Optional<Resource> resource = game.getPlayer().getInventoryResourceByName(event.getDragboard().getString());
                resource.ifPresent(value -> game.getPlayer().removeResource(value));
            }
            event.consume();
        });

        // On remet la vue dans son état d'origine après le survol.
        imageView.setOnDragExited(event -> {
            if (resources[index] == null) {
                imageView.setImage(null);
            } else {
                imageView.setImage(resources[index].getSprite().getImage());
            }
            imageView.setOpacity(1);
            event.consume();
        });
    }

    /**
     * Associe à ce contrôleur la partie de Flatcraft en cours.
     *
     * @param game La partie de Flatcraft en cours.
     */
    public void setGame(FlatcraftGame game) {
        this.game = game;
    }

    /**
     * Crée une nouvelle ressource à partir de celles déposées dans le fourneau.
     */
    @FXML
    private void cook() {
        // On crée la nouvelle ressource.
        product = game.cook(resources[1], resources[0]);
        if(product != null) {
            productView.setImage(product.getSprite().getImage());

            // On met à jour les actions disponibles.
            addButton.setDisable(false);
            furnaceGrid.setDisable(true);
            cookButton.setDisable(true);
            clearButton.setDisable(true);
        }
    }

    /**
     * Ajoute la ressource nouvellement créée à l'inventaire du joueur.
     */
    @FXML
    private void addToInventory() {
        game.getPlayer().addObject(product);
        for (int i = 0; i < resources.length; i++) {
            resources[i] = null;
            resourceView.setImage(null);
            resourceView.setDisable(false);
            furnaceGrid.setDisable(false);
            fuelView.setImage(null);
            fuelView.setDisable(false);

            productView.setImage(null);
            addButton.setDisable(true);
            cookButton.setDisable(false);
            clearButton.setDisable(false);
        }
    }

    /**
     * Supprime la totalité des ressources ayant été déposées dans le fourneau.
     */
    @FXML
    private void clear() {
        if(resources[0] != null){
            game.getPlayer().addObject(resources[0]);
        }
        if(resources[1] != null){
            game.getPlayer().addObject(resources[1]);
        }
        resources[0] = null;
        fuelView.setImage(null);
        fuelView.setDisable(false);
        resources[1] = null;
        resourceView.setImage(null);
        resourceView.setDisable(false);
        furnaceGrid.setDisable(false);

        // On met à jour les actions disponibles.
        cookButton.setDisable(true);
        clearButton.setDisable(true);
    }

}
