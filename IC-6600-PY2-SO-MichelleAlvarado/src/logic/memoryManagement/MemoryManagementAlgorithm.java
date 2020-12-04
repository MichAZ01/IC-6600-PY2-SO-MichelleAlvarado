/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.memoryManagement;

import java.util.ArrayList;
import logic.ProcessesManagement.memoryProcesses.DynamicPartitionedProcess;
import logic.ProcessesManagement.Process;
import logic.memory.Register;
/**
 *
 * @author Michelle Alvarado
 */
public abstract class MemoryManagementAlgorithm {
    
    public MemoryManagementAlgorithm(){
        
    }
    
    public abstract void allocateProcessInMemory(Process process);
    
    public abstract void freeUpProcessMemory(Process process);
    
    public void setAllocatedProcess(Process process, String memoryType, ArrayList<Register> memoryRegisters){
        DynamicPartitionedProcess allocatedProcess = (DynamicPartitionedProcess) process;
            if(memoryType.equals("mainMemory")) allocatedProcess.getPCB().getPC().setRegisterValue(memoryRegisters.get(0).getRegisterAddress().getMemoryAddress());
            if(allocatedProcess.getPCB().getProcessStatus().getRegisterValue().equals("En espera") && memoryType.equals("mainMemory")){
                allocatedProcess.setAllocatedMemory(memoryRegisters);
                allocatedProcess.getPCB().getProcessStatus().setRegisterValue("Preparado");
            }
            else{
                allocatedProcess.setAllocatedMemory(memoryRegisters);
                if(memoryType.equals("mainMemory")) allocatedProcess.getPCB().getProcessStatus().setRegisterValue("Preparado");
                else allocatedProcess.getPCB().getProcessStatus().setRegisterValue("En espera");
            }
    }
}
