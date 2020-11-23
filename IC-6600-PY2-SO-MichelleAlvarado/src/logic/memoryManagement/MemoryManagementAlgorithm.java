/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.memoryManagement;

import java.util.ArrayList;
import logic.memory.Register;
import logic.ProcessesManagement.Process;

/**
 *
 * @author Michelle Alvarado
 */
public interface MemoryManagementAlgorithm {
    
    public ArrayList<Register> allocateProcessInMemory(Process process);
    
    public void freeUpProcessMemory(Process process);
}
