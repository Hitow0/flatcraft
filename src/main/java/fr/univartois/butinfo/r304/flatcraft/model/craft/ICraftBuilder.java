package fr.univartois.butinfo.r304.flatcraft.model.craft;


public interface ICraftBuilder {
    void buildRecette(String rule);
    void buildProduit( String product, int quantity);

    void getResult();
}
