package fr.univartois.butinfo.r304.flatcraft.model.craft;

import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;

import java.util.List;

public class Craft {

    /**
     * Le produit obtenu lors du craft
     */
    private final Resource product;

    /**
     * La quantité de produit obtenue
     */
    private final int quantity;

    /**
     * La liste des crafts possibles pour le produit
     */
    private List<Resource> crafts;

    /**
     * Création d'un nouveau craft
     * @param product : produit du craft
     * @param quantity : quantité de produit
     */
    public Craft(List<Resource> craft, Resource product, int quantity) {
        this.crafts = craft;
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Obtenir le produit
     * @return le produit crafté
     */
    public Resource getProduct() {
        return product;
    }

    /**
     * Obtenir la quantité retournée
     * @return la quantité
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Obtenir la liste des crafts possible pour le produit
     * @return la liste des crafts
     */
    public List<Resource> getCrafts() {
        return crafts;
    }
}
