/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.OSManagement;

import java.util.ArrayList;
import logic.ProcessesManagement.ProcessManager;
import logic.coresManagement.Core;
import logic.memory.MemoryManager;

/**
 *
 * @author Michelle Alvarado
 */
public class CPU {
    private static CPU myCPU = null;
    private int CPUCurrentTime;
    private MemoryManager memoryManager;
    private ProcessManager processesManager;
    private ArrayList<Core> CPUCores;
    
    public CPU(){
        this.CPUCurrentTime = 0;
        this.memoryManager = new MemoryManager();
        this.processesManager = new ProcessManager();
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

    public ProcessManager getProcessesManager() {
        return processesManager;
    }
}
