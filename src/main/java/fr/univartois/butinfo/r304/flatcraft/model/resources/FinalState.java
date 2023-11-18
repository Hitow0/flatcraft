package fr.univartois.butinfo.r304.flatcraft.model.resources;

import com.sun.prism.PresentableState;
import fr.univartois.butinfo.r304.flatcraft.view.SpriteStore;

public class FinalState implements IStateResource{


    @Override
    public IStateResource changeState(Resource resource) {
        return this;
    }
}
