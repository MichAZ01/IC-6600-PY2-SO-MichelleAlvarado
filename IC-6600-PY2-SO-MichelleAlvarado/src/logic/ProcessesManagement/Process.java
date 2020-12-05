/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.ProcessesManagement;

import java.awt.Color;
import java.util.ArrayList;
import logic.memory.Register;

/**
 *
 * @author Michelle Alvarado
 */
public abstract class Process {

    private final ArrayList<String> processInstructions;
    private int processBurstTime;
    private PCB PCB;
    private String processName;
    private final String processID;
    private String arrivalHour;
    private String arrivalTime;
    private Color processColor;
    private double trValue;
    private double trTsValue;
    private String CPMFlag;
    private int currentProcessRemainingBurstTime;
    private int coreWhereIsRuning;
    private String initHour;
    private String finalHour;
    private int internalPC;
    private boolean isInMainMemory;
    private boolean isFinalized;

    public Process(ArrayList<String> instructions, String name, int processIDIndex) {
        this.processInstructions = instructions;
        this.processBurstTime = this.processInstructions.size();
        this.processName = name;
        this.arrivalHour = "";
        this.initHour = "";
        this.finalHour = "";
        this.arrivalTime = "";
        this.processID = "P-" + processIDIndex;
        this.trValue = -1;
        this.trTsValue = -1;
        this.CPMFlag = "-";
        this.processBurstTime = instructions.size();
        this.currentProcessRemainingBurstTime = instructions.size();
        this.arrivalTime = "-1";
        this.internalPC = 0;
        this.isInMainMemory = false;
        this.isFinalized = false;
    }

    public void setPCB(PCB PCB) {
        this.PCB = PCB;
    }

    public PCB getPCB() {
        return PCB;
    }

    public ArrayList<String> getProcessInstructions() {
        return processInstructions;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProcessID() {
        return processID;
    }

    public void setArrivalHour(String arrivalHour) {
        this.arrivalHour = arrivalHour;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getProcessName() {
        return processName;
    }
    
    public int getStackAvailableSpace(){
        int space = 0;
        for(int i = 0; i < this.PCB.getStack().size(); i++){
            if(this.PCB.getStack().get(i).getRegisterValue().equals("-")) space += 1;
        }
        return space;
    }

    public String getCPMFlag() {
        return CPMFlag;
    }

    public void setCPMFlag(String CPMFlag) {
        this.CPMFlag = CPMFlag;
    }

    public String getArrivalHour() {
        return arrivalHour;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public Color getProcessColor() {
        return processColor;
    }

    public void setProcessColor(Color processColor) {
        this.processColor = processColor;
    }
    
    public abstract void setAllocatedMemory(ArrayList<Register> memorySpaces);
    
    public abstract ArrayList<Register> getMemorySpaces();

    public int getCurrentProcessRemainingBurstTime() {
        return currentProcessRemainingBurstTime;
    }

    public void setCurrentProcessRemainingBurstTime(int currentProcessRemainingBurstTime) {
        this.currentProcessRemainingBurstTime = currentProcessRemainingBurstTime;
    }

    public int getCoreWhereIsRuning() {
        return coreWhereIsRuning;
    }

    public void setCoreWhereIsRuning(int coreWhereIsRuning) {
        this.coreWhereIsRuning = coreWhereIsRuning;
    }

    public String getInitHour() {
        return initHour;
    }

    public void setInitHour(String initHour) {
        this.initHour = initHour;
    }

    public String getFinalHour() {
        return finalHour;
    }

    public void setFinalHour(String finalHour) {
        this.finalHour = finalHour;
    }

    public int getProcessBurstTime() {
        return processBurstTime;
    }

    public double getTrValue() {
        return trValue;
    }

    public void setTrValue(double trValue) {
        this.trValue = trValue;
    }

    public double getTrTsValue() {
        return trTsValue;
    }

    public void setTrTsValue(double trTsValue) {
        this.trTsValue = trTsValue;
    }

    public void setProcessBurstTime(int processBurstTime) {
        this.processBurstTime = processBurstTime;
    }

    public int getInternalPC() {
        return internalPC;
    }

    public void setInternalPC() {
        this.internalPC += 1;
    }
    
    public void setInternalPC(int pc) {
        this.internalPC = pc;
    }

    public boolean isInMainMemory() {
        return isInMainMemory;
    }

    public void setIsInMainMemory(boolean isInMainMemory) {
        this.isInMainMemory = isInMainMemory;
    }

    public boolean isFinalized() {
        return isFinalized;
    }

    public void setIsFinalized(boolean isFinalized) {
        this.isFinalized = isFinalized;
    }
}
