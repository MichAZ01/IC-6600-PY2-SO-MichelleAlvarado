/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.schedulingAlgorithms;

import java.util.ArrayList;
import logic.ProcessesManagement.Process;
import logic.computer.Computer;

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
        Process process = null;
        ArrayList<Process> executingProcesses = Computer.getInstance().getOS().getKernel().getProcessesManager().getCurrentExecutingProcesses();
        if(executingProcesses.size() > 0) {
            process = executingProcesses.get(0);
            Computer.getInstance().getOS().getKernel().getProcessesManager().getCurrentExecutingProcesses().remove(process);
        }
        return process;
    }
}
