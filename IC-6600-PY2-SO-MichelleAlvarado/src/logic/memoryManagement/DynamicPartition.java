/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.memoryManagement;

import java.util.ArrayList;
import java.util.List;
import logic.ProcessesManagement.Process;
import logic.ProcessesManagement.memoryProcesses.DynamicPartitionedProcess;
import logic.computer.Computer;
import logic.memory.Memory;
import logic.memory.Register;

/**
 *
 * @author Michelle Alvarado
 */
public class DynamicPartition extends MemoryManagementAlgorithm{
    
    public DynamicPartition(){
    }

    @Override
    public void allocateProcessInMemory(Process process) {
        ArrayList<Register> memoryRegisters = new ArrayList<>();
        String memoryType = "";
        int requiredSpace = process.getProcessInstructions().size();
        if(Computer.getInstance().getMemoryManager().getMainMemory().getFreeSpaces() >= requiredSpace){
            memoryRegisters = this.getMemorySpaces(Computer.getInstance().getMemoryManager().getMainMemory(), requiredSpace);
            Computer.getInstance().getMemoryManager().getMainMemory().setFreeSpaces(Computer.getInstance().getMemoryManager().getMainMemory().getFreeSpaces() - memoryRegisters.size());
            memoryType = "mainMemory";
        }
        else if(Computer.getInstance().getMemoryManager().getSecondaryMemory().getFreeSpaces() >= requiredSpace){
            memoryRegisters = this.getMemorySpaces(Computer.getInstance().getMemoryManager().getSecondaryMemory(), requiredSpace);
            Computer.getInstance().getMemoryManager().getSecondaryMemory().setFreeSpaces(Computer.getInstance().getMemoryManager().getSecondaryMemory().getFreeSpaces() - memoryRegisters.size());
            memoryType = "secondaryMemory";
        }
        
        if(memoryRegisters.size() > 0){
            this.setAllocatedProcess(process, memoryType, memoryRegisters);
        }
    }
    
    public ArrayList<Register> getMemorySpaces(Memory memory, int requiredSpace){
        ArrayList<Register> memorySpaces = new ArrayList<Register>();
        int currentSpaceIndex = 0;
        int currentFinalIndex = requiredSpace;
        boolean hasSpace = true;
        while(currentSpaceIndex < memory.getMemoryLength() && currentFinalIndex <= memory.getMemoryLength()){
            List registersList = memory.getMemoryRegisters().subList(currentSpaceIndex, currentFinalIndex);
            ArrayList<Register> registers = new ArrayList<>(registersList.size());
            registers.addAll(registersList);
            for(int i = 0; i < registers.size(); i++){
                if(!registers.get(i).getRegisterValue().equals("-")){
                    currentSpaceIndex += 1;
                    hasSpace = false;
                }
            }
            if(hasSpace){
                memorySpaces = registers;
                break;
            }
            currentFinalIndex = currentSpaceIndex + requiredSpace;
            hasSpace = true;
        }
        
        
        return memorySpaces;
    }

    @Override
    public void freeUpProcessMemory(Process process) {
        DynamicPartitionedProcess allocatedProcess = (DynamicPartitionedProcess) process;
        ArrayList<Register> memorySpaces = process.getMemorySpaces();
        for(Register register: memorySpaces){
            register.setRegisterValue("-");
        }
        allocatedProcess.setAllocatedProcess();
    }
    
}
