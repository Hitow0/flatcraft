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
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;

/**
 * La classe {@link CraftTableController} fournit le contrôleur permettant de gérer la
 * table de craft, sur laquelle l'utilisateur peut fabriquer de nouvelles ressources à
 * partir de celles collectées dans le jeu.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class CraftTableController {

    /**
     * La partie de Flatcraft en cours.
     */
    private FlatcraftGame game;

    /**
     * Les ressources déposées sur la table de craft.
     */
    private Resource[][] resources;

    /**
     * La grille représentant la table sur laquelle les ressources sont déposées.
     */
    @FXML
    private GridPane craftGrid;

    /**
     * Les vues représentant les ressources déposées sur la table de craft.
     */
    private ImageView[][] resourceViews;

    /**
     * Le produit obtenu à l'issue du craft.
     */
    private Resource product;

    /**
     * La vue représentant la ressource produite à l'issue du craft.
     */
    @FXML
    private ImageView productView;

    /**
     * Le bouton permettant d'effacer les ressources ajoutées à la table de craft.
     */
    @FXML
    private Button craftButton;

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
        // On initialise le tableau des ressources, qui est initialement vide.
        this.resources = new Resource[craftGrid.getRowCount()][craftGrid.getColumnCount()];

        // On initialise ensuite les vues pour ces ressources.
        this.resourceViews = new ImageView[craftGrid.getRowCount()][craftGrid.getColumnCount()];
        for (int i = 0; i < resourceViews.length; i++) {
            for (int j = 0; j < resourceViews[i].length; j++) {
                resourceViews[i][j] = new ImageView();

                // On configure la taille appropriée pour la vue.
                resourceViews[i][j].setFitHeight(16);
                resourceViews[i][j].setFitWidth(16);

                // On positionne la vue correctement.
                GridPane.setHalignment(resourceViews[i][j], HPos.CENTER);
                GridPane.setValignment(resourceViews[i][j], VPos.CENTER);
                resourceViews[i][j].setPickOnBounds(true);

                // On ajoute la possibilité de déposer des ressource sur la grille.
                dropResource(resourceViews[i][j], i, j);

                // On ajoute la vue à la grille.
                craftGrid.add(resourceViews[i][j], j, i);
            }
        }
    }

    /**
     * Dépose (ou pas) une ressource sur un emplacement de la grille.
     *
     * @param imageView La vue affichant l'image de la ressource.
     * @param row La ligne où la ressource a été déposée.
     * @param column La colonne où la ressource a été déposée.
     */
    private void dropResource(ImageView imageView, int row, int column) {
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
                // TODO Remplacez cette affectation par la récupération de la ressource dans l'inventaire du joueur.
                Optional<Resource> resource = Optional.empty();
                if (resource.isPresent()) {
                    resources[row][column] = resource.get();
                    craftButton.setDisable(false);
                    clearButton.setDisable(false);
                    success = true;
                }
            }

            event.setDropCompleted(success);
            event.consume();
        });

        // On remet la vue dans son état d'origine après le survol.
        imageView.setOnDragExited(event -> {
            if (resources[row][column] == null) {
                imageView.setImage(null);
            } else {
                imageView.setImage(resources[row][column].getSprite().getImage());
            }
            imageView.setOpacity(1);
            event.consume();
        });

        // Lorsque la ressource est déposée, elle est retirée de l'inventaire du joueur.
        imageView.setOnDragDone(event -> {
            // TODO Retirez de l'inventaire du joueur la ressource ayant été déposée.
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
     * Crée une nouvelle ressource à partir de celles déposées sur la table de craft.
     */
    @FXML
    private void craft() {
        // On crée la nouvelle ressource.
        product = game.craft(resources);
        productView.setImage(product.getSprite().getImage());

        // On met à jour les actions disponibles.
        addButton.setDisable(false);
        craftGrid.setDisable(true);
        craftButton.setDisable(true);
        clearButton.setDisable(true);
    }

    /**
     * Ajoute la ressource nouvellement créée à l'inventaire du joueur.
     */
    @FXML
    private void addToInventory() {
        // TODO Ajoutez un l'inventaire du joueur la ressource "product" ayant été produite.
    }

    /**
     * Supprime la totalité des ressources ayant été déposées sur la table de craft.
     */
    @FXML
    private void clear() {
        // On retire toutes les ressources déposées sur la table de craft.
        for (int i = 0; i < resources.length; i++) {
            for (int j = 0; j < resources[i].length; j++) {
                // TODO Remettez les ressources non null dans l'inventaire du joueur.
                resources[i][j] = null;
                resourceViews[i][j].setImage(null);
            }
        }

        // On met à jour les actions disponibles.
        craftButton.setDisable(true);
        clearButton.setDisable(true);
    }

}
