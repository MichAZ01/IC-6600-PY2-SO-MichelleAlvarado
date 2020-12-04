/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.computer;

import logic.OSManagement.CPU;
import logic.OSManagement.OperatingSystem;
import logic.memory.MemoryManager;

/**
 *
 * @author Michelle Alvarado
 */
public class Computer {
    private static Computer myComputer = null;
    private final MemoryManager memoryManager;
    private final CPU myCPU;
    private final OperatingSystem myOS;
    
    private Computer(){
        myOS = new OperatingSystem();
        this.memoryManager = new MemoryManager();
        myCPU = new CPU();
    }
    
    public static Computer getInstance() {
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
   
    public void cleanComputer(){
        myComputer = new Computer();
    }
}
