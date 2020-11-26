/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.memoryManagement.DynamicPartition;

import java.util.ArrayList;
import logic.memory.Register;

/**
 *
 * @author Michelle Alvarado
 */
public interface IDynamicPartitionType {
    public ArrayList<Register> allocateProcessInMemory(logic.ProcessesManagement.Process process);
    
    public void freeUpProcessMemory(logic.ProcessesManagement.Process process);
}
