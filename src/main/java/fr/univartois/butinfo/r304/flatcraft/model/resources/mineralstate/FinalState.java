package fr.univartois.butinfo.r304.flatcraft.model.resources.mineralstate;

import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;

public class FinalState implements IStateResource {

    @Override
    public IStateResource changeState(Resource resource) {
        return this;
    }
}
