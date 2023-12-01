package fr.univartois.butinfo.r304.flatcraft.model.craft;

import fr.univartois.butinfo.r304.flatcraft.model.resources.Resource;

public class Cook {
    /**
     * L'objet utilisé lors de la cuisson
     */
    private Resource ingredient;

    /**
     * Le produit de la cuisson
     */
    private Resource produit;

    /**
     * Crée une recette de cuisson
     * @param ingredient L'objet à cuire
     * @param produit L'objet après la cuisson
     */
    public Cook(Resource ingredient, Resource produit) {
        this.ingredient = ingredient;
        this.produit = produit;
    }

    /**
     * Retourne l'objet à cuire
     * @return l'objet à cuire
     */
    public Resource getIngredient() {
        return ingredient;
    }

    /**
     * Retourne l'objet après la cuisson
     * @return l'objet après la cuisson
     */
    public Resource getProduit() {
        return produit;
    }
}
