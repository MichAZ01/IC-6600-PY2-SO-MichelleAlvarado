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
public class Process {

    private ArrayList<String> processInstructions;
    private ArrayList<Register> allocatedMemory;
    private int processBurstTime;
    private PCB PCB;
    private boolean processIsCorrect;
    private String processName;
    private String processID;
    private int PCBSize;
    private String initTime;
    private String finalTime;
    private String arrivalTime;
    private Color processColor;
    private double trValue;
    private double trTsValue;
    private boolean hasArrivalTime;
    private String CPMFlag;

    public Process(ArrayList<String> instructions, String name, int processIDIndex) {
        this.processInstructions = instructions;
        this.allocatedMemory = new ArrayList<Register>();
        this.processBurstTime = this.processInstructions.size();
        this.processIsCorrect = false;
        this.processName = name;
        this.PCBSize = 19;
        this.initTime = initTime = "";
        this.finalTime = "";
        this.arrivalTime = "";
        this.processID = "P-" + processIDIndex;
        this.trValue = -1;
        this.trTsValue = -1;
        this.hasArrivalTime = false;
        this.CPMFlag = "-";
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

    public void setInitTime(String initTime) {
        this.initTime = initTime;
    }

    public void setFinalTime(String finalTime) {
        this.finalTime = finalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getProcessName() {
        return processName;
    }

    public int getPCBSize() {
        return PCBSize;
    }
    
    public int getStackAvailableSpace(){
        int space = 0;
        for(int i = 0; i < this.PCB.getStack().size(); i++){
            if(this.PCB.getStack().get(i).getRegisterValue().equals("00000000")) space += 1;
        }
        return space;
    }

    public String getCPMFlag() {
        return CPMFlag;
    }

    public void setCPMFlag(String CPMFlag) {
        this.CPMFlag = CPMFlag;
    }
    
    
}
