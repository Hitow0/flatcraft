package fr.univartois.butinfo.r304.flatcraft.model.craft;

import java.util.ArrayList;
import java.util.List;

public class ListRecette {

    private List<Craft> craftList;

    private  List<Cook> cookList;

    private static ListRecette instance;

    private ListRecette(){
        this.cookList = new ArrayList<>();
        this.craftList = new ArrayList<>();
    }

    public static ListRecette getInstance(){
        if (instance == null){
            instance = new ListRecette();
        }
        return instance;
    }

    public List<Craft> getCraftList() {
        return craftList;
    }

    public List<Cook> getCookList() {
        return cookList;
    }
}

