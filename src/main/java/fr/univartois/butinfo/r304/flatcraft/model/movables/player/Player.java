package fr.univartois.butinfo.r304.flatcraft.model.movables.player;

import fr.univartois.butinfo.r304.flatcraft.model.FlatcraftGame;
import fr.univartois.butinfo.r304.flatcraft.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;
import fr.univartois.butinfo.r304.flatcraft.model.resources.ToolType;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;
import fr.univartois.butinfo.r304.flatcraft.view.SpriteStore;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class Player extends AbstractMovable {

    private final IntegerProperty pv;
    private final IntegerProperty xp;
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

    public void addObject(Resource r){
        if(inventaire.containsKey(r)){
            inventaire.replace(r,inventaire.get(r)+1);
        }
        else {
            inventaire.put(r,1);
        }
    }

    public void addObject(Resource r, Integer q){
        if(inventaire.containsKey(r)){
            inventaire.replace(r,inventaire.get(r)+q);
        }
        else {
            inventaire.put(r,q);
        }
    }

    public void removeResource(Resource r){
        if(inventaire.containsKey(r)) {
            if(inventaire.get(r)==1){
                inventaire.remove(r);
            } else {
                inventaire.replace(r,inventaire.get(r)-1);
            }
        }
    }

    public Optional<Resource> getInventoryResourceByName(String name){
        AtomicReference<Optional<Resource>> result = new AtomicReference<>(Optional.empty());
        inventaire.forEach((resource, integer) -> {
            if(resource.getName().equals(name)){
                result.set(Optional.of(resource));
            }
        });
        return result.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Player player = (Player) o;
        return Objects.equals(pv, player.pv) && Objects.equals(xp, player.xp) && Objects.equals(inventaire, player.inventaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pv, xp, inventaire);
    }
}
