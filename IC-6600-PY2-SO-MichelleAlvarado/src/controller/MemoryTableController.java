/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import GUI.ColorRenderer;
import GUI.MiniPC;
import java.awt.Color;
import java.util.ArrayList;
import logic.computer.Computer;
import logic.memory.Memory;
import logic.memory.Register;
import logic.ProcessesManagement.Process;
/**
 *
 * @author Michelle Alvarado
 */
public class MemoryTableController extends TableController{
    private static MemoryTableController myMemoryTableController = null;
    ColorRenderer mainMemoryColorRenderer;
    ColorRenderer secondaryMemoryColorRenderer;
    private MemoryTableController(){
        super();
    }
    
    public static MemoryTableController getInstance(){
        if(myMemoryTableController == null) myMemoryTableController = new MemoryTableController();
        
        return myMemoryTableController;
    }
    
    public Object[][] setMemoryData(Memory memory){
        Object[][] data = this.CreateEmptyData(2, memory.getMemoryLength());
        ArrayList<Register> memoryRegisters = memory.getMemoryRegisters();
        for(int i = 0; i < memoryRegisters.size(); i++){
            data[i][0] = memoryRegisters.get(i).getRegisterAddress().getMemoryAddress();
            data[i][1] = memoryRegisters.get(i).getRegisterValue();
        }
        return data;
    }
    
    public void setMainMemoryTable(){
        String[] header = new String [] {"Posición", "Valor"};
        boolean[] canEdit = new boolean [] {false, false};
        Object[][] data = this.setMemoryData(Computer.getInstance().getMemoryManager().getMainMemory());
        this.setTableModel(data, this.getView().mainMemoryTable, header, canEdit);
        ArrayList<Process> processes = Computer.getInstance().getOS().getKernel().getProcessesManager().getCurrentMainMemoryProcesses();
        ColorRenderer renderer = this.setMemoryTableProcessesColors(processes, 2);
        this.mainMemoryColorRenderer = renderer;
        this.getView().mainMemoryTable.setDefaultRenderer(Object.class, renderer);
    }
    
    public void setSecondaryMemoryTable(){
        String[] header = new String [] {"Posición", "Valor"};
        boolean[] canEdit = new boolean [] {false, false};
        Object[][] data = this.setMemoryData(Computer.getInstance().getMemoryManager().getSecondaryMemory());
        this.setTableModel(data, this.getView().secondaryMemoryTable, header, canEdit);
        ArrayList<Process> processes = Computer.getInstance().getOS().getKernel().getProcessesManager().getCurrentSecondaryMemoryProcesses();
        ColorRenderer renderer = this.setMemoryTableProcessesColors(processes, 2);
        this.secondaryMemoryColorRenderer = renderer;
        this.getView().secondaryMemoryTable.setDefaultRenderer(Object.class, renderer);
    }
    
    public ColorRenderer setMemoryTableProcessesColors(ArrayList<logic.ProcessesManagement.Process> processes, int col){
        ColorRenderer renderer = new ColorRenderer();
        for(logic.ProcessesManagement.Process process: processes){
            ArrayList<Register> memorySpaces = process.getMemorySpaces();
            for(Register register: memorySpaces){
                int row = register.getRegisterAddress().getMemoryIndex();
                for(int column = 0; column < col; column++){
                    renderer.setColorForCell(row, column, process.getProcessColor());
                }
            }
        }
        return renderer;
    }
    
    public void cleanMemoryTableController(){
        myMemoryTableController = new MemoryTableController();
    }
}
