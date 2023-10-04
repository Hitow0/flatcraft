package fr.univartois.butinfo.r304.flatcraft.model.movables.player;

import fr.univartois.butinfo.r304.flatcraft.model.FlatcraftGame;
import fr.univartois.butinfo.r304.flatcraft.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Player extends AbstractMovable {

    private IntegerProperty pv;
    private IntegerProperty xp;
    private ObservableMap<Resource, Integer> inventaire;

    public Player(FlatcraftGame game, double xPosition, double yPosition, Sprite sprite, IntegerProperty pv, IntegerProperty xp) {
        super(game, xPosition, yPosition, sprite);
        this.pv = pv;
        this.xp = xp;
        this.inventaire = FXCollections.observableHashMap();
    }
}
