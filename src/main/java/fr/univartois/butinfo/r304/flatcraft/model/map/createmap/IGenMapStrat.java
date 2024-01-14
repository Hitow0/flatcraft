package fr.univartois.butinfo.r304.flatcraft.model.map.createmap;

import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.map.SimpleGameMap;

public interface IGenMapStrat {
    public void setCell(CellFactory cell);
    public void setWidth(int width);
    public void setHeight(int height);
    public SimpleGameMap genMap();
    public SimpleGameMap getMap();
    public SimpleGameMap getAfterMap();
    public SimpleGameMap getBeforeMap();
    public void mapMoveLeft();
    public void mapMoveRight();
    public SimpleGameMap getAfterAMap();
    public SimpleGameMap getBeforeAMap();
}
