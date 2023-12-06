package fr.univartois.butinfo.r304.flatcraft.model.resources.mineralstate;

import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.model.resources.mineralstate.FinalState;
import fr.univartois.butinfo.r304.flatcraft.model.resources.mineralstate.IStateResource;
import fr.univartois.butinfo.r304.flatcraft.view.SpriteStore;

public class MineralState implements IStateResource {

    @Override
    public IStateResource changeState(Resource resource) {
        if("stone".equals(resource.getName()))
            resource.setName("cobble");
        else {
            resource.setName(resource.getName().substring(8));
            if ("coal".equals(resource.getName()) || "iron".equals(resource.getName()) || "gold".equals(resource.getName()))
                resource.setName(resource.getName() + "_lump");
        }
        resource.setSprite(SpriteStore.getInstance().getSprite(resource.getName()));
        return new FinalState();
    }
}
