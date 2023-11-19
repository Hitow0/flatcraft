package fr.univartois.butinfo.r304.flatcraft.model.resources.breakingstate;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.map.Cellule;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;

public class ThirdBreakingState implements IBreakingState{
    @Override
    public IBreakingState changeState(Cell cellule, CellFactory cellFactory) {
        cellule.replaceBy(cellFactory.changeBreakingLevel(cellule, 3));
        return new FourthBreakingState();
    }
}
