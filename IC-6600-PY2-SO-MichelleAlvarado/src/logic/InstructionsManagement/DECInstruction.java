/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.InstructionsManagement;
import logic.ProcessesManagement.PCB;
import logic.ProcessesManagement.Process;

/**
 *
 * @author Michelle Alvarado
 */
public class DECInstruction implements IInstruction{
    private String instruction;
    
    public DECInstruction(String instruction){
        super();
        this.instruction = instruction;
    }

    @Override
    public int execute(Process process) {
        int result = 0;
        PCB currentPCB = process.getPCB();
        currentPCB.getAC().setRegisterValue(Integer.toString(Integer.parseInt(currentPCB.getAC().getRegisterValue()) - 1));
        return result;
    }
}
