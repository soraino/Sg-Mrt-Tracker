/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajpca1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Darren
 */
public class Trains extends ModeOfTransport<Object, Object>{
    
    private HashMap<String, String> KeretaApi1 = new HashMap<String , String>();
    private HashMap<String, String> KeretaApi2 = new HashMap<String , String>();
    private HashMap<String, String> KeretaApi3 = new HashMap<String , String>();
    private HashMap<String, String> KeretaApi4 = new HashMap<String , String>();
    private HashMap<String, String> KeretaApi5 = new HashMap<String , String>();
    private Set<String> trainNames;
    
    private Hashtable<String, HashMap<String, String>> wow = new Hashtable<String , HashMap<String, String>>();
    
    
    
    public Trains(){
        
    }
       
    public HashMap GetTrainHashMap(String Name){
        
        return wow.get(Name);
        
    }
    public ArrayList<String> GetTrainName(){
        ArrayList<String> TrainNames = new ArrayList<>(trainNames);
        return TrainNames;
    }
   
    public void SetHashTable(){
        try{
        FileReader fr = new FileReader("MRT.txt");
        BufferedReader br = new BufferedReader(fr);
        String reads = "";
        
        int interval = 1;
        String lineCode = "";
        String LineName = ""; 
        trainNames = new TreeSet<String>();
        while((reads = br.readLine()) != null){
            
            if( reads.equals("(start)") != true && reads.equals("(end)") != true){
                
                if(interval %2 != 0){
                    lineCode = reads.trim();
                    
                    if(lineCode.contains("CC")){
                        LineName = "Circle Line";
                    }
                    else if(lineCode.contains("DT")){
                        LineName= "Down Town Line";
                    }
                    else if(lineCode.contains("EW")){
                        LineName= "East West Line";
                    }
                    else if(lineCode.contains("NS")){
                        LineName = "North South Line";
                    }
                    else if(lineCode.contains("CG")){
                        LineName = "East West Line";
                    }
                    else if (lineCode.contains("NE")){
                        LineName = "North East Line";
                    }
                    else if(lineCode.contains("CE")){
                        LineName = "Circle Line";
                    }
                    
                    interval++;
                }
                
                else{
                    if(LineName.equals("Circle Line"))
                        KeretaApi1.put(lineCode, reads);
                    else if(LineName.equals("Down Town Line"))
                        KeretaApi2.put(lineCode, reads);
                    else if(LineName.equals("East West Line"))
                        KeretaApi3.put(lineCode, reads);
                    else if(LineName.equals("North South Line"))
                        KeretaApi4.put(lineCode, reads);
                    else if(LineName.equals("North East Line"))
                        KeretaApi5.put(lineCode, reads);
                    trainNames.add(reads);
                        
                        
                    interval++;
                }
                
                
                
            }
            
            else if(reads.equals("(end)")){
                if(LineName.equals("Circle Line"))
                        wow.put(LineName, this.KeretaApi1);
                else if(LineName.equals("Down Town Line"))
                        wow.put(LineName, this.KeretaApi2);
                else if(LineName.equals("East West Line"))
                        wow.put(LineName, this.KeretaApi3);
                else if(LineName.equals("North South Line"))
                        wow.put(LineName, this.KeretaApi4);
                else if(LineName.equals("North East Line"))
                        wow.put(LineName, this.KeretaApi5);

                }
            }
        super.SetHashTable(wow);
        }
        catch(IOException e){
            System.err.printf("Error");
        }
    }
    
    public Hashtable GetHashTable(){
        return wow;
    }
    
    
}
