/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.schedulingAlgorithms;

import logic.ProcessesManagement.Process;

/**
 *
 * @author Michelle Alvarado
 */
public class FCFS extends Scheduler{
    
    public FCFS(){
       super(); 
    }

    @Override
    public int getCPUCycleRemainingTime(Process process) {
        return process.getProcessBurstTime();
    }

    @Override
    public Process getNextProcess() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
