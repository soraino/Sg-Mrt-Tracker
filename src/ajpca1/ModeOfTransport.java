/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajpca1;

import java.util.*;

/**
 *
 * @author Darren
 */
public class ModeOfTransport<k,v> {
    
    private Hashtable<k,v> Hash = new  Hashtable<k,v>();
    
    public void SetHashTable(Hashtable t){
        Hash = t;
    }
    public Hashtable<k,v> GetHashTable(){
        return Hash;
    }
    
}
