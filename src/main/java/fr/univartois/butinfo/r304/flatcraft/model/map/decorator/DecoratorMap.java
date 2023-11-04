package fr.univartois.butinfo.r304.flatcraft.model.map.decorator;

import fr.univartois.butinfo.r304.flatcraft.model.map.SimpleGameMap;
import fr.univartois.butinfo.r304.flatcraft.model.map.createMap.IGenMapStrat;

public abstract class DecoratorMap {
    private IGenMapStrat genMapStrat;

    public DecoratorMap(IGenMapStrat genMapStrat){
        this.genMapStrat=genMapStrat;
    }

    public SimpleGameMap getMap(){
        return genMapStrat.getMap();
    }
}
