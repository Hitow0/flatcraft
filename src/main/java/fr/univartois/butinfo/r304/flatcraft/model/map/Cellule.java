package fr.univartois.butinfo.r304.flatcraft.model.map;

import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.FlatcraftGame;
import fr.univartois.butinfo.r304.flatcraft.model.IMovable;
import fr.univartois.butinfo.r304.flatcraft.model.movables.player.Player;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.model.resources.breakingstate.BasicState;
import fr.univartois.butinfo.r304.flatcraft.model.resources.breakingstate.BreakState;
import fr.univartois.butinfo.r304.flatcraft.model.resources.breakingstate.FirstBreakingState;
import fr.univartois.butinfo.r304.flatcraft.model.resources.breakingstate.IBreakingState;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;
import fr.univartois.butinfo.r304.flatcraft.view.SpriteStore;

public class Cellule extends AbstractCell {

    IBreakingState breakingState;

    public Cellule(int row, int column) {
        super(row, column);
        this.breakingState = new FirstBreakingState();
    }

    public Cellule(Sprite sprite) {
        super(sprite);
        this.breakingState = new BreakState();
    }

    public Cellule(Resource resource) {
        super(resource);
        this.breakingState = new BasicState();
    }

    @Override
    public boolean move(IMovable movable) {
        if (this.getResource()==null) {
            movable.setX(this.getColumn()*this.getSprite().getWidth());
            movable.setY(this.getRow()*this.getSprite().getHeight());
            return true;
        }
        return false;
    }

    @Override
    public void dig(IMovable player) {
        Resource resource = getResource();
        if (resource!=null) {
            if (resource.getHardness()!=0) {
                resource.dig();
            } else {
                ((Player) player).addObject(this.getResource().digBlock());
                this.breakingState = new BreakState();
            }
            this.breakingState = breakingState.changeState(this, FlatcraftGame.getCellFactory());
        }
    }
}
