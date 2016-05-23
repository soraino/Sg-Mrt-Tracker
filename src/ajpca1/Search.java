/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajpca1;

import java.util.*;
import java.util.Map.Entry;

/**
 *
 * @author Darren
 */
public class Search {
    
    private String StationName = "";
    private String TrainCode   = "";
    private String TrainLine   = "";
    
    
    
    public Search(){
        
    }
    
    
    
    public String GetLine(){
        return TrainLine;
    }
    public String GetCode(){
        return TrainCode;
    }
    public String GetStationName(){
       return StationName;
    }
    
    
    public boolean SearchTrain(String search){
        
        boolean r = false;
        
        Trains t = new Trains();
        t.SetHashTable();
        Set<String> links = t.GetHashTable().keySet();
        int interchanges = 0;
        /**
         * interchange will be the indicator if the station has more than one line
        * string key represents the Line Name
        */

        for( String key : links){
            Collection<String> value = t.GetTrainHashMap(key).values();
            /**
             * String train represents the station name
             */
            for(String train : value){
                
                if (train.equalsIgnoreCase(search)){
                    if(interchanges == 0){
                    r = true;
                    TrainLine   = key;
                    StationName = train;
                    TrainCode   = (String) getKeyByValue(t.GetTrainHashMap(key), train);
                    interchanges ++;
                    }
                    else if(interchanges >0){
                        r = true;
                        TrainLine   +=" / "+ key;
                        TrainCode   +=" / "+ (String) getKeyByValue(t.GetTrainHashMap(key), train);
                        interchanges ++;
                    }
                }
                
            }
            if(r == false){
                Set<String>yay = t.GetTrainHashMap(key).keySet();
                for(String train : yay){
                    if(train.equalsIgnoreCase(search)){
                        r = true;
                        TrainCode   = train;
                        TrainLine   = key;
                        StationName = (String)t.GetTrainHashMap(key).get(train);
                        
                    }
                }
            }
        }

        return r;
    }
     public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
         }
        }
        return null;
    }
}
