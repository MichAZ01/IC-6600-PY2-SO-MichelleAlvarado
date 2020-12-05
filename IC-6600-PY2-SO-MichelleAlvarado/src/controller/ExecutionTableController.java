/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import GUI.ColorRenderer;
import GUI.MiniPC;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.table.TableColumn;
import logic.ProcessesManagement.Process;
import logic.computer.Computer;
/**
 *
 * @author Michelle Alvarado
 */
public class ExecutionTableController extends TableController{
    private ColorRenderer myColorRenderer;
    
    public ExecutionTableController(){
        this.myColorRenderer = new ColorRenderer();
        this.myColorRenderer.setHorizontalAlignment(JLabel.CENTER);
    }
    
    public void addExecutionSecond(Process process){
        if(Computer.getInstance().getCPU().getCPUCurrentTime() >= 100) this.addColumn();
        int CPUTime = Computer.getInstance().getCPU().getCPUCurrentTime() - 1;
        int core = process.getCoreWhereIsRuning() + 1;
        this.getView().executionTable.getModel().setValueAt(Computer.getInstance().getCPU().getCPUCurrentTime(), 0, CPUTime);
        this.getView().executionTable.getModel().setValueAt(process.getProcessID(), core, CPUTime);
        this.setFieldColor(process.getProcessColor(), core, CPUTime);
        this.getView().executionTable.setDefaultRenderer(Object.class, this.myColorRenderer);
    }
    
    public void setFieldColor(Color color, int i, int j){
        this.myColorRenderer.setColorForCell(i, j, color);
    }
    
    public void addColumn(){
        TableColumn column = new TableColumn();
        column.setPreferredWidth(60);
        this.getView().executionTable.addColumn(column);
    }
}
