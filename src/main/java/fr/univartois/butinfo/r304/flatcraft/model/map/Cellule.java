package fr.univartois.butinfo.r304.flatcraft.model.map;

import fr.univartois.butinfo.r304.flatcraft.model.IMovable;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;

public class Cellule extends AbstractCell {
    protected Cellule(int row, int column) {
        super(row, column);
    }

    protected Cellule(Sprite sprite) {
        super(sprite);
    }

    protected Cellule(Resource resource) {
        super(resource);
    }

    @Override
    public boolean move(IMovable movable) {
        return false;
    }

    @Override
    public boolean dig(IMovable player) {
        return false;
    }
}
