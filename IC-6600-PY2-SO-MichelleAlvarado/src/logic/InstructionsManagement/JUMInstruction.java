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
public class JUMInstruction implements Instruction{
    private String instruction;
    
    public JUMInstruction(String instruction){
        this.instruction = instruction;
    }

    @Override
    public void execute(Process process) {
        
    }
}
