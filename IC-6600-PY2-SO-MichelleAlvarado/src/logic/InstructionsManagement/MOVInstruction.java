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
public class MOVInstruction extends Instruction implements IInstruction{
    private String instruction;
    
    public MOVInstruction(String instruction){
        super();
        this.instruction = instruction;
    }

    @Override
    public int execute(Process process) {
        int result = 0;
        
        String[] registers = instruction.split(",");
        
        String destinationRegisterName = registers[0].split(" ")[1].trim();
        String originRegisterName = registers[1].trim();
        
        Register destinationRegister = this.getRegister(process, destinationRegisterName);
        Register originRegister = this.getRegister(process, originRegisterName);
        
        destinationRegister.setRegisterValue(originRegister.getRegisterValue());
        
        return result;
    }
    
}
