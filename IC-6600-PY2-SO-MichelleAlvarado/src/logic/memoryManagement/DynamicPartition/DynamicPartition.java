/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.memoryManagement.DynamicPartition;

import java.util.ArrayList;
import java.util.List;
import logic.ProcessesManagement.Process;
import logic.computer.Computer;
import logic.memory.Memory;
import logic.memory.Register;
import logic.memoryManagement.IMemoryManagementAlgorithm;

/**
 *
 * @author Michelle Alvarado
 */
public class DynamicPartition implements IMemoryManagementAlgorithm{
    private String dynamicPartitionUbicationType;
    public int mainMemoryLastIndex;
    public int secondaryMemoryLastIndex;
    public IDynamicPartitionType partitionUbicationAlgorithm;
    
    public DynamicPartition(String ubicationType){
        this.dynamicPartitionUbicationType = ubicationType;
        this.mainMemoryLastIndex = 0;
        this.secondaryMemoryLastIndex = 0;
        this.setPartitionType();
    }

    @Override
    public void allocateProcessInMemory(Process process) {
        //ArrayList<Register> memoryRegisters = 
    }

    @Override
    public void freeUpProcessMemory(Process process) {
        
    }
    
    public void setPartitionType(){
        switch(this.dynamicPartitionUbicationType){
            case "BestFit":
                //this.partitionUbicationAlgorithm = 
                break;
            case "FirstFit":
                this.partitionUbicationAlgorithm = new FirstFit();
                break;
            case "NextFit":
                //memoryRegisters = this.getFreeMemoryByNextFit(process);
                break;
            default:
                break;
        }
    }
    
}
