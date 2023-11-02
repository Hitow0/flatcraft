package fr.univartois.butinfo.r304.flatcraft.model.map;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.model.resources.ToolType;
import fr.univartois.butinfo.r304.flatcraft.view.ISpriteStore;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;

public class EndFactory implements CellFactory {
    private ISpriteStore spriteStore;
    @Override
    public void setSpriteStore(ISpriteStore spriteStore) {
        this.spriteStore=spriteStore;
    }

    @Override
    public Cell createSky() {
        return null;
    }

    @Override
    public Cell createSoilSurface() {
        Sprite sprite = spriteStore.getSprite("sandstone");
        Resource resource = new Resource("sand_stone", sprite, ToolType.NO_TOOL, 1);
        return new Cellule(resource);
    }

    @Override
    public Cell createSubSoil() {
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
        return null;
    }
}
