/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.computer;

import logic.OSManagement.CPU;
import logic.OSManagement.OperatingSystem;
import logic.ProcessesManagement.ProcessesManager;
import logic.memory.MemoryManager;

/**
 *
 * @author Michelle Alvarado
 */
public class Computer {
    private static Computer myComputer = null;
    private MemoryManager memoryManager;
    private CPU myCPU;
    private OperatingSystem myOS;
    
    private Computer(){
        this.memoryManager = new MemoryManager();
        myCPU = new CPU();
        myOS = new OperatingSystem();
    }
    
    public static Computer getInstance(){
        if(myComputer == null){
            myComputer = new Computer();
        }
        return myComputer;
    }
    
    public CPU getCPU(){
        return myCPU;
    }
    
    public OperatingSystem getOS(){
        return myOS;
    }

    public MemoryManager getMemoryManager() {
        return memoryManager;
    }
   
}
