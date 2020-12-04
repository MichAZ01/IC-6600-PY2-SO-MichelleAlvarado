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
    
    public void allocateNewProcessesInMemory(){
        ArrayList<Process> newProcesses = Computer.getInstance().getOS().getKernel().getProcessesManager().getNewProcesses();
        ArrayList<Process> allocatedProcesses = new ArrayList<>();
        for (Process newProcess : newProcesses) {
            Computer.getInstance().getOS().getKernel().getMemoryManagementAlgorithm().allocateProcessInMemory(newProcess);
            if(newProcess.getPCB().getProcessStatus().getRegisterValue().equals("Preparado")){
                Computer.getInstance().getOS().getKernel().getProcessesManager().addProcessTocurrentReadyProcesses(newProcess);
                allocatedProcesses.add(newProcess);
            }
            if(newProcess.getPCB().getProcessStatus().getRegisterValue().equals("En espera")){
                Computer.getInstance().getOS().getKernel().getProcessesManager().addProcessTocurrentWaitingProcesses(newProcess);
                allocatedProcesses.add(newProcess);
            }
        }
        Computer.getInstance().getOS().getKernel().getProcessesManager().cleanNewProcesses(allocatedProcesses);
    }
    
    public void allocateWaitingProcessesInMemory(){
        ArrayList<Process> allocatedProcesses = new ArrayList<>();
        ArrayList<Process> waitingProcesses = Computer.getInstance().getOS().getKernel().getProcessesManager().getCurrentWaitingProcesses();
        for(Process process: waitingProcesses){
            Computer.getInstance().getOS().getKernel().getMemoryManagementAlgorithm().allocateProcessInMemory(process);
            
            if(process.getPCB().getProcessStatus().getRegisterValue().equals("Preparado")){
                Computer.getInstance().getOS().getKernel().getProcessesManager().addProcessTocurrentReadyProcesses(process);
                allocatedProcesses.add(process);
            }
        }
        Computer.getInstance().getOS().getKernel().getProcessesManager().cleanWaitingProcesses(allocatedProcesses);
    }
}
