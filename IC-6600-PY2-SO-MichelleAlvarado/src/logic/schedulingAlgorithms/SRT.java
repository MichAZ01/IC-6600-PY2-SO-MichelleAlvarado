/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.schedulingAlgorithms;

import java.util.ArrayList;
import logic.ProcessesManagement.Process;
import logic.computer.Computer;
import logic.coresManagement.Core;

/**
 *
 * @author Michelle Alvarado
 */
public class SRT extends Scheduler {

    public SRT() {
        super();
    }

    @Override
    public int getCPUCycleRemainingTime(Process process) {
        return 1;
    }

    @Override
    public Process getNextProcess() {
        ArrayList<Process> executingProcesses = Computer.getInstance().getOS().getKernel().getProcessesManager().getCurrentExecutingProcesses();
        Process process = null;
        if (executingProcesses.size() > 0) {
            process = executingProcesses.get(0);
            Core core = Computer.getInstance().getCPU().getMyCoresManager().getCPUCores().get(process.getCoreWhereIsRuning());
            for (int i = 1; i < executingProcesses.size(); i++) {
                Process newProcess = executingProcesses.get(i);
                Core newCore = Computer.getInstance().getCPU().getMyCoresManager().getCPUCores().get(newProcess.getCoreWhereIsRuning());
                if (newCore.getCurrentProcessRemainingBurstTime() < core.getCurrentProcessRemainingBurstTime()) {
                    process = newProcess;
                }
            }
        }
        return process;
    }

}
