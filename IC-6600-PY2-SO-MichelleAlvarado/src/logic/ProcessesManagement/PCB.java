/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.ProcessesManagement;

import java.util.ArrayList;
import logic.memory.Register;

/**
 *
 * @author Michelle Alvarado
 */
public class PCB {
        private Register processID;
    private Register processStatus;
    private Register PC;
    private Register AC;
    private Register AX;
    private Register BX;
    private Register CX;
    private Register DX;
    private ArrayList<Register> stack;
    private Register coreWhereIsRunning;
    private Register initTime;
    private Register finalTime;
    private Register initProgramIndex;
    private Register processLength;
    
    public PCB(String processStatus, int processLength){
        this.processStatus = new Register();
        this.processStatus.setRegisterValue(processStatus);
        this.processLength = new Register();
        this.processLength.setRegisterValue(Integer.toString(processLength));
        this.processID = new Register();
        this.stack = new ArrayList<Register>(5);
        this.setStackRegisters();
        this.PC = new Register();
        this.AC = new Register();
        this.AC.setRegisterValue("0");
        this.AX = new Register();
        this.AX.setRegisterValue("0");
        this.BX = new Register();
        this.BX.setRegisterValue("0");
        this.CX = new Register();
        this.CX.setRegisterValue("0");
        this.DX = new Register();
        this.DX.setRegisterValue("0");
        this.coreWhereIsRunning = new Register();
        this.initTime = new Register();
        this.initTime.setRegisterValue("-1");
        this.finalTime = new Register();
        this.finalTime.setRegisterValue("-1");
        this.initProgramIndex = new Register();
    }
    
    public void setStackRegisters(){
        for(int i = 0; i < 5; i++){
            Register stackRegister = new Register();
            stackRegister.setRegisterValue("00000000");
            this.stack.add(stackRegister);
        }
    }
    
    public Register getProcessStatus() {
        return processStatus;
    }

    public Register getProcessLength() {
        return processLength;
    }

    public Register getProcessID() {
        return processID;
    }

    public Register getInitProgramIndex() {
        return initProgramIndex;
    }

    public Register getPC() {
        return PC;
    }

    public Register getCoreWhereIsRunning() {
        return coreWhereIsRunning;
    }

    public Register getAC() {
        return AC;
    }

    public Register getAX() {
        return AX;
    }

    public Register getBX() {
        return BX;
    }

    public Register getCX() {
        return CX;
    }

    public Register getDX() {
        return DX;
    }

    public ArrayList<Register> getStack() {
        return stack;
    }

    public Register getInitTime() {
        return initTime;
    }

    public Register getFinalTime() {
        return finalTime;
    }
    
    
}
