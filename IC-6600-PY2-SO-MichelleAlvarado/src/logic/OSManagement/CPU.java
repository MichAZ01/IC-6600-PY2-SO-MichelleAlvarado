/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.OSManagement;

import logic.coresManagement.CoresManager;

/**
 *
 * @author Michelle Alvarado
 */
public class CPU {
    private int CPUCurrentTime;
    private final CoresManager myCoresManager;
    private boolean hasProcessesToExecute;
    
    public CPU(){
        this.CPUCurrentTime = 1;
        this.myCoresManager = new CoresManager();
        this.hasProcessesToExecute = false;
    }
    
    public void setHasProcessesToExecute(boolean processesToExecute){
        this.hasProcessesToExecute = processesToExecute;
    }

    public boolean getHasProcessesToExecute() {
        return hasProcessesToExecute;
    }

    public int getCPUCurrentTime() {
        return CPUCurrentTime;
    }

    public void setCPUCurrentTime() {
        this.CPUCurrentTime += 1;
    }

    public CoresManager getMyCoresManager() {
        return myCoresManager;
    }
}
