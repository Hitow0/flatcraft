package fr.univartois.butinfo.r304.flatcraft.model.map.createMap;

import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.map.SimpleGameMap;

public class GenMapStrat1 implements IGenMapStrat{

    public SimpleGameMap genMap(int height, int width, CellFactory cell){
        SimpleGameMap map = new SimpleGameMap(height, width, height / 2);
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                if(i<map.getSoilHeight())
                    map.setAt(i,j,cell.createSky());
                if(i==map.getSoilHeight())
                    map.setAt(i,j, cell.createSoilSurface());
                if(i> map.getSoilHeight())
                    map.setAt(i,j,cell.createSubSoil());
            }
        }
        return map;
    }
}
