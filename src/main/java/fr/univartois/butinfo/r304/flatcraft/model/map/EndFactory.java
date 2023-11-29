package fr.univartois.butinfo.r304.flatcraft.model.map;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.model.resources.ToolType;
import fr.univartois.butinfo.r304.flatcraft.view.ISpriteStore;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;

public class EndFactory implements CellFactory {
    private ISpriteStore spriteStore;
    private static EndFactory instance;

    private EndFactory(){

    }

    public static EndFactory getInstance(){
        if (instance == null){
            instance = new EndFactory();
        }
        return instance;
    }

    @Override
    public void setSpriteStore(ISpriteStore spriteStore) {
        this.spriteStore=spriteStore;
    }

    @Override
    public Cell createSky() {
        Sprite sprite = spriteStore.getSprite("coal_block");
        return new Cellule(sprite);
    }

    @Override
    public Cell createSoilSurface() {
        Sprite sprite = spriteStore.getSprite("sandstone");
        Resource resource = new Resource("sand_stone", sprite, ToolType.NO_TOOL, 1);
        return new Cellule(resource);
    }

    @Override
    public Cell createSubSoil(int surfaceSoilHeight, int j) {
        Sprite sprite = spriteStore.getSprite("sandstone");
        Resource resource = new Resource("sand_stone", sprite, ToolType.NO_TOOL, 1);
        return new Cellule(resource);
    }

    @Override
    public Cell createTrunk() {
        Sprite sprite = spriteStore.getSprite("obsidian");
        Resource resource = new Resource("obsidian", sprite, ToolType.NO_TOOL, 1);
        return new Cellule(resource);
    }

    @Override
    public Cell createLeaves() {
        Sprite sprite = spriteStore.getSprite("coal_block");
        return new Cellule(sprite);
    }

    @Override
    public Cell changeBreakingLevel(Cell toDig, int breakingLevel) {
        Sprite ressource = Resource.fusionSprite(toDig.getSprite(), Resource.obtenirNiveauCassage(breakingLevel));
        Resource resource = new Resource(toDig.getResource().getName(), ressource, toDig.getResource().getToolType(), toDig.getResource().getHardness());
        return new Cellule(resource);
    }
}
