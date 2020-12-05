/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import logic.ProcessesManagement.Process;
/**
 *
 * @author Michelle Alvarado
 */
public class StatisticsTableController extends TableController{
    int currentRowIndex;
    
    public StatisticsTableController(){
        this.currentRowIndex = 0;
    }
    
    public void addProcessStatistics(Process process){
        if(process != null){
            this.getView().statisticsTable.getModel().setValueAt(process.getProcessID(), this.currentRowIndex, 0);
            this.getView().statisticsTable.getModel().setValueAt(process.getArrivalHour(), this.currentRowIndex, 1);
            this.getView().statisticsTable.getModel().setValueAt(process.getInitHour(), this.currentRowIndex, 2);
            this.getView().statisticsTable.getModel().setValueAt(process.getPCB().getInitTime().getRegisterValue(), this.currentRowIndex, 3);
            this.getView().statisticsTable.getModel().setValueAt(process.getPCB().getFinalTime().getRegisterValue(), this.currentRowIndex, 4);
            this.getView().statisticsTable.getModel().setValueAt(process.getTrValue(), this.currentRowIndex, 5);
            this.getView().statisticsTable.getModel().setValueAt(process.getTrValue(), this.currentRowIndex, 6);
        }
        this.currentRowIndex += 1;
    }
}
