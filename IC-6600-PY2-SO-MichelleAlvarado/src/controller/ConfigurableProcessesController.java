/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import GUI.MiniPC;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import logic.computer.Computer;

/**
 *
 * @author Michelle Alvarado
 */
public class ConfigurableProcessesController extends TableController implements ActionListener {

    private MiniPC view;

    public ConfigurableProcessesController(MiniPC viewp) {
        super();
        this.view = viewp;
        this.view.arrivalTimeButtonAccept.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "arrivalTimeAccept":
                this.validateConfigurableProcesses();
                break;
            default:
                break;
        }
    }

    public void validateConfigurableProcesses() {
        int count = Computer.getInstance().getOS().getKernel().getProcessesManager().getConfigurableProcessesCount() - 1;
        if (this.view.firstProcessesTable.isEditing()) {
            this.view.firstProcessesTable.getCellEditor().stopCellEditing();
        }
        int columnIndex = this.getColumnIndex(this.view.firstProcessesTable, "Tiempo de llegada");
        ArrayList<Object> processesArrivalTime = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Object arrivalTime = this.view.firstProcessesTable.getModel().getValueAt(i, columnIndex);
            if(arrivalTime != null) processesArrivalTime.add(arrivalTime);
        }
        this.verifyArrivalTimesAreCorrect(processesArrivalTime, count);
    }
    
    public void verifyArrivalTimesAreCorrect(ArrayList<Object> processesArrivalTime,int count){
        if(processesArrivalTime.size() == count){
            Computer.getInstance().getOS().getKernel().getProcessesManager().setReadyProcessesArrivalTime(processesArrivalTime);
            this.view.arrivalTimePanel.setVisible(false);
        }
        else{
            JOptionPane.showMessageDialog(this.view, "Los tiempos de llegada deben ser números enteros y no se pueden dejar vacíos.");
        }
    }
    
    public void setConfigurableProcessesTable(){
        ArrayList<logic.ProcessesManagement.Process> configurableProcesses = Computer.getInstance().getOS().getKernel().getProcessesManager().getConfigurableProcesses();
        int j = 1;
        for (int i = 0; i < configurableProcesses.size() - 1; i++) {
            this.view.firstProcessesTable.getModel().setValueAt(configurableProcesses.get(j).getProcessName(), i, 0);
            this.view.firstProcessesTable.getModel().setValueAt(configurableProcesses.get(j).getProcessID(), i, 1);
            j++;
        }
    }

    int getColumnIndex(JTable table, String columnTitle) {
        int columnCount = table.getColumnCount();

        for (int column = 0; column < columnCount; column++) {
            if (table.getColumnName(column).equalsIgnoreCase(columnTitle)) {
                return column;
            }
        }

        return -1;
    }

}
