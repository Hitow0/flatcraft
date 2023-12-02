package fr.univartois.butinfo.r304.flatcraft.model.map.createMap;

import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.listMap.ListMap;
import fr.univartois.butinfo.r304.flatcraft.model.map.SimpleGameMap;

public class GenMapStrat1 implements IGenMapStrat{
    private static GenMapStrat1 instance;

    private ListMap listMap;

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

    private GenMapStrat1() {
        listMap=new ListMap(this);
    }

    public static GenMapStrat1 getInstance() {
        if (instance == null)
            instance = new GenMapStrat1();
        return instance;
    }

    public SimpleGameMap genMap(){
             SimpleGameMap map = new SimpleGameMap(height, width, height / 2);
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
        return listMap.getMap();
    }

    public void mapMoveLeft(){
        listMap.MoveBefore();
    }

    public void mapMoveRight(){
        listMap.MoveAfter();
    }
}
