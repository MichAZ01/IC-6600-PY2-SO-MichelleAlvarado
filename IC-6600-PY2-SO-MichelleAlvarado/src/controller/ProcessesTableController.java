/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import GUI.ColorRenderer;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import logic.ProcessesManagement.Process;
import logic.computer.Computer;
/**
 *
 * @author Michelle Alvarado
 */
public class ProcessesTableController extends TableController{
    private static ProcessesTableController myProcessesTableController;
    
    private ProcessesTableController(){
        super();
    }
    
    public static ProcessesTableController getInstance(){
        if(myProcessesTableController == null) myProcessesTableController = new ProcessesTableController();
        return myProcessesTableController;
    }
    
    public void setProcessesTable(){
        String[] header = new String [] {"Color", "ID", "Llegada", "Nombre", "Estado", "PC", "AC", 
                   "AX", "BX", "CX", "DX", "Pl V1", "Pl V2", "Pl V3", "Pl V4", "Pl V5"};
        boolean[] canEdit = new boolean [] {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
        ArrayList<Process> processes = Computer.getInstance().getOS().getKernel().getProcessesManager().getLoadedProcesses();
        Object[][] data = this.setProcessesTableData(processes);
        this.setTableModel(data, this.getView().processesTable, header, canEdit);
        this.customizeProcessesTable(this.getView().processesTable);
        ColorRenderer renderer = this.setTableProcessesColors(processes);
        this.getView().processesTable.setDefaultRenderer(Object.class, renderer);
    }
    
    public Object[][] setProcessesTableData(ArrayList<Process> processes){
        int rows = this.getView().processesTable.getModel().getRowCount();
        if(processes.size() > rows) rows = processes.size();
        int columns = this.getView().processesTable.getModel().getColumnCount();
        Object[][] data = new Object[rows][columns];
        for(int i = 0; i < processes.size(); i++){
            Process process = processes.get(i);
            data[i][1] = process.getProcessID();
            data[i][2] = process.getArrivalTime();
            data[i][3] = process.getProcessName();
            data[i][4] = process.getPCB().getProcessStatus().getRegisterValue();
            data[i][5] = process.getPCB().getPC().getRegisterValue();
            data[i][6] = process.getPCB().getAC().getRegisterValue();
            data[i][7] = process.getPCB().getAX().getRegisterValue();
            data[i][8] = process.getPCB().getBX().getRegisterValue();
            data[i][9] = process.getPCB().getCX().getRegisterValue();
            data[i][10] = process.getPCB().getDX().getRegisterValue();
            data[i][11] = process.getPCB().getStack().get(0).getRegisterValue();
            data[i][12] = process.getPCB().getStack().get(1).getRegisterValue();
            data[i][13] = process.getPCB().getStack().get(2).getRegisterValue();
            data[i][14] = process.getPCB().getStack().get(3).getRegisterValue();
            data[i][15] = process.getPCB().getStack().get(4).getRegisterValue();
        }
        
        return data;
    }
    
    public ColorRenderer setTableProcessesColors(ArrayList<Process> processes){
        ColorRenderer renderer = new ColorRenderer();
        
        for(int i = 0; i < processes.size(); i++){
            renderer.setColorForCell(i, 0, processes.get(i).getProcessColor());
        }
        
        return renderer;
    }
    
    public void customizeProcessesTable(JTable processesTable) {
        processesTable.getColumnModel().getColumn(0).setPreferredWidth(2);
        processesTable.getColumnModel().getColumn(1).setPreferredWidth(4);
        processesTable.getColumnModel().getColumn(2).setPreferredWidth(30);
        processesTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        processesTable.getColumnModel().getColumn(4).setPreferredWidth(110);
        processesTable.getColumnModel().getColumn(5).setPreferredWidth(40);
        processesTable.getColumnModel().getColumn(6).setPreferredWidth(20);
        processesTable.getColumnModel().getColumn(7).setPreferredWidth(20);
        processesTable.getColumnModel().getColumn(8).setPreferredWidth(20);
        processesTable.getColumnModel().getColumn(9).setPreferredWidth(20);
        processesTable.getColumnModel().getColumn(10).setPreferredWidth(20);
        processesTable.getColumnModel().getColumn(11).setPreferredWidth(20);
        processesTable.getColumnModel().getColumn(12).setPreferredWidth(20);
        processesTable.getColumnModel().getColumn(13).setPreferredWidth(20);
        processesTable.getColumnModel().getColumn(14).setPreferredWidth(20);
        processesTable.getColumnModel().getColumn(15).setPreferredWidth(20);
    }
    
    public void cleanProcessesTableController(){
        myProcessesTableController = new ProcessesTableController();
    }
}
