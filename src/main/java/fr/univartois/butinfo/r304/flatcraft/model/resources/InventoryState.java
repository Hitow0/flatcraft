package fr.univartois.butinfo.r304.flatcraft.model.resources;

import fr.univartois.butinfo.r304.flatcraft.view.SpriteStore;

public class InventoryState implements IStateResource{


    @Override
    public Resource changeState(Resource resource) {
        if (resource.getName().startsWith("mineral_")){
            resource.setName(resource.getName().substring(0,8)+"_ingot");
            resource.setSprite(SpriteStore.getInstance().getSprite(resource.getName()));
        }
    }
}
