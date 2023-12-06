package fr.univartois.butinfo.r304.flatcraft.model.craft;

import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.view.SpriteStore;

import java.sql.ResultSet;
import java.util.Optional;

public class BuildCook implements ICraftBuilder{

    Resource resource;
    Resource produit;

    @Override
    public void buildRecette(String rule) {
        this.resource = new Resource(rule, SpriteStore.getInstance().getSprite(rule),null, 0);
    }

    @Override
    public void buildProduit(String product, int quantity) {
        this.produit = new Resource(product, SpriteStore.getInstance().getSprite(product),null,0 );
    }

    @Override
    public void getResult(){
         ListRecette.getInstance().getCookList().add(new Cook(this.resource,this.produit));
    }

}
