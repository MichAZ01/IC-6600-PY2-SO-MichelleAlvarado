/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.memory;

import logic.custom.OSSettings.OSConfig;

/**
 *
 * @author Michelle Alvarado
 */
public class MemoryManager {

    private Memory mainMemory;
    private Memory secondaryMemory;

    public MemoryManager() {
        this.mainMemory = new Memory();
        this.secondaryMemory = new Memory();
        this.init();
    }

    public Memory getMainMemory() {
        return mainMemory;
    }

    public Memory getSecondaryMemory() {
        return secondaryMemory;
    }

    public Memory initializeMemory(Memory memory, int memoryLength, String memoryType) {
        memory.setMemoryLength(memoryLength);
        memory.setFreeSpaces(memoryLength);
        for (int i = 0; i < memoryLength; i++) {
            Register newRegister = new Register();
            newRegister.getRegisterAddress().setMemoryType(memoryType);
            newRegister.getRegisterAddress().setMemoryIndex(i);
            newRegister.getRegisterAddress().setMemoryAddress(this.getProcessAddressForRegister(newRegister));
            memory.getMemoryRegisters().add(newRegister);
        }
        return memory;
    }

    public String getProcessAddressForRegister(Register register) {
        String processAddress = "";
        if (register.getRegisterAddress().getMemoryType().equals("mainMemory")) {
            processAddress += "0000 ";
        } else {
            processAddress += "1000 ";
        }
        int zerosAmount = 4 - (Integer.toString(register.getRegisterAddress().getMemoryIndex()).length());
        for (int i = 0; i < zerosAmount; i++) {
            processAddress += "0";
        }
        processAddress += Integer.toString(register.getRegisterAddress().getMemoryIndex());
        return processAddress;
    }

    public void init() {
        int mainMemorySize = OSConfig.getInstance().getMainMemorySize();
        int secondaryMemorySize = OSConfig.getInstance().getSecondaryMemorySize();
        this.mainMemory = this.initializeMemory(this.mainMemory, mainMemorySize, "mainMemory");
        this.secondaryMemory = this.initializeMemory(this.secondaryMemory, secondaryMemorySize, "secondaryMemory");
    }
}
