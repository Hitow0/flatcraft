package fr.univartois.butinfo.r304.flatcraft.model.resources.breakingstate;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;

public class FifthBreakingState implements IBreakingState{
    @Override
    public IBreakingState changeState(Cell cellule, CellFactory cellFactory) {
        cellule.replaceBy(cellFactory.changeBreakingLevel(cellule, 5));
        return new BreakState();
    }
}
