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

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import fr.univartois.butinfo.r304.flatcraft.model.craft.Cook;
import fr.univartois.butinfo.r304.flatcraft.model.craft.Craft;
import fr.univartois.butinfo.r304.flatcraft.model.craft.ListRecette;
import fr.univartois.butinfo.r304.flatcraft.model.craft.RuleParser;
import fr.univartois.butinfo.r304.flatcraft.model.map.EndFactory;
import fr.univartois.butinfo.r304.flatcraft.model.map.NetherFactory;
import fr.univartois.butinfo.r304.flatcraft.model.map.OverworldFactory;
import fr.univartois.butinfo.r304.flatcraft.model.map.createMap.IGenMapStrat;
import fr.univartois.butinfo.r304.flatcraft.model.map.decorator.DecoSlagHeap;
import fr.univartois.butinfo.r304.flatcraft.model.map.decorator.DecoTree;


import fr.univartois.butinfo.r304.flatcraft.model.movables.mobs.EndermanMovement;
import fr.univartois.butinfo.r304.flatcraft.model.movables.mobs.Mob;
import fr.univartois.butinfo.r304.flatcraft.model.movables.mobs.RandomMovement;

import fr.univartois.butinfo.r304.flatcraft.model.movables.player.Player;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.model.resources.breakingstate.BasicState;
import fr.univartois.butinfo.r304.flatcraft.model.resources.breakingstate.IBreakingState;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
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
    private static CellFactory cellFactory;

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

    private List<Craft> craftList;

    private List<Cook> cookList;

    /**
     * Instance de FlatcraftGame
     */
    private static FlatcraftGame instance;

    /**
     * Instance unique de FlatcraftGame.
     *
     * @param width La largeur de la carte du jeu (en pixels).
     * @param height La hauteur de la carte du jeu (en pixels).
     * @param spriteStore L'instance de {@link ISpriteStore} permettant de créer les
     *        {@link Sprite} du jeu.
     * @param factory La fabrique permettant de créer les cellules du jeux.
     */
    private FlatcraftGame(int width, int height, ISpriteStore spriteStore, CellFactory factory) {
        this.width = width;
        this.height = height;
        this.spriteStore = spriteStore;
        setCellFactory(OverworldFactory.getInstance());
        cellFactory.setSpriteStore(spriteStore);
        setCellFactory(NetherFactory.getInstance());
        cellFactory.setSpriteStore(spriteStore);
        setCellFactory(EndFactory.getInstance());
        cellFactory.setSpriteStore(spriteStore);
        //Faudra enlever factory dans le contructeur et la ligne en dessous comme ça dans la version final on spawnera tout le temps dans l'overworld
        cellFactory = factory;
    }

    /**
     * Méthode permettant de récuperer l'instance de FlatcraftGame
     *
     * @param width La largeur de la carte du jeu (en pixels).
     * @param height La hauteur de la carte du jeu (en pixels).
     * @param spriteStore L'instance de {@link ISpriteStore} permettant de créer les
     *        {@link Sprite} du jeu.
     * @param factory La fabrique permettant de créer les cellules du jeux.
     * @return instance de FlatcraftGame
     */
    public static FlatcraftGame getInstance(int width, int height, ISpriteStore spriteStore, CellFactory factory) {
        if (instance == null) {
            instance = new FlatcraftGame(width, height, spriteStore, factory);
        }
        return instance;
    }

    /**
     * Méthode pour récupérer la cellFactory
     * @return la cellfactory
     */
    public static CellFactory getCellFactory() {
        return cellFactory;
    }


    public static void setCellFactory(CellFactory cellFactory) {
        FlatcraftGame.cellFactory = cellFactory;
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
    public void prepare() throws IOException {
        // On crée la carte du jeu.
       /* setCellFactory(NetherFactory.getInstance());
        createMap();
        setCellFactory(EndFactory.getInstance());
        createMap();
        */
        setCellFactory(OverworldFactory.getInstance());
        map = createMap();
        controller.prepare(map);
        int i=0;
        while(map.getAt(i,0).getResource() == null){
            i++;
        }
        // On crée le joueur, qui se trouve sur le sol à gauche de la carte.
        player = new Player(this,0,(i-1)*16, spriteStore.getSprite("hemery"));
        movableObjects.add(player);
        controller.addMovable(player);

        i=0;
        while(map.getAt(i,50).getResource() == null){
            i++;
        }
        // On crée un mob de test qui se déplace de manière linéraire
        Mob goomba = new Mob(this,50*16, (i-1)*16, spriteStore.getSprite("chmeiss"), new RandomMovement());
        i=0;
        while(map.getAt(i,20).getResource() == null){
            i++;
        }
        Mob enderman = new Mob(this,20*16, (i-1)*16, spriteStore.getSprite("enderman"), new EndermanMovement());
        movableObjects.add(enderman);
        controller.addMovable(enderman);
        movableObjects.add(goomba);
        controller.addMovable(goomba);

        // On fait le lien entre les différentes propriétés et leur affichage.
        controller.bindHealth(player.pvProperty());
        controller.bindXP(player.xpProperty());
        controller.bindTime(this.time);
        controller.bindLevel(this.level);
        controller.bindInventory(player.getInventaire());

        //On récupère les crafts possibles
        RuleParser craftParser = new RuleParser("craftrules");
        RuleParser furnaceParser = new RuleParser("furnacerules");

        craftParser.parse();
        furnaceParser.parse();

        cookList = ListRecette.getInstance().getCookList();
        craftList = ListRecette.getInstance().getCraftList();

        // On démarre l'animation du jeu.
        animation.start();
    }



    /**
     * Crée la carte du jeu.
     *
     * @return La carte du jeu créée.
     */
    private GameMap createMap() {
        IGenMapStrat mapStrat = this.genMapStrat;
        mapStrat.setHeight(height/spriteStore.getSpriteSize());
        mapStrat.setWidth(width/ spriteStore.getSpriteSize());
        mapStrat.setCell(cellFactory);
        DecoSlagHeap slagHeap=new DecoSlagHeap(mapStrat);
        DecoTree tree=new DecoTree(mapStrat);
        slagHeap.genSlagHeap(cellFactory);
        tree.genTree(cellFactory);
        return mapStrat.getMap();
    }


    private void nextMap(int bOrA) {
        if (bOrA == 0)
            genMapStrat.mapMoveLeft();
        if (bOrA == 1)
            genMapStrat.mapMoveRight();
        map = genMapStrat.getMap();
    }

    private void tpMap(int bOrA){
        controller.prepare(map);
        if(bOrA==0){
            player.setX(width*16);
        }
        if(bOrA==1){
            player.setX(0);
        }
    }

    /**
     * Indique à cette partie de Flatcraft qu'une nouvelle heure s'est écoulée
     * (dans le jeu).
     */
    void oneHour() {
        time.set((time.get() + 1) % 24);
    }

    /**
     * Vérifie si la cellule est un portail et change la factory et la map en fonction du type du portail
     * @param cellToTravel
     */
    private void changeDimension(Cell cellToTravel) {
        Cell cell = getCellOf(player);

        if (cellToTravel != null && "nether_portal".equals(cellToTravel.getResource().getName())) { //On vérifie si le joueur est dans un portail du Nether
            //On regarde le type de cellFactory pour savoir dans quelle dimension on est
            if (cellFactory.equals(OverworldFactory.getInstance())) {
                setCellFactory(NetherFactory.getInstance());
                map=genMapStrat.getMap();
                controller.prepare(map);
                player.setX(0);
                player.setY((map.getSoilHeight()-1)*16);
            } else if (cellFactory.equals(NetherFactory.getInstance())) {
                setCellFactory(OverworldFactory.getInstance());
                map=genMapStrat.getMap();
                controller.prepare(map);
                player.setX(0);
                player.setY((map.getSoilHeight()-1)*16);
            }


        } else if (cellToTravel != null && "end_portal".equals(cellToTravel.getResource().getName())) { //On vérifie si le joueur est dans un portail de l'End
            //On regarde le type de cellFactory pour savoir dans quelle dimension on est
            if (cellFactory.equals(OverworldFactory.getInstance())) {
                setCellFactory(EndFactory.getInstance());
                map=genMapStrat.getMap();
                controller.prepare(map);
                player.setX(0);
                player.setY((map.getSoilHeight()-1)*16);
            } else if (cellFactory.equals(EndFactory.getInstance())) {
                setCellFactory(OverworldFactory.getInstance());
                map=genMapStrat.getMap();
                controller.prepare(map);
                player.setX(0);
                player.setY((map.getSoilHeight()-1)*16);
            }
        }
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
        }else{
            map = genMapStrat.getBeforeMap();
            if(map.getAt(player.getY()/16, 79).getResource() == null) {
                tpMap(0);
                nextMap(0);
            }
        }
        if(cellToTravel != null && (cellToTravel.getResource()==null)) {
            player.setHorizontalSpeed(-4 * spriteStore.getSpriteSize());
            move(player);
        } else {
            changeDimension(cellToTravel);
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
        }else {
            map = genMapStrat.getAfterMap();
            if (map.getAt(player.getY() / 16, 0).getResource() == null){
                tpMap(1);
                nextMap(1);
            }
        }
        if(cellToTravel != null && (cellToTravel.getResource()==null)) {
            player.setHorizontalSpeed(4 * spriteStore.getSpriteSize());
            move(player);
        } else {
            changeDimension(cellToTravel);
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
        // Vérifier si le joueur est au sol (sur une cellule sans objet en dessous).
        Cell currentCell = getCellOf(player);
        if (player.getVerticalSpeed()!=0)
            player.setVerticalSpeed(0);
        else
            player.setVerticalSpeed(-4 * spriteStore.getSpriteSize());
    }


    /**
     * Fait creuser le joueur vers le haut.
     */
    public void digUp() {
        Cell cell = getCellOf(player);
        Cell cellToDig = null;
        if(cell.getRow()-1 < map.getHeight()) {
            cellToDig = map.getAt(cell.getRow()-1, cell.getColumn());
        }
        if(cellToDig != null && cellToDig.getResource() != null){
            cellToDig.dig(player);
            move(player);
        }    }

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
            cellToDig.dig(player);
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
            if(cellToDig != null && cellToDig.getResource() != null){
                cellToDig.dig(player);
            }
        } else {
            map = genMapStrat.getBeforeMap();
            cellToDig = map.getAt(player.getY() / 16, 79);
            if(cellToDig != null && cellToDig.getResource() != null){
                cellToDig.dig(player);
                map = genMapStrat.getMap();
            }
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
        } else {
            map = genMapStrat.getAfterMap();
            cellToDig = map.getAt(player.getY() / 16, 0);
        }
        if(cellToDig != null && cellToDig.getResource() != null){
            cellToDig.dig(player);
            map = genMapStrat.getMap();
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

    /**
     * Crée une nouvelle ressource à l'aide d'un ensemble de ressources, en suivant les
     * règles de la table de craft.
     *
     * @param inputResources Les ressources déposées sur la table de craft.
     *
     * @return La ressource produite.
     */
    public Map<Resource, Integer> craft(Resource[][] inputResources) {
        List<Resource> resourceInput = new ArrayList<>();
        Map<Resource, Integer> produit = new HashMap<>();
        for (Resource[] inputResource : inputResources) {
            resourceInput.addAll(Arrays.asList(inputResource));
        }
        for(Craft craft : craftList){
            if(craft.getCraft().equals(resourceInput)){
                produit.put(craft.getProduct(), craft.getQuantity());
                return produit;
            }
        }
        controller.displayError("Aucun craft n'a ete trouve");
        return null;
    }

    /**
     * Crée une nouvelle ressource à l'aide d'un combustible et d'une ressource, en suivant les
     * règles du fourneau.
     *
     * @param fuel Le matériau combustible utilisé dans le fourneau.
     * @param resource La ressource à transformer.
     *
     * @return La ressource produite.
     */
    public Resource cook(Resource fuel, Resource resource) {
        for(Cook cook : cookList){
            if(cook.getIngredient().equals(resource)){
                return cook.getProduit();
            }
        }
        controller.displayError("Aucune cuisson n'a ete trouvee");
        return null;
    }

    public Player getPlayer() {
        return player;
    }
}
