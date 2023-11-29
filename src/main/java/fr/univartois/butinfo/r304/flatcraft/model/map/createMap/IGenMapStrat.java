package fr.univartois.butinfo.r304.flatcraft.model.map.createMap;

import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.map.SimpleGameMap;

public interface IGenMapStrat {
    public void genMap(int height, int width, CellFactory cell);
    public SimpleGameMap getMap();
}
