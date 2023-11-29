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
            Resource resource = new Resource("grass", sprite, ToolType.NO_TOOL, 2);
            return new Cellule(resource);
        }
        return new Cellule(spriteStore.getSprite("water"));
    }



    @Override
    public Cell createSubSoil(int surfaceSoilHeight, int j) {
        Random r = new Random();
        if(j<surfaceSoilHeight+surfaceSoilHeight*0.10 || j<=surfaceSoilHeight){
            Sprite sprite = spriteStore.getSprite("dirt");
            Resource resource = new Resource("dirt", sprite, ToolType.NO_TOOL, 2);
            return new Cellule(resource);
        }
        if(j<surfaceSoilHeight+surfaceSoilHeight*0.15){
            int n =r.nextInt(100);
            if (n<50){
                Sprite sprite = spriteStore.getSprite("dirt");
                Resource resource = new Resource("dirt", sprite, ToolType.NO_TOOL, 2);
                return new Cellule(resource);
            }else {
                Sprite sprite = spriteStore.getSprite("stone");
                Resource resource = new Resource("stone", sprite, ToolType.MEDIUM_TOOL, 5);
                return new Cellule(resource);
            }
        }
        if(j<surfaceSoilHeight+surfaceSoilHeight*0.60){
            int n=r.nextInt(100);
            if (n<4) {
                Sprite sprite = spriteStore.getSprite("stone");
                Sprite spriteCoal = spriteStore.getSprite("mineral_coal");
                Sprite ressource = Resource.fusionSprite(sprite, spriteCoal);
                Resource resource = new Resource("mineral_coal", ressource, ToolType.MEDIUM_TOOL, 5);
                return new Cellule(resource);
            }
            if (n<7) {
                Sprite sprite = spriteStore.getSprite("stone");
                Sprite spriteIron = spriteStore.getSprite("mineral_iron");
                Sprite ressource = Resource.fusionSprite(sprite, spriteIron);
                Resource resource = new Resource("mineral_iron", ressource, ToolType.MEDIUM_TOOL, 5);
                return new Cellule(resource);
            }else {
                Sprite sprite = spriteStore.getSprite("stone");
                Resource resource = new Resource("stone", sprite, ToolType.MEDIUM_TOOL, 5);
                return new Cellule(resource);
            }
        }else {
            int n=r.nextInt(100);
            if (n<1) {
                Sprite sprite = spriteStore.getSprite("stone");
                Sprite spriteDiamond = spriteStore.getSprite("mineral_diamond");
                Sprite ressource = Resource.fusionSprite(sprite, spriteDiamond);
                Resource resource = new Resource("mineral_diamond", ressource, ToolType.NO_TOOL, 5);
                return new Cellule(resource);

            }
            if (n<7) {
                Sprite sprite = spriteStore.getSprite("stone");
                Sprite spriteIron = spriteStore.getSprite("mineral_iron");
                Sprite ressource = Resource.fusionSprite(sprite, spriteIron);
                Resource resource = new Resource("mineral_iron", ressource, ToolType.MEDIUM_TOOL, 5);
                return new Cellule(resource);
            }else{
                Sprite sprite = spriteStore.getSprite("stone");
                Resource resource = new Resource("stone", sprite, ToolType.MEDIUM_TOOL, 5);
                return new Cellule(resource);
            }
        }
    }

    @Override
    public Cell createTrunk() {
        Sprite sprite = spriteStore.getSprite("pine_tree");
        Resource resource = new Resource("pine_tree", sprite, ToolType.NO_TOOL, 5);
        return new Cellule(resource);
    }

    @Override
    public Cell createLeaves() {
        Sprite sprite = spriteStore.getSprite("ice");
        Sprite spriteLeaves = spriteStore.getSprite("pine_needles");
        Sprite ressource = Resource.fusionSprite(sprite, spriteLeaves);
        Resource resource = new Resource("pine_needles", ressource, ToolType.NO_TOOL, 5);
        return new Cellule(resource);
    }

    @Override
    public Cell changeBreakingLevel(Cell toDig, int breakingLevel) {
        Sprite ressource = Resource.fusionSprite(toDig.getSprite(), Resource.obtenirNiveauCassage(breakingLevel));
        Resource resource = new Resource(toDig.getResource().getName(), ressource, toDig.getResource().getToolType(), toDig.getResource().getHardness());
        return new Cellule(resource);
    }

}

