/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.OSManagement;
import java.util.ArrayList;
import logic.ProcessesManagement.Process;
import logic.computer.Computer;
import logic.memory.Register;
/**
 *
 * @author Michelle Alvarado
 */
public class Loader {
    
    public Loader(){
        
    }
    
    public void allocateProcessesInMemory(ArrayList<Process> currentWaitingProcesses){
        ArrayList<Process> readyProcesses = new ArrayList<>();
        for (Process currentWaitingProcess : currentWaitingProcesses) {
            Computer.getInstance().getOS().getKernel().getMemoryManagementAlgorithm().allocateProcessInMemory(currentWaitingProcess);
            if(currentWaitingProcess.getPCB().getProcessStatus().getRegisterValue().equals("Preparado")){
                Computer.getInstance().getOS().getKernel().getProcessesManager().addProcessTocurrentReadyProcesses(currentWaitingProcess);
                readyProcesses.add(currentWaitingProcess);
            }
        }
        Computer.getInstance().getOS().getKernel().getProcessesManager().cleanCurrenWaitingProcesses(readyProcesses);
    }
}
