package fr.univartois.butinfo.r304.flatcraft.model.craft;

import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.model.resources.ToolType;
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
        ArrayList<Resource> r = new ArrayList<>();
        for (String item : items) {
            if (!"empty".equals(item)) {
                r.add(new Resource(item, SpriteStore.getInstance().getSprite(item), null, 0));
            }
            else {
                r.add(null);
            }
        }
        this.recette = r;
    }

    @Override
    public void buildProduit(String product, int quantity) {
        this.produit = new Resource(product, SpriteStore.getInstance().getSprite(product), ToolType.NO_TOOL, 4);
        this.quantity=quantity;
    }

    @Override
    public void getResult() {
        ListRecette.getInstance().getCraftList().add(new Craft(recette, produit, quantity));
    }
}
