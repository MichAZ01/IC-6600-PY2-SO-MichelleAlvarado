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
    private ArrayList<Core> CPUCores;
    
    public CoresManager(){
        this.CPUCores = this.createCores(5);
    }
    
    public ArrayList<Core> createCores(int coresAmount){
        ArrayList<Core> cores = new ArrayList<Core>();
        
        for(int i = 0; i < coresAmount; i++){
            cores.add(new Core(i));
        }
        
        return cores;
    }

    public ArrayList<Core> getCPUCores() {
        return CPUCores;
    }
    
    public ArrayList<Core> getAvailableCores(){
        ArrayList<Core> cores = new ArrayList<>();
        for(Core core: this.CPUCores){
            if(core.getIsAvailable() == true) cores.add(core);
        }
        return cores;
    }
    
    public ArrayList<Core> getBusyCores(){
        ArrayList<Core> cores = new ArrayList<>();
        for(Core core: this.CPUCores){
            if(core.getIsAvailable() == false) cores.add(core);
        }
        return cores;
    }
}
