package fr.univartois.butinfo.r304.flatcraft.model.map.decorator;

import fr.univartois.butinfo.r304.flatcraft.model.CellFactory;
import fr.univartois.butinfo.r304.flatcraft.model.map.createMap.IGenMapStrat;

import java.util.Random;

public class DecoSlagHeap extends DecoratorMap{
    private int MAX_HEIGHT=8;

    private int height;
    private int posX;

    public DecoSlagHeap(IGenMapStrat genMapStrat) {
        super(genMapStrat);
        Random ran=new Random();
        height=ran.nextInt(1,MAX_HEIGHT);
        posX=ran.nextInt(1,getMap().getWidth()-1);
        posX=2;
        height=4;
    }

    public void genSlagHeap(CellFactory cell){
        int posI=this.height;
        int slagHeapWhith=1;

        int height=getMap().getHeight();
        int width=getMap().getWidth();
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                if(i==getMap().getSoilHeight()-this.height && j==posX){

                    for(int k=0;k<this.height;k++){
                        if(slagHeapWhith==1) {
                            getMap().setAt(i, j, cell.createSoilSurface());
                            while(getMap().getAt(i,j).getResource().getName()=="water"){
                                getMap().setAt(i, j, cell.createSoilSurface());
                            }
                        }
                        else{
                            getMap().setAt(getMap().getSoilHeight()-posI, posX,cell.createSubSoil());
                            if(posI==1){
                                getMap().setAt(getMap().getSoilHeight(), posX,cell.createSubSoil());

                            }
                            if(i-slagHeapWhith>0){
                                for(int l=0;l<slagHeapWhith;l++){
                                    if(posX-l>=0  && getMap().getAt(getMap().getSoilHeight()-posI-1, posX-l).getResource()==null) {
                                        getMap().setAt(getMap().getSoilHeight() - posI, posX - l, cell.createSoilSurface());
                                        while(getMap().getAt(getMap().getSoilHeight() - posI, posX - l).getResource().getName()=="water"){
                                            getMap().setAt(getMap().getSoilHeight() - posI, posX - l, cell.createSoilSurface());
                                        }
                                    }else
                                        getMap().setAt(getMap().getSoilHeight() - posI, posX - l, cell.createSubSoil());
                                    if(posI==1){
                                        getMap().setAt(getMap().getSoilHeight(), posX-l,cell.createSubSoil());

                                    }
                                }
                            }
                            if(i+slagHeapWhith<width){
                                for(int l=1;l<slagHeapWhith;l++){
                                    if(getMap().getAt(getMap().getSoilHeight()-posI-1, posX+l).getResource()==null){
                                        getMap().setAt(getMap().getSoilHeight()-posI, posX+l,cell.createSoilSurface());
                                        while (getMap().getAt(getMap().getSoilHeight()-posI, posX+l).getResource().getName()=="water"){
                                            getMap().setAt(getMap().getSoilHeight()-posI, posX+l,cell.createSoilSurface());
                                        }
                                    }else
                                        getMap().setAt(getMap().getSoilHeight()-posI, posX+l,cell.createSubSoil());
                                    if(posI==1){
                                        getMap().setAt(getMap().getSoilHeight(), posX+l,cell.createSubSoil());

                                    }
                                }


                            }

                        }
                        posI=posI-1;
                        slagHeapWhith++;
                    }
                }
            }
        }

    }
}
