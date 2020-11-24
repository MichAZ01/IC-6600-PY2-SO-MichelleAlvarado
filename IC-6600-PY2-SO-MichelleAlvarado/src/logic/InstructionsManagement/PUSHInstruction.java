/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.InstructionsManagement;
import java.util.ArrayList;
import logic.ProcessesManagement.Process;
import logic.memory.Register;

/**
 *
 * @author Michelle Alvarado
 */
public class PUSHInstruction extends Instruction implements IInstruction{
    private String instruction;
    
    public PUSHInstruction(String instruction){
        super();
        this.instruction = instruction;
    }

    @Override
    public int execute(Process process) {
        int result = 0;
        Register register = this.getRegister(process, instruction.split(" ")[1]);
        if(process.getStackAvailableSpace() >= 1){
            this.PUSHStack(register.getRegisterValue(), process.getPCB().getStack());
        } else {
            return 1;
        }
        return result;
    }
    
    
    
    public void PUSHStack(String value, ArrayList<Register> stack){
        for(int i = 0; i < stack.size(); i++){
            if(stack.get(i).getRegisterValue().equals("00000000")){
                stack.get(i).setRegisterValue(value);
                break;
            }
        }
    }
}
