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
public class CMPInstruction extends Instruction implements IInstruction{
    private String instruction;
    
    public CMPInstruction(String instruction){
        this.instruction = instruction;
    }

    @Override
    public int execute(Process process) {
        int result = 0;
        
        String[] registers = instruction.split(",");
        
        String val1Name = registers[0].split(" ")[1].trim();
        String val2Name = registers[1].trim();
        
        Register val1Register = this.getRegister(process, val1Name);
        Register val2Register = this.getRegister(process, val2Name);
        
        if(Integer.parseInt(val1Register.getRegisterValue()) == Integer.parseInt(val2Register.getRegisterValue())){
            result = 3;
            process.setCPMFlag("0");
        } else {
            result = 4;
            process.setCPMFlag("1");
        }
        
        return result;
    }
}
