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
public class Instruction {
    
    public Instruction(){
        
    }
    
    public Register getRegister(Process process, String registerName){
        Register register = null;
        switch(registerName){
            case "PC":
                register = process.getPCB().getPC();
                break;
            case "AC":
                register = process.getPCB().getAC();
                break;
            case "AX":
                register = process.getPCB().getAX();
                break;
            case "BX":
                register = process.getPCB().getBX();
                break;
            case "CX":
                register = process.getPCB().getCX();
                break;
            case "DX":
                register = process.getPCB().getDX();
                break;
            default:
                break;
        }
        return register;
    }
}
