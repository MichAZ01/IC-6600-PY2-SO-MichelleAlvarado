/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.ProcessesManagement;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import logic.custom.FileManager;

/**
 *
 * @author Michelle Alvarado
 */
public class ProcessManager {
    private ArrayList<Process> loadedProcesses;
    private ArrayList<Process> currentReadyProcesses;
    private ArrayList<Process> finishedProcesses;
    private int processesToExecute;
    private String currentProcessesInitTime;
    
    public ProcessManager(){
        this.processesToExecute = 0;
        this.currentProcessesInitTime = "";
    }
    
    public void loadProcesses(File[] processFiles) throws IOException{
        this.setCurrentProcessesInitTime();
        this.loadedProcesses = new ArrayList<Process>();
        this.currentReadyProcesses = new ArrayList<Process>();
        File currentProcessFile;
        FileManager fileReader = new FileManager();
        ProgramValidator programValidator = new ProgramValidator();
        String[] processStatus = null;
        for(int i = 0; i < processFiles.length; i++){
            currentProcessFile = processFiles[i];
            String processLine = fileReader.extractFileInfo(currentProcessFile);
            processStatus = programValidator.validateSelectedFile(processLine);
            ArrayList<String> instructions = fileReader.getCleanData(processLine);
            Process newProcess = new Process(instructions, currentProcessFile.getName(), i);
            if(processStatus[0].equals("0")){
                newProcess.setProcessIsCorrect();
                this.processesToExecute += 1;
                this.currentReadyProcesses.add(newProcess);
            }
            PCB newProcessPCB = new PCB(processStatus[1], newProcess.getProcessInstructions().size());
            newProcessPCB.getProcessID().setRegisterValue(newProcess.getProcessID());
            newProcess.setPCB(newProcessPCB);
            newProcess.setProcessName(currentProcessFile.getName());
            this.loadedProcesses.add(newProcess);
        }
    }
    
    public void setCurrentProcessesInitTime(){
        Date time = new java.util.Date(System.currentTimeMillis());
        this.currentProcessesInitTime = new SimpleDateFormat("HH:mm:ss").format(time);
    }
    
}
