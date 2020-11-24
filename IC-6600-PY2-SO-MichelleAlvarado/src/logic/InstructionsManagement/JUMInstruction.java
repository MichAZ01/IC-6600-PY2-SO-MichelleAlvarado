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
public class JUMInstruction implements IInstruction{
    private String instruction;
    
    public JUMInstruction(String instruction){
        this.instruction = instruction;
    }

    @Override
    public int execute(Process process) {
        int result = 0;
        
        return result;
    }
    
    public int getJumpNumber(){
        String jumpValue = this.instruction.split(" ")[1];
        int jumpNumber = 0;
        
        if(jumpValue.substring(0, 1) == "-"){
            jumpNumber = Integer.parseInt(jumpValue.substring(1)) * - 1;
        } else {
            jumpNumber = Integer.parseInt(jumpValue.substring(1));
        }
        return jumpNumber;
    }
    
    public boolean jumpIsValid(Process process){
        boolean jumpIsValid = true;
        
        
        return jumpIsValid;
    }
}
