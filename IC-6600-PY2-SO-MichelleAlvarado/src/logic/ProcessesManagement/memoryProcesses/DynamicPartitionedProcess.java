/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.ProcessesManagement.memoryProcesses;
import java.util.ArrayList;
import logic.ProcessesManagement.Process;
import logic.memory.Register;
/**
 *
 * @author Michelle Alvarado
 */
public class DynamicPartitionedProcess extends Process {
    ArrayList<Register> allocatedMemory;
    
    public DynamicPartitionedProcess(ArrayList<String> instructions, String name, int processIDIndex){
        super(instructions, name, processIDIndex);
        this.allocatedMemory = new ArrayList<Register>();
    }
    
    @Override
    public void setAllocatedMemory(ArrayList<Register> memorySpaces){
        for(int i = 0; i < memorySpaces.size(); i++){
            Register register = memorySpaces.get(i);
            register.setRegisterValue(this.getProcessInstructions().get(i));
        }
        this.allocatedMemory = memorySpaces;
    }

    @Override
    public ArrayList<Register> getMemorySpaces() {
        return this.allocatedMemory;
    }
    
    public void setAllocatedProcess(){
        ArrayList<Register> allocatedMemory = new ArrayList<>();
    }
}
