/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.coresManagement;

import logic.memory.Register;
import logic.ProcessesManagement.Process;
/**
 *
 * @author Michelle Alvarado
 */
public class Core {
    private int coreIndex;
    private Register currentProcessID;
    private Register IRRegister;
    private boolean isAvailable;
    private int currentProcessRemainingBurstTime;
    private int CPUCycleRemainingTime;
    private Process currentProcess;
    
    public Core(int i){
        this.currentProcessID = new Register();
        this.IRRegister = new Register();
        this.isAvailable = true;
        this.currentProcessRemainingBurstTime = 0;
        this.CPUCycleRemainingTime = 1;
        this.coreIndex = i;
    }

    public Register getCurrentProcessID() {
        return currentProcessID;
    }

    public void setCurrentProcessID(Register currentProcessID) {
        this.currentProcessID = currentProcessID;
    }

    public Register getIRRegister() {
        return IRRegister;
    }

    public void setIRRegister(Register IRRegister) {
        this.IRRegister = IRRegister;
    }
    
    public void setIsAvailable(){
        this.isAvailable = !this.isAvailable;
    }
    
    public boolean getIsAvailable(){
        return this.isAvailable;
    }

    public int getCurrentProcessRemainingBurstTime() {
        return currentProcessRemainingBurstTime;
    }

    public void setCurrentProcessRemainingBurstTime(int currentProcessRemainingBurstTime) {
        this.currentProcessRemainingBurstTime = currentProcessRemainingBurstTime;
    }

    public Process getCurrentProcess() {
        return currentProcess;
    }

    public void setCurrentProcess(Process currentProcess) {
        this.currentProcess = currentProcess;
    }

    public int getCoreIndex() {
        return coreIndex;
    }

    public int getCPUCycleRemainingTime() {
        return CPUCycleRemainingTime;
    }

    public void setCPUCycleRemainingTime(int CPUCycleRemainingTime) {
        this.CPUCycleRemainingTime = CPUCycleRemainingTime;
    }
    
    public void setCPUCycleRemainingTime(){
        this.CPUCycleRemainingTime -=1;
        this.currentProcessRemainingBurstTime -= 1;
    }
    
}
