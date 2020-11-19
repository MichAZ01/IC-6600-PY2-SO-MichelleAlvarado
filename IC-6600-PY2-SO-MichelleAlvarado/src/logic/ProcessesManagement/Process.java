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
    private Color processColor;
    private double trValue;
    private double trTsValue;

    public Process(ArrayList<String> instructions, String initTime, String name, PCB pcb, int processIDIndex) {
        this.processInstructions = instructions;
        this.allocatedMemory = new ArrayList<Register>();
        this.processBurstTime = this.processInstructions.size();
        this.processIsCorrect = false;
        this.processName = name;
        this.PCBSize = 19;
        this.initTime = initTime;
        this.finalTime = "";
        this.PCB = pcb;
        this.processID = "P-" + processIDIndex;
        this.PCB.getProcessID().setRegisterValue(this.processID);
        this.trValue = -1;
        this.trTsValue = -1;
    }
}
