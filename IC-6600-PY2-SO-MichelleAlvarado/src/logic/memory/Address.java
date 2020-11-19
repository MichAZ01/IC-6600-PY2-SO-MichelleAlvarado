/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.memory;

/**
 *
 * @author Michelle Alvarado
 */
public class Address {
    private String memoryType;
    private int memoryIndex;
    private String memoryAddress;
    
    public Address(){
        this.memoryAddress = "";
    }

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public int getMemoryIndex() {
        return memoryIndex;
    }

    public void setMemoryIndex(int memoryIndex) {
        this.memoryIndex = memoryIndex;
    }

    public String getMemoryAddress() {
        return memoryAddress;
    }

    public void setMemoryAddress(String memoryAddress) {
        this.memoryAddress = memoryAddress;
    }
}
