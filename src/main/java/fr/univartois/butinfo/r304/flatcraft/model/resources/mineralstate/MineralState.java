package fr.univartois.butinfo.r304.flatcraft.model.resources.mineralstate;

import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.model.resources.mineralstate.FinalState;
import fr.univartois.butinfo.r304.flatcraft.model.resources.mineralstate.IStateResource;
import fr.univartois.butinfo.r304.flatcraft.view.SpriteStore;

public class MineralState implements IStateResource {

    @Override
    public IStateResource changeState(Resource resource) {
        if (resource.getName().endsWith("_ingot")){
            resource.setName("mineral_" + resource.getName().substring(resource.getName().length()-6,resource.getName().length()));
            resource.setSprite(SpriteStore.getInstance().getSprite(resource.getName()));
        }
        return new FinalState();
    }
}
