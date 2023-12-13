package fr.univartois.butinfo.r304.flatcraft.model.listmap;

import fr.univartois.butinfo.r304.flatcraft.model.map.SimpleGameMap;
import fr.univartois.butinfo.r304.flatcraft.model.map.createmap.IGenMapStrat;

public class ListMap {
    protected static class AMap{

        private AMap before ;

        private final SimpleGameMap map;


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


    private final IGenMapStrat genMapStrat;

    private AMap actualMap;

    public ListMap(IGenMapStrat genMapStrat){
        this.genMapStrat=genMapStrat;



    }
    public void moveBefore(){
        if(actualMap.before==null) {
            actualMap.before = new AMap(genMapStrat.genMap());
            actualMap.before.after=actualMap;
        }
        actualMap=actualMap.before;

    }

    public void moveAfter(){
        if(actualMap.after==null) {
            actualMap.after = new AMap(genMapStrat.genMap());
            actualMap.after.before=actualMap;
        }
        actualMap=actualMap.after;

    }

    public SimpleGameMap getMap(){
        if(actualMap==null)
            actualMap= new AMap(genMapStrat.genMap());
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
