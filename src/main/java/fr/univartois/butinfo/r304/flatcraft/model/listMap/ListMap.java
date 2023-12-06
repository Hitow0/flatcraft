package fr.univartois.butinfo.r304.flatcraft.model.listMap;

import java.util.Random;

import fr.univartois.butinfo.r304.flatcraft.model.map.SimpleGameMap;
import fr.univartois.butinfo.r304.flatcraft.model.map.createMap.GenMapStrat1;
import fr.univartois.butinfo.r304.flatcraft.model.map.createMap.IGenMapStrat;

public class ListMap {
    protected class AMap{

        private AMap before ;

        private SimpleGameMap map;


        private AMap after;

        protected AMap(SimpleGameMap map){
            before=null;
            after=null;
            this.map=map;

        }

        protected SimpleGameMap getMap(){
            return map;
        }





    }


    private IGenMapStrat genMapStrat;

    private AMap actualMap;

    public ListMap(IGenMapStrat genMapStrat){
        this.genMapStrat=genMapStrat;



    }
    public void MoveBefore(){
        if(actualMap.before==null) {
            actualMap.before = new AMap(genMapStrat.genMap());
            actualMap.before.after=actualMap;
        }
        actualMap=actualMap.before;

    }

    public void MoveAfter(){
        if(actualMap.after==null) {
            actualMap.after = new AMap(genMapStrat.genMap());
            actualMap.after.before=actualMap;
        }
        actualMap=actualMap.after;

    }

    public SimpleGameMap getMap(){
        if(actualMap==null)
            actualMap=new AMap(genMapStrat.genMap());
        return actualMap.getMap();

    }

    public SimpleGameMap getBeforeMap(){
        if(actualMap.before==null) {
            actualMap.before = new AMap(genMapStrat.genMap());
            actualMap.before.after=actualMap;
        }
        return actualMap.before.getMap();
    }

    public SimpleGameMap getAfterMap(){
        if(actualMap.after==null) {
            actualMap.after = new AMap(genMapStrat.genMap());
            actualMap.after.before=actualMap;
        }
        return actualMap.after.getMap();
    }

}
