package fr.univartois.butinfo.r304.flatcraft.model.resources;

import fr.univartois.butinfo.r304.flatcraft.view.SpriteStore;

public class MineralState implements IStateResource{


    // IL FAUT RETOURNER UN MINERALSTATE OU UN INVENTORYSTATE SELON L ETAT ACTUEL
    @Override
    public Resource changeState(Resource resource) {
        if (resource.getName().endsWith("_ingot")){
            resource.setName("mineral_" + resource.getName().substring(resource.getName().length()-6,resource.getName().length()));
            resource.setSprite(SpriteStore.getInstance().getSprite(resource.getName()));
        }

    }
}
