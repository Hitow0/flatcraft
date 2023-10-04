package fr.univartois.butinfo.r304.flatcraft.model.map;

import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;

public class GenerateMap {

    public SimpleGameMap genMap(int height, int width, CellFactory cell){
        SimpleGameMap map = new SimpleGameMap(height, width, height / 2);
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                if(i>map.getSoilHeight())
                    map.setAt(i,j,cell.createSky());
                if(i==map.getSoilHeight())
                    map.setAt(i,j, cell.createSoilSurface());
                if(i< map.getSoilHeight())
                    map.setAt(i,j,cell.createSubSoil());
            }
        }
        return map;
    }
}
