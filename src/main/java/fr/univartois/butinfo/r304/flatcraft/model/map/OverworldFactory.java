package fr.univartois.butinfo.r304.flatcraft.model.map;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.model.resources.ToolType;
import fr.univartois.butinfo.r304.flatcraft.view.ISpriteStore;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;

import java.util.Random;

public class OverworldFactory implements CellFactory {
    private ISpriteStore spriteStore;

    private static OverworldFactory instance;


    private OverworldFactory(){

    }

    public static OverworldFactory getInstance(){
        if (instance == null){
            instance = new OverworldFactory();
        }
        return instance;
    }

    @Override
    public void setSpriteStore(ISpriteStore spriteStore) {
        this.spriteStore = spriteStore;
    }

    @Override
    public Cell createSky() {
        Random r = new Random();
        int n = r.nextInt(100);
        if (n<3) {
            Sprite sprite = spriteStore.getSprite("cloud");
            return new Cellule(sprite);
        }
        Sprite sprite = spriteStore.getSprite("ice");
        return new Cellule(sprite);
    }

    @Override
    public Cell createSoilSurface() {
        Random r = new Random();
        int n = r.nextInt(5);
        if (n<=3) {
            Sprite sprite = spriteStore.getSprite("grass");
            Resource resource = new Resource("grass", sprite, ToolType.NO_TOOL, 1);
            return new Cellule(resource);
        }
        Sprite sprite = spriteStore.getSprite("water");
        Resource resource = new Resource("water", sprite, ToolType.NO_TOOL, 1);
        return new Cellule(resource);
    }

    @Override
    public Cell createSubSoil() {
        Random r = new Random();
        int n = r.nextInt(100);
        if (n<2) {
            Sprite sprite = spriteStore.getSprite("dirt");
            Resource resource = new Resource("dirt", sprite, ToolType.NO_TOOL, 5);
            return new Cellule(resource);
        }
        if (n<8) {
            Sprite sprite = spriteStore.getSprite("mineral_coal");
            Resource resource = new Resource("mineral_coal", sprite, ToolType.MEDIUM_TOOL, 10);
            return new Cellule(resource);
        }
        if (n<12) {
            Sprite sprite = spriteStore.getSprite("mineral_iron");
            Resource resource = new Resource("mineral_iron", sprite, ToolType.MEDIUM_TOOL, 20);
            return new Cellule(resource);
        }
        if (n<15) {
            Sprite sprite = spriteStore.getSprite("mineral_diamond");
            Resource resource = new Resource("mineral_diamond", sprite, ToolType.NO_TOOL, 25);
            return new Cellule(resource);
        }
        Sprite sprite = spriteStore.getSprite("stone");
        Resource resource = new Resource("stone", sprite, ToolType.MEDIUM_TOOL, 7);
        return new Cellule(resource);
//        Sprite sprite = spriteStore.getSprite("dirt");
//        Resource resource = new Resource("dirt", sprite, ToolType.NO_TOOL, 5);
//        return new Cellule(resource);
    }

    @Override
    public Cell createTrunk() {
        Sprite sprite = spriteStore.getSprite("pine_tree");
        Resource resource = new Resource("pine_tree", sprite, ToolType.NO_TOOL, 7);
        return new Cellule(resource);
    }

    @Override
    public Cell createLeaves() {
        Sprite sprite = spriteStore.getSprite("pine_needles");
        Resource resource = new Resource("pine_needles", sprite, ToolType.NO_TOOL, 2);
        return new Cellule(resource);
    }
}

