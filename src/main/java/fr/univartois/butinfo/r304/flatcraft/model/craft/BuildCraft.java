package fr.univartois.butinfo.r304.flatcraft.model.craft;

import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.view.SpriteStore;

import java.util.ArrayList;
import java.util.List;

public class BuildCraft implements  ICraftBuilder{
    private List<Resource> recette;
    private Resource produit;
    private int quantity;

    @Override
    public void buildRecette(String rule) {
        String[] items = rule.split("-");
        ArrayList<Resource> recette = new ArrayList<>();
        for (String item : items) {
            if (!"empty".equals(item)) {
                recette.add(new Resource(item, SpriteStore.getInstance().getSprite(item), null, 0));
            }
            else {
                recette.add(null);
            }
        }
        this.recette = recette;
    }

    @Override
    public void buildProduit(String product, int quantity) {
        this.produit = new Resource(product, SpriteStore.getInstance().getSprite(product), null, 0);
        this.quantity=quantity;
    }

    @Override
    public void getResult() {
        ListRecette.getInstance().getCraftList().add(new Craft(recette, produit, quantity));
    }
}
