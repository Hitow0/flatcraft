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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.FlatcraftGame;
import fr.univartois.butinfo.r304.flatcraft.model.GameMap;
import fr.univartois.butinfo.r304.flatcraft.model.IFlatcraftController;
import fr.univartois.butinfo.r304.flatcraft.model.IMovable;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.view.ResourceInInventory;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * La classe {@link FlatcraftController} fournit le contrôleur permettant de jouer au jeu
 * Flatcraft dans une interface JavaFX.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class FlatcraftController implements IFlatcraftController {

    /**
     * La partie du jeu Flatcraft en cours.
     */
    private FlatcraftGame game;

    /**
     * La fenêtre dans laquelle se déroule le jeu.
     */
    private Stage stage;

    /**
     * Le conteneur dans lequel l'arrière-plan du jeu est affiché.
     */
    @FXML
    private Pane background;

    /**
     * Le conteneur dans lequel les éléments mobiles du jeu sont affichés.
     */
    @FXML
    private Pane mainPane;

    /**
     * Le label affichant le temps écoulé depuis le début de la partie.
     */
    @FXML
    private Label time;

    /**
     * Le label affichant les points d'expérience du joueur.
     */
    @FXML
    private Label xp;

    /**
     * Le label affichant les points de vie du joueur.
     */
    @FXML
    private Label health;

    /**
     * Le label affichant le niveau actuel.
     */
    @FXML
    private Label level;

    /**
     * Le conteneur permettant d'afficher l'inventaire du joueur.
     */
    @FXML
    private HBox inventory;

    /**
     * Les composants affichant les ressources actuellement dans l'inventaire du joueur.
     */
    private Map<Resource, ResourceInInventory> resourcesInInventory = new HashMap<>();

    private boolean keyListenerAdded = false;

    /**
     * Associe à ce contrôleur la fenêtre dans laquelle se déroule le jeu.
     *
     * @param stage La fenêtre dans laquelle se déroule le jeu.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.IFlatcraftController#setGame(fr.
     * univartois.butinfo.r304.flatcraft.model.FlatcraftGame)
     */
    @Override
    public void setGame(FlatcraftGame game) {
        this.game = game;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.IFlatcraftController#prepare(fr.
     * univartois.butinfo.r304.flatcraft.model.GameMap)
     */
    @Override
    public void prepare(GameMap map) {
        createBackground(map);
        if(!this.keyListenerAdded){
            addKeyListeners();
        }
    }

    /**
     * Crée l'arrière-plan du jeu.
     *
     * @param map La carte du jeu à partir de laquelle créer l'arrière-plan.
     */
    private void createBackground(GameMap map) {
        // On commence par récupérer les dimensions de la carte.
        int height = map.getHeight();
        int width = map.getWidth();

        // On ajoute les images représentant les cellules de la carte.
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // On récupère l'image de la cellule.
                Cell cell = map.getAt(i, j);
                Image image = cell.getSprite().getImage();

                // On crée le composant pour afficher l'image, et on l'ajoute.
                ImageView view = new ImageView(image);
                view.relocate(j * image.getHeight(), i * image.getWidth());
                background.getChildren().add(view);

                // Tout changement dans la cellule est reporté sur l'affichage.
                cell.getSpriteProperty().addListener((p, o, n) -> view.setImage(n.getImage()));
            }
        }
    }

    /**
     * Ajoute les écouteurs de saisie clavier à la fenêtre affichant le jeu.
     */
    private void addKeyListeners() {
        // L'appui (bref) sur la barre espace déclenche un saut.
        stage.addEventFilter(KeyEvent.KEY_TYPED, e -> {
            if (" ".equals(e.getCharacter())) {
                game.jump();
            }
        });

        // L'appui sur une flèche permet au joueur de se déplacer ou de creuser.
        stage.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            KeyCode code = e.getCode();
            if(e.isControlDown()){
                game.stopMoving();
            }
            if (code.isArrowKey() && e.isControlDown()) {
                dig(code);
            } else if (code.isArrowKey()) {
                move(code);
            }
        });

        // Le relâchement d'une flèche interrompt le déplacement.
        stage.addEventFilter(KeyEvent.KEY_RELEASED, e -> {
            if ((!e.isAltDown()) && e.getCode().isArrowKey()) {
                game.stopMoving();
            }
        });

        keyListenerAdded = true;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.controller.IFlatcraftController#bindTime(
     * javafx.beans.property.IntegerProperty)
     */
    @Override
    public void bindTime(IntegerProperty timeProperty) {
        time.textProperty().bind(timeProperty.asString().concat(":00"));
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.butinfo.r304.flatcraft.controller.IFlatcraftController#bindXP(javafx.
     * beans.property.IntegerProperty)
     */
    @Override
    public void bindXP(IntegerProperty xpProperty) {
        xp.textProperty().bind(xpProperty.asString());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.butinfo.r304.flatcraft.controller.IFlatcraftController#bindHealth(
     * javafx.beans.property.IntegerProperty)
     */
    @Override
    public void bindHealth(IntegerProperty healthProperty) {
        health.textProperty().bind(healthProperty.asString());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.butinfo.r304.flatcraft.controller.IFlatcraftController#bindLevel(
     * javafx.beans.property.IntegerProperty)
     */
    @Override
    public void bindLevel(IntegerProperty levelProperty) {
        level.textProperty().bind(levelProperty.asString());
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.IFlatcraftController#bindInventory(
     * javafx.collections.ObservableMap)
     */
    @Override
    public void bindInventory(ObservableMap<Resource, Integer> playerInventory) {
        playerInventory.addListener((MapChangeListener<Resource, Integer>) change -> {
            if (change.wasAdded() && !resourcesInInventory.containsKey(change.getKey())) {
                // Il faut ajouter la ressource à l'affichage.
                ResourceInInventory resource = new ResourceInInventory(change.getKey());
                resource.bind(Bindings.valueAt(playerInventory, change.getKey()));
                resourcesInInventory.put(change.getKey(), resource);
                dragResource(resource);
                inventory.getChildren().add(resource.getNode());
            } else if (change.wasRemoved() && (change.getValueRemoved() == 1) && game.getPlayer().getInventaire().get(change.getKey()) != 2) {
                // La ressource doit être retirée de l'affichage.
                ResourceInInventory resource = resourcesInInventory.remove(change.getKey());
                inventory.getChildren().remove(resource.getNode());
            }
        });
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.IFlatcraftController#addMovable(fr.
     * univartois.butinfo.r304.flatcraft.model.IMovable)
     */
    @Override
    public void addMovable(IMovable movable) {
        // On affiche l'objet au bon endroit.
        ImageView view = new ImageView(movable.getSprite().getImage());
        view.xProperty().bind(movable.getXProperty());
        view.yProperty().bind(movable.getYProperty());
        mainPane.getChildren().add(view);

        // Lorsque le sprite de l'objet change, son image doit changer également.
        movable.getSpriteProperty().addListener((p, o, n) -> view.setImage(n.getImage()));
    }

    /**
     * Permet au joueur de se déplacer en utilisant une flèche directionnelle.
     *
     * @param code Le code de la touche sur laquelle le joueur a appuyé.
     */
    @SuppressWarnings("incomplete-switch")
    private void move(KeyCode code) {
        switch (code) {
            case UP -> game.moveUp();
            case DOWN -> game.moveDown();
            case LEFT -> game.moveLeft();
            case RIGHT -> game.moveRight();
        }
    }

    /**
     * Permet au joueur de creuser en utilisant une flèche directionnelle.
     *
     * @param code Le code de la touche sur laquelle le joueur a appuyé.
     */
    @SuppressWarnings("incomplete-switch")
    private void dig(KeyCode code) {
        switch (code) {
            case UP -> game.digUp();
            case DOWN -> game.digDown();
            case LEFT -> game.digLeft();
            case RIGHT -> game.digRight();
        }
    }

    /**
     * Affiche la table de craft.
     *
     * @throws IOException Si une erreur se produit lors du chargement de la vue.
     */
    @FXML
    private void showCraftTable() throws IOException {
        // On charge la vue et son contrôleur.
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/crafttable.fxml"));
        Parent viewContent = fxmlLoader.load();
        CraftTableController controller = fxmlLoader.getController();

        // On initialise le contrôleur.
        controller.setGame(game);

        // On affche la fenêtre.
        Stage crafttableStage = new Stage();
        crafttableStage.initOwner(stage);
        crafttableStage.setScene(new Scene(viewContent));
        crafttableStage.show();
    }

    /**
     * Affiche le fourneau.
     *
     * @throws IOException Si une erreur se produit lors du chargement de la vue.
     */
    @FXML
    private void showFurnace() throws IOException {
        // On charge la vue et son contrôleur.
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/furnace.fxml"));
        Parent viewContent = fxmlLoader.load();
        FurnaceController controller = fxmlLoader.getController();

        // On initialise le contrôleur.
        controller.setGame(game);

        // On affche la fenêtre.
        Stage furnaceStage = new Stage();
        furnaceStage.initOwner(stage);
        furnaceStage.setScene(new Scene(viewContent));
        furnaceStage.show();
    }

    /**
     * Gère le déplacement d'une ressource en utilisant un glisser-déposer.
     *
     * @param resource La ressource sur laquelle le glisser-déposer a été initié.
     */
    private void dragResource(ResourceInInventory resource) {
        resource.getNode().setOnDragDetected(event -> {
            Dragboard dragboard = resource.getNode().startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString(resource.getResource().getName());
            content.putImage(resource.getResource().getSprite().getImage());
            dragboard.setContent(content);
            event.consume();
        });
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.butinfo.r304.flatcraft.model.IFlatcraftController#displayError(java.
     * lang.String)
     */
    @Override
    public void displayError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
