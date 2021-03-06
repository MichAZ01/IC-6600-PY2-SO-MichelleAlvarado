/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.memory;

import java.util.ArrayList;

/**
 *
 * @author Michelle Alvarado
 */
public class Memory {
    private String memoryType;
    private int memoryLength;
    private ArrayList<Register> memoryRegisters;
    private int freeSpaces;
    
    public Memory(String memoryType){
        this.memoryRegisters = new ArrayList<Register>();
        this.memoryType = memoryType;
    }

    public int getMemoryLength() {
        return memoryLength;
    }

    public void setMemoryLength(int memoryLength) {
        this.memoryLength = memoryLength;
    }

    public ArrayList<Register> getMemoryRegisters() {
        return memoryRegisters;
    }

    public void setMemoryRegisters(ArrayList<Register> memoryRegisters) {
        this.memoryRegisters = memoryRegisters;
    }

    public int getFreeSpaces() {
        return freeSpaces;
    }

    public void setFreeSpaces(int freeSpaces) {
        this.freeSpaces = freeSpaces;
    }
    
    
}
