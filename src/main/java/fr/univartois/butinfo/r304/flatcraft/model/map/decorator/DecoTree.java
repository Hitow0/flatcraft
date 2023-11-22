package fr.univartois.butinfo.r304.flatcraft.model.map.decorator;

import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.map.createMap.IGenMapStrat;
import fr.univartois.butinfo.r304.flatcraft.view.SpriteStore;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class DecoTree extends DecoratorMap{
    private int NB_TREE=6;
    private int MAX_TREE_HEIGHT=5;

    private int nbTree;

    public DecoTree(IGenMapStrat genMapStrat) {
        super(genMapStrat);
        Random ran=new Random();
        nbTree=ran.nextInt(0,NB_TREE);
    }


    public void genTree(CellFactory cell){

        Random ran=new Random();
        int height= getMap().getHeight();
        int width= getMap().getWidth();

        for(int nb=0;nb<nbTree;nb++){
            boolean found=false;
            int posX= ran.nextInt(1,width-1);


            while (!found){
                if(!Objects.equals(getMap().getAt(getMap().getSoilHeight(), posX).getSprite(), SpriteStore.getInstance().getSprite("water"))){
                    if(getMap().getAt(getMap().getSoilHeight()+1,posX).getResource()!=null){
                        int l= getMap().getSoilHeight()+1;
                        while (getMap().getAt(l,posX).getResource()!=null){
                            l=l-1;
                        }
                        if(getMap().getAt(l+1,posX).getResource().getName()!="pine_needles" && getMap().getAt(l+1,posX).getResource().getName()!="pine_tree"){
                            if(getMap().getAt(l+1,posX+1).getResource()!=null && getMap().getAt(l+1,posX+1).getResource().getName()!="pine_tree"){
                                if(getMap().getAt(l+1,posX-1).getResource()!=null && getMap().getAt(l+1,posX-1).getResource().getName()!="pine_tree"){
                                    found=true;
                                }else{
                                    posX=ran.nextInt(1,width-1);
                                }
                            }else{
                                posX=ran.nextInt(1,width-1);
                            }

                        }else{
                            posX=ran.nextInt(1,width-1);
                        }
                    }else{
                        found=true;
                    }
                }else{
                    posX=ran.nextInt(1,width-1);
                }
            }


            int treeHeight= ran.nextInt(1,MAX_TREE_HEIGHT);
            for(int i=0;i<height;i++){
                for(int j=0;j<width;j++){
                    if(i==getMap().getSoilHeight() && j==posX){
                        int posI=i;
                        while (getMap().getAt(posI,j).getResource()!=null){
                            posI=posI-1;
                            if(posI<0){
                                return;
                            }
                        }
                        for (int k=0;k<treeHeight;k++){
                            getMap().setAt(posI-k,posX,cell.createTrunk());
                            if(k==treeHeight-1){
                                if(getMap().getAt(posI-k-1,posX).getResource()==null)
                                     getMap().setAt(posI-k-1,posX,cell.createLeaves());
                                if(getMap().getAt(posI-k,posX+1).getResource()==null)
                                    getMap().setAt(posI-k,posX+1,cell.createLeaves());
                                if(getMap().getAt(posI-k,posX-1).getResource()==null)
                                    getMap().setAt(posI-k,posX-1,cell.createLeaves());


                            }
                        }



                    }
                }
            }


        }
    }
}
