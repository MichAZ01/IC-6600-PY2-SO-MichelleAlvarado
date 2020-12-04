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
    private int CPUCurrentTime;
    private CoresManager myCoresManager;
    private boolean hasProcessesToExecute;
    
    public CPU(){
        this.CPUCurrentTime = 1;
        this.myCoresManager = new CoresManager();
        this.hasProcessesToExecute = false;
    }
    
    public void setHasProcessesToExecute(){
        this.hasProcessesToExecute = !this.hasProcessesToExecute;
    }

    public int getCPUCurrentTime() {
        return CPUCurrentTime;
    }

    public void setCPUCurrentTime() {
        this.CPUCurrentTime += 1;
    }
    
    
}
