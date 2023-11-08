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

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import fr.univartois.butinfo.r304.flatcraft.model.map.createMap.IGenMapStrat;
import fr.univartois.butinfo.r304.flatcraft.model.map.decorator.DecoSlagHeap;
import fr.univartois.butinfo.r304.flatcraft.model.map.decorator.DecoTree;


import fr.univartois.butinfo.r304.flatcraft.model.movables.mobs.EndermanMovement;
import fr.univartois.butinfo.r304.flatcraft.model.movables.mobs.Mob;
import fr.univartois.butinfo.r304.flatcraft.model.movables.mobs.RandomMovement;

import fr.univartois.butinfo.r304.flatcraft.model.movables.player.Player;
import fr.univartois.butinfo.r304.flatcraft.view.ISpriteStore;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * La classe {@link FlatcraftGame} permet de gérer une partie du jeu Flatcraft.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class FlatcraftGame {

    /**
     * La largeur de la carte du jeu affichée (en pixels).
     */
    private final int width;

    /**
     * La hauteur de la carte du jeu affichée (en pixels).
     */
    private final int height;

    /**
     * Le contrôleur de l'application.
     */
    private IFlatcraftController controller;

    /**
     * L'instance e {@link ISpriteStore} utilisée pour créer les sprites du jeu.
     */
    private ISpriteStore spriteStore;

    /**
     * L'instance de {@link CellFactory} utilisée pour créer les cellules du jeu.
     */
    private CellFactory cellFactory;

    /**
     * La carte du jeu, sur laquelle le joueur évolue.
     */
    private GameMap map;

    /**
     * L'instance de {@link IGenMapStrat} utilisée pour créer la carte du jeu.
     */
    private IGenMapStrat genMapStrat;

    /**
     * Le temps écoulé depuis le début de la partie.
     */
    private IntegerProperty time = new SimpleIntegerProperty(12);

    /**
     * Le niveau actuel de la partie.
     */
    private IntegerProperty level = new SimpleIntegerProperty(1);

    /**
     * La représentation du joueur.
     */
    private Player player;

    /**
     * La liste des objets mobiles du jeu.
     */
    private List<IMovable> movableObjects = new CopyOnWriteArrayList<>();

    /**
     * L'animation simulant le temps qui passe dans le jeu.
     */
    private FlatcraftAnimation animation = new FlatcraftAnimation(this, movableObjects);

    /**
     * Crée une nouvelle instance de FlatcraftGame.
     *
     * @param width La largeur de la carte du jeu (en pixels).
     * @param height La hauteur de la carte du jeu (en pixels).
     * @param spriteStore L'instance de {@link ISpriteStore} permettant de créer les
     *        {@link Sprite} du jeu.
     * @param factory La fabrique permettant de créer les cellules du jeux.
     */
    public FlatcraftGame(int width, int height, ISpriteStore spriteStore, CellFactory factory) {
        this.width = width;
        this.height = height;
        this.spriteStore = spriteStore;
        this.cellFactory = factory;
        this.cellFactory.setSpriteStore(spriteStore);
    }

    /**
     * Associe à cette partie la classe gérant la création de la carte.
     *
     * @param genMapStrat La classe gérant la création de la carte.
     */
    public void setIGenMapStrat(IGenMapStrat genMapStrat){this.genMapStrat=genMapStrat;}

    /**
     * Donne la largeur de la carte du jeu affichée (en pixels).
     *
     * @return La largeur de la carte du jeu affichée (en pixels).
     */
    public int getWidth() {
        return width;
    }

    /**
     * Donne la hauteur de la carte du jeu affichée (en pixels).
     *
     * @return La hauteur de la carte du jeu affichée (en pixels).
     */
    public int getHeight() {
        return height;
    }

    /**
     * Associe à cette partie le contrôleur gérant l'affichage du jeu.
     *
     * @param controller Le contrôleur gérant l'affichage.
     */
    public void setController(IFlatcraftController controller) {
        this.controller = controller;
    }

    /**
     * Prépare la partie de Flatcraft avant qu'elle ne démarre.
     */
    public void prepare() {
        // On crée la carte du jeu.
        map = createMap();
        controller.prepare(map);

        // TODO On crée le joueur, qui se trouve sur le sol à gauche de la carte.
        player = new Player(this,0,(map.getSoilHeight()-1)*16, spriteStore.getSprite("tool_steelpick"));
        movableObjects.add(player);
        controller.addMovable(player);

        // TODO On crée un mob de test qui se déplace de manière linéraire
        Mob goomba = new Mob(this,50*16, (map.getSoilHeight()-1)*16, spriteStore.getSprite("snowball"), new RandomMovement());
        Mob enderman = new Mob(this,20*16, (map.getSoilHeight()-1)*16, spriteStore.getSprite("obsidian_shard"), new EndermanMovement());
        movableObjects.add(enderman);
        controller.addMovable(enderman);
        movableObjects.add(goomba);
        controller.addMovable(goomba);

        // TODO On fait le lien entre les différentes propriétés et leur affichage.
        controller.bindHealth(player.pvProperty());
        controller.bindXP(player.xpProperty());
        controller.bindTime(this.time);
        controller.bindLevel(this.level);
        // On démarre l'animation du jeu.
        animation.start();
    }

    /**
     * Crée la carte du jeu.
     *
     * @return La carte du jeu créée.
     */
    private GameMap createMap() {

        IGenMapStrat map = this.genMapStrat;
        map.genMap(height/spriteStore.getSpriteSize(),width/ spriteStore.getSpriteSize(),cellFactory);
        DecoSlagHeap slagHeap=new DecoSlagHeap(map);
        DecoTree tree=new DecoTree(map);
        slagHeap.genSlagHeap(cellFactory);
        tree.genTree(cellFactory);
        return map.getMap();
    }

    /**
     * Indique à cette partie de Flatcraft qu'une nouvelle heure s'est écoulée (dans le
     * jeu).
     */
    void oneHour() {
        time.set((time.get() + 1) % 24);
    }

    /**
     * Fait se déplacer le joueur vers le haut.
     */
    public void moveUp() {
        // TODO Implémentez cette méthode.
    }

    /**
     * Fait se déplacer le joueur vers le bas.
     */
    public void moveDown() {
        // TODO Implémentez cette méthode.
    }

    /**
     * Fait se déplacer le joueur vers la gauche.
     */
    public void moveLeft() {
        Cell cell = getCellOf(player);
        Cell cellToTravel = null;
        if(!(cell.getColumn()-1 < 0)) {
            cellToTravel = map.getAt(cell.getRow(), cell.getColumn() - 1);
        }
        if(cellToTravel != null && (cellToTravel.getResource()==null)) {
            player.setHorizontalSpeed(-4 * spriteStore.getSpriteSize());
            move(player);
        } else {
            player.setHorizontalSpeed(0);
        }
    }

    /**
     * Fait se déplacer le joueur vers la droite.
     */
    public void moveRight() {
        Cell cell = getCellOf(player);
        Cell cellToTravel = null;
        if(!(cell.getColumn()+1 >= map.getWidth())) {
            cellToTravel = map.getAt(cell.getRow(), cell.getColumn() + 1);
        }
        if(cellToTravel != null && (cellToTravel.getResource()==null)) {
            player.setHorizontalSpeed(4 * spriteStore.getSpriteSize());
            move(player);
        } else {
            player.setHorizontalSpeed(0);
        }
    }

    /**
     * Déplace un objet mobile en tenant compte de la gravité.
     *
     * @param movable L'objet à déplacer.
     */
    private void move(IMovable movable) {
        Cell currentCell = getCellOf(movable);
        for (int row = currentCell.getRow() + 1; row < map.getHeight(); row++) {
            Cell below = map.getAt(row, currentCell.getColumn());
            if (!below.move(movable)) {
                break;
            }
        }
    }

    /**
     * Interrompt le déplacement du joueur.
     */
    public void stopMoving() {
        player.setHorizontalSpeed(0);
    }

    /**
     * Fait sauter le joueur.
     */
    public void jump() {
        // TODO Cette méthode vous sera fournie ultérieurement.
    }

    /**
     * Fait creuser le joueur vers le haut.
     */
    public void digUp() {
        // TODO Nous reviendrons plus tard sur cette méthode.
    }

    /**
     * Fait creuser le joueur vers le bas.
     */
    public void digDown() {
        Cell cell = getCellOf(player);
        Cell cellToDig = null;
        if(!(cell.getRow()+1 >= map.getHeight())) {
            cellToDig = map.getAt(cell.getRow()+1, cell.getColumn());
        }
        if(cellToDig != null && cellToDig.getResource() != null){
            dig(cellToDig);
            move(player);
        }
    }

    /**
     * Fait creuser le joueur vers la gauche.
     */
    public void digLeft() {
        Cell cell = getCellOf(player);
        Cell cellToDig = null;
        if(!(cell.getColumn()-1 < 0)) {
            cellToDig = map.getAt(cell.getRow(), cell.getColumn() - 1);
        }
        if(cellToDig != null && cellToDig.getResource() != null){
            dig(cellToDig);
        }
    }

    /**
     * Fait creuser le joueur vers la droite.
     */
    public void digRight() {
        Cell cell = getCellOf(player);
        Cell cellToDig = null;
        if(!(cell.getColumn()+1 >= map.getWidth())) {
            cellToDig = map.getAt(cell.getRow(), cell.getColumn() + 1);
        }
        if(cellToDig != null && cellToDig.getResource() != null){
            dig(cellToDig);
        }
    }

    /**
     * Creuse la cellule donnée pour en extraire une ressource.
     *
     * @param toDig La cellule sur laquelle creuser.
     */
    private void dig(Cell toDig) {
        if(toDig.dig(player)){
            toDig.replaceBy(cellFactory.createSky());
        }
    }

    /**
     * Récupére la cellule correspondant à la position d'un objet mobile.
     * Il s'agit de la cellule sur laquelle l'objet en question occupe le plus de place.
     *
     * @param movable L'objet mobile dont la cellule doit être récupérée.
     *
     * @return La cellule occupée par l'objet mobile.
     */
    private Cell getCellOf(IMovable movable) {
        // On commence par récupérer la position du centre de l'objet.
        int midX = movable.getX() + (movable.getWidth() / 2);
        int midY = movable.getY() + (movable.getHeight() / 2);

        // On traduit cette position en position dans la carte.
        int row = midY / spriteStore.getSpriteSize();
        int column = midX / spriteStore.getSpriteSize();

        // On récupère enfin la cellule à cette position dans la carte.
        return map.getAt(row, column);
    }

}
