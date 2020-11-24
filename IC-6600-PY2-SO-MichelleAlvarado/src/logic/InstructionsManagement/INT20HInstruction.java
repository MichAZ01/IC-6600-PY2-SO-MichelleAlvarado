/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.InstructionsManagement;
import logic.ProcessesManagement.Process;

/**
 *
 * @author Michelle Alvarado
 */
public class INT20HInstruction implements IInstruction{
    private String instruction;
    
    public INT20HInstruction(String instruction){
        this.instruction = instruction;
    }

    @Override
    public int execute(Process process) {
        int result = 0;
        
        return result;
    }
}
