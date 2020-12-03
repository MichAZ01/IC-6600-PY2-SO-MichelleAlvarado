/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.InstructionsManagement;
import java.util.ArrayList;
import logic.ProcessesManagement.PCB;
import logic.ProcessesManagement.Process;
import logic.memory.Register;

/**
 *
 * @author Michelle Alvarado
 */
public class PARAMInstruction implements IInstruction{
    private final String instruction;
    
    public PARAMInstruction(String instruction){
        super();
        this.instruction = instruction;
    }

    @Override
    public int execute(Process process) {
        int result = 0;
        String[] instructionParts = this.instruction.split(",");
        String param1 = instructionParts[0].split(" ")[1];
        ArrayList<String> params = new ArrayList<>();
        params.add(param1.trim());
        PCB currentPCB = process.getPCB();
        if (instructionParts.length == 3) {
            params.add(instructionParts[1].trim());
            params.add(instructionParts[2].trim());
        } else if (instructionParts.length == 2) {
            params.add(instructionParts[1].trim());
        }

        if (process.getStackAvailableSpace() >= params.size()) {
            this.storeParamsIntoStack(params, currentPCB.getStack());
        } else {
            result = 1;
        }
        return result;
    }
    
    public void storeParamsIntoStack(ArrayList<String> params, ArrayList<Register> stack){
        for(int i = 0; i < stack.size(); i++){
            if(stack.get(i).getRegisterValue().equals("-")){
                int x = i;
                for(int j = 0; j < params.size(); j++){
                    stack.get(x).setRegisterValue(params.get(j));
                    x +=1;
                }
                break;
            }
        }
    }
}
