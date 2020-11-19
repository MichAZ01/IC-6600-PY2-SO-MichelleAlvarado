/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.ProcessesManagement;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Michelle Alvarado
 */
public class ProcessManager {
    private ArrayList<Process> loadedProcesses;
    private ArrayList<Process> currentReadyProcesses;
    private ArrayList<Process> finishedProcesses;
    private File[] processesFiles;
    
    public ProcessManager(){
        
    }

    public void setProcessesFiles(File[] processesFiles) {
        this.processesFiles = processesFiles;
    }
    
    
}
