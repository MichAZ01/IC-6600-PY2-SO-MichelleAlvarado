/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.coresManagement;

import java.util.ArrayList;

/**
 *
 * @author Michelle Alvarado
 */
public class CoresManager {
    
    public CoresManager(){
    
    }
    
    public ArrayList<Core> createCores(int coresAmount){
        ArrayList<Core> cores = new ArrayList<Core>();
        
        for(int i = 0; i < coresAmount; i++){
            cores.add(new Core());
        }
        
        return cores;
    }
    
}
