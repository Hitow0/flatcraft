package fr.univartois.butinfo.r304.flatcraft.model.map.createMap;

import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.FlatcraftGame;
import fr.univartois.butinfo.r304.flatcraft.model.listMap.ListMap;
import fr.univartois.butinfo.r304.flatcraft.model.map.EndFactory;
import fr.univartois.butinfo.r304.flatcraft.model.map.NetherFactory;
import fr.univartois.butinfo.r304.flatcraft.model.map.OverworldFactory;
import fr.univartois.butinfo.r304.flatcraft.model.map.SimpleGameMap;

public class GenMapStrat1 implements IGenMapStrat{
    private static GenMapStrat1 instance;

    private ListMap overworldMaps;

    private ListMap netherMaps;

    private  ListMap endMaps;

    private int height;

    private int width;

    private CellFactory cell;

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setCell(CellFactory cell) {
        this.cell = cell;
    }

    /**
     * Change la factory de l'instance pour qu'elle soit la même que celle de FlatcraftGame
     */
    private void sameFactory(){this.cell = FlatcraftGame.getCellFactory();}

    private GenMapStrat1() {
        overworldMaps=new ListMap(this);
        netherMaps = new ListMap(this);
        endMaps = new ListMap(this);
    }

    public static GenMapStrat1 getInstance() {
        if (instance == null)
            instance = new GenMapStrat1();
        return instance;
    }

    public SimpleGameMap genMap(){
        SimpleGameMap map = new SimpleGameMap(height, width, height / 2);
        sameFactory();
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (i < map.getSoilHeight())
                        map.setAt(i, j, cell.createSky());
                    if (i == map.getSoilHeight())
                        map.setAt(i, j, cell.createSoilSurface());
                    if (i > map.getSoilHeight())
                        map.setAt(i, j, cell.createSubSoil(map.getSoilHeight(),i));
                }
            }
            return map;
    }

    public SimpleGameMap getMap() {
        if (FlatcraftGame.getCellFactory().equals(OverworldFactory.getInstance())) {
            return overworldMaps.getMap();
        }
        if (FlatcraftGame.getCellFactory().equals(NetherFactory.getInstance())){
            return netherMaps.getMap();
        }
        if (FlatcraftGame.getCellFactory().equals(EndFactory.getInstance())){
            return endMaps.getMap();
        }
        return null;
    }

    public SimpleGameMap getAfterMap(){
        if (FlatcraftGame.getCellFactory().equals(OverworldFactory.getInstance())) {
            return overworldMaps.getAfterMap();
        }
        if (FlatcraftGame.getCellFactory().equals(NetherFactory.getInstance())){
            return netherMaps.getAfterMap();
        }
        return endMaps.getAfterMap();


    }

    public SimpleGameMap getBeforeMap(){
        if (FlatcraftGame.getCellFactory().equals(OverworldFactory.getInstance())) {
            return overworldMaps.getBeforeMap();
        }
        if (FlatcraftGame.getCellFactory().equals(NetherFactory.getInstance())){
            return netherMaps.getBeforeMap();
        }
        return endMaps.getBeforeMap();

    }

    public void mapMoveLeft(){
        if (FlatcraftGame.getCellFactory().equals(OverworldFactory.getInstance())) {
            overworldMaps.MoveBefore();
        }
        if (FlatcraftGame.getCellFactory().equals(NetherFactory.getInstance())){
            netherMaps.MoveBefore();
        }
        if (FlatcraftGame.getCellFactory().equals(EndFactory.getInstance())){
            endMaps.MoveBefore();
        }
    }

    public void mapMoveRight(){
        if (FlatcraftGame.getCellFactory().equals(OverworldFactory.getInstance())) {
            overworldMaps.MoveAfter();
        }
        if (FlatcraftGame.getCellFactory().equals(NetherFactory.getInstance())){
            netherMaps.MoveAfter();
        }
        if (FlatcraftGame.getCellFactory().equals(EndFactory.getInstance())){
            endMaps.MoveAfter();
        }
    }
}
