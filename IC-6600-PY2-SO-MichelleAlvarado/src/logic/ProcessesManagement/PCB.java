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
    private Register IOStatus;
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
    private static final int processIDIndex = 0;
    private static final int processStatusIndex = 1;
    private static final int IOStatusIndex = 2;
    private static final int PCIndex = 3;
    private static final int ACIndex = 4;
    private static final int AXIndex = 5;
    private static final int BXIndex = 6;
    private static final int CXIndex = 7;
    private static final int DXIndex = 8;
    private static final int stackInitIndex = 9;
    private static final int coreWhereIsRunningIndex = 14;
    private static final int initTimeIndex = 15;
    private static final int finalTimeIndex = 16;
    private static final int initProgramIndexIndex = 17;
    private static final int processLengthIndex = 18;
    
    public PCB(String processStatus, int processLength){
        this.processStatus = new Register();
        this.processStatus.setRegisterValue(processStatus);
        this.processLength = new Register();
        this.processLength.setRegisterValue(Integer.toString(processLength));
        this.processID = new Register();
        this.stack = new ArrayList<Register>(5);
    }
    
    public void setPCBRegisters(ArrayList<Register> registers){
        registers.get(processIDIndex).setRegisterValue(this.processID.getRegisterValue());
        registers.get(processStatusIndex).setRegisterValue(this.processStatus.getRegisterValue());
        registers.get(processLengthIndex).setRegisterValue(this.processLength.getRegisterValue());
        this.processID = registers.get(processIDIndex);
        this.processStatus = registers.get(processStatusIndex);
        this.IOStatus = registers.get(IOStatusIndex);
        this.IOStatus.setRegisterValue("0000");
        this.PC = registers.get(PCIndex);
        this.AC = registers.get(ACIndex);
        this.AC.setRegisterValue("0");
        this.AX = registers.get(AXIndex);
        this.AX.setRegisterValue("0");
        this.BX = registers.get(BXIndex);
        this.BX.setRegisterValue("0");
        this.CX = registers.get(CXIndex);
        this.CX.setRegisterValue("0");
        this.DX = registers.get(DXIndex);
        this.DX.setRegisterValue("0");
        this.storeStackRegisters(registers);
        this.coreWhereIsRunning = registers.get(coreWhereIsRunningIndex);
        this.initTime = registers.get(initTimeIndex);
        this.initTime.setRegisterValue("-1");
        this.finalTime = registers.get(finalTimeIndex);
        this.finalTime.setRegisterValue("-1");
        this.initProgramIndex = registers.get(initProgramIndexIndex);
        this.processLength = registers.get(processLengthIndex);
    }
    
    public void storeStackRegisters(ArrayList<Register> registers){
        int stackFinalIndex = stackInitIndex + 4;
        for(int i = stackInitIndex; i <= stackFinalIndex; i++){
            registers.get(i).setRegisterValue("00000000");
            this.stack.add(registers.get(i));
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

    public Register getIOStatus() {
        return IOStatus;
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
