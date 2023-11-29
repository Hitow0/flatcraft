package fr.univartois.butinfo.r304.flatcraft.model.resources.mineralstate;

import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.view.SpriteStore;

public class FinalState implements IStateResource {

    @Override
    public IStateResource changeState(Resource resource) {
        resource.setSprite(SpriteStore.getInstance().getSprite(resource.getName()));
        return this;
    }
}
