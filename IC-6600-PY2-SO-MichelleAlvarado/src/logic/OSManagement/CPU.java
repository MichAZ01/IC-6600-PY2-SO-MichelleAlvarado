/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.OSManagement;

import java.util.ArrayList;
import logic.ProcessesManagement.ProcessesManager;
import logic.coresManagement.Core;
import logic.coresManagement.CoresManager;
import logic.memory.MemoryManager;

/**
 *
 * @author Michelle Alvarado
 */
public class CPU {
    private static CPU myCPU = null;
    private int CPUCurrentTime;
    
    private ArrayList<Core> CPUCores;
    
    private CPU(){
        this.CPUCurrentTime = 0;
        this.CPUCores = new CoresManager().createCores(5);
    }
    
    /**
     * Singleton method
     * @return
     * @throws IOException 
     */
    public static CPU getInstance(){
        if(myCPU == null){
            myCPU = new CPU();
        }
        return myCPU;
    }
}
