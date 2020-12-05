/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import GUI.MiniPC;
import logic.schedulingAlgorithms.Scheduler.IExecutionObserver;
import logic.ProcessesManagement.Process;
import logic.computer.Computer;
/**
 *
 * @author Michelle Alvarado
 */
public class ExecutionController implements IExecutionObserver{
    private ExecutionTableController myExecutionTableController;
    private StatisticsTableController myStatisticsController;
    public ExecutionController(){
        MiniPC view = MemoryTableController.getInstance().getView();
        this.myExecutionTableController = new ExecutionTableController();
        this.myExecutionTableController.initView(view);
        this.myStatisticsController = new StatisticsTableController();
        this.myStatisticsController.initView(view);
    }
    
    @Override
    public void update(Process process) {
        MemoryTableController.getInstance().setMainMemoryTable();
        MemoryTableController.getInstance().setSecondaryMemoryTable();
        ProcessesTableController.getInstance().setProcessesTable();
        if(process != null) this.myExecutionTableController.addExecutionSecond(process);
    }

    public ExecutionTableController getMyExecutionTableController() {
        return myExecutionTableController;
    }

    @Override
    public void setStatistics(Process process) {
        this.myStatisticsController.addProcessStatistics(process);
    }
    
}
