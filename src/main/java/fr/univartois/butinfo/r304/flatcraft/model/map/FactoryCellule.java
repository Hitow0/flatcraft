package fr.univartois.butinfo.r304.flatcraft.model.map;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.view.ISpriteStore;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;
import fr.univartois.butinfo.r304.flatcraft.view.SpriteStore;
import javafx.scene.image.Image;

import java.awt.*;

public class FactoryCellule implements CellFactory {
    private ISpriteStore spriteStore;

    @Override
    public void setSpriteStore(ISpriteStore spriteStore) {
        this.spriteStore = spriteStore;
    }

    @Override
    public Cell createSky() {
        Sprite sprite = new Sprite(new Image("src/main/ressources/air.png"));
        return new Cellule(sprite);
    }

    @Override
    public Cell createSoilSurface() {
        return null;
    }

    @Override
    public Cell createSubSoil() {
        return null;
    }

    @Override
    public Cell createTrunk() {
        return null;
    }

    @Override
    public Cell createLeaves() {
        return null;
    }
}
