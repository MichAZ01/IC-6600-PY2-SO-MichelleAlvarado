/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.InstructionsManagement;
import logic.ProcessesManagement.Process;
import logic.memory.Register;

/**
 *
 * @author Michelle Alvarado
 */
public class LOADInstruction extends Instruction implements IInstruction{
    private String instruction;
    
    public LOADInstruction(String instruction){
        super();
        this.instruction = instruction;
    }

    @Override
    public int execute(Process process) {
        int result = 0;
        Register register = this.getRegister(process, instruction.split(" ")[1]);
        process.getPCB().getAC().setRegisterValue(register.getRegisterValue());
        return result;
    }

}
