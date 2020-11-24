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
public class POPInstruction extends Instruction implements IInstruction{
    private String instruction;
    
    public POPInstruction(String instruction){
        super();
        this.instruction = instruction;
    }

    @Override
    public int execute(Process process) {
        int result = 0;
        Register register = this.getRegister(process, instruction.split(" ")[1]);
        String POPResult = this.POPStack(process.getPCB().getStack());
        if(POPResult != ""){
            register.setRegisterValue(POPResult);
        } else {
            result = 2;
        }
        return result;
    }
    
    public String POPStack(ArrayList<Register> stack){
        String result = "";
        for(int i = 4; i >= 0; i--){
            if(!stack.get(i).getRegisterValue().equals("00000000")){
                result = stack.get(i).getRegisterValue();
                stack.get(i).setRegisterValue("00000000");
                break;
            }
        }
        return result;
    }
}
