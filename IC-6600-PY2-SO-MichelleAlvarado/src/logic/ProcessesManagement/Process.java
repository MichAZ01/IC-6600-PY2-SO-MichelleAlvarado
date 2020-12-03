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

    private ArrayList<String> processInstructions;
    private int processBurstTime;
    private PCB PCB;
    private boolean processIsCorrect;
    private String processName;
    private String processID;
    private String arrivalHour;
    private String arrivalTime;
    private Color processColor;
    private double trValue;
    private double trTsValue;
    private boolean hasArrivalTime;
    private String CPMFlag;
    private ArrayList<Register> memorySpacesIndexes;

    public Process(ArrayList<String> instructions, String name, int processIDIndex) {
        this.processInstructions = instructions;
        this.processBurstTime = this.processInstructions.size();
        this.processIsCorrect = false;
        this.processName = name;
        this.arrivalHour = "";
        this.arrivalTime = "";
        this.processID = "P-" + processIDIndex;
        this.trValue = -1;
        this.trTsValue = -1;
        this.hasArrivalTime = false;
        this.CPMFlag = "-";
        this.memorySpacesIndexes = new ArrayList<>();
        this.processBurstTime = instructions.size();
    }

    public void setPCB(PCB PCB) {
        this.PCB = PCB;
    }

    public PCB getPCB() {
        return PCB;
    }

    public void setProcessIsCorrect() {
        this.processIsCorrect = !processIsCorrect;
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
}
