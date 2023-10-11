package fr.univartois.butinfo.r304.flatcraft.model.movables.player;

import fr.univartois.butinfo.r304.flatcraft.model.FlatcraftGame;
import fr.univartois.butinfo.r304.flatcraft.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Player extends AbstractMovable {

    private IntegerProperty pv;
    private IntegerProperty xp;
    private ObservableMap<Resource, Integer> inventaire;

    public Player(FlatcraftGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
        this.inventaire = FXCollections.observableHashMap();
        this.pv = new SimpleIntegerProperty(10);
        this.xp = new SimpleIntegerProperty(0);
    }

    public int getPv() {
        return pv.get();
    }

    public IntegerProperty pvProperty() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv.set(pv);
    }

    public int getXp() {
        return xp.get();
    }

    public IntegerProperty xpProperty() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp.set(xp);
    }

    public ObservableMap<Resource, Integer> getInventaire() {
        return inventaire;
    }

    public void setInventaire(ObservableMap<Resource, Integer> inventaire) {
        this.inventaire = inventaire;
    }

    /*
    SI IL Y A UN PB DANS L'INVENTAIRE C'EST MA FAUTE
     */

    public void addObject(Resource k){
        Integer v = this.inventaire.get(k);
        this.inventaire.replace(k,v+1);
    }

    public void removeResource(Resource k){
        Integer v = this.inventaire.get(k);
        this.inventaire.replace(k,v-1);
        this.inventaire.remove(k,0);
    }
}
