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
public class ADDInstruction extends Instruction implements IInstruction{
    private String instruction;
    
    public ADDInstruction(String instruction){
        super();
        this.instruction = instruction;
    }

    @Override
    public int execute(Process process) {
        int result = 0;
        Register register = this.getRegister(process, instruction.split(" ")[1]);
        String sum = Integer.toString(Integer.parseInt(process.getPCB().getAC().getRegisterValue()) + Integer.parseInt(register.getRegisterValue()));
        process.getPCB().getAC().setRegisterValue(sum);
        return result;
    }
}
