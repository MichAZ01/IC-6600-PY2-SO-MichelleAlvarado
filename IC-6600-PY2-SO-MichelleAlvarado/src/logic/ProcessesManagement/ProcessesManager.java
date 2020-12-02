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
import java.util.Calendar;
import java.util.Date;
import logic.custom.FileManager;

/**
 *
 * @author Michelle Alvarado
 */
public class ProcessesManager {
    private ArrayList<Process> loadedProcesses;
    private ArrayList<Process> currentWaitingProcesses;
    private ArrayList<Process> currentReadyProcesses;
    private ArrayList<Process> currentExecutingProcesses;
    private ArrayList<Process> finishedProcesses;
    private int processesToExecute;
    private Calendar currentProcessesInitHour;
    
    public ProcessesManager(){
        this.processesToExecute = 0;
        this.currentWaitingProcesses = new ArrayList<Process>();
        this.currentReadyProcesses = new ArrayList<Process>();
        this.loadedProcesses = new ArrayList<Process>();
        this.currentExecutingProcesses = new ArrayList<Process>();
        this.finishedProcesses = new ArrayList<Process>();
    }
    
    public void loadProcesses(File[] processFiles) throws IOException{
        this.setCurrentProcessesInitHour();
        this.loadedProcesses = new ArrayList<Process>();
        this.currentWaitingProcesses = new ArrayList<Process>();
        File currentProcessFile;
        FileManager fileReader = new FileManager();
        ProgramValidator programValidator = new ProgramValidator();
        String[] processStatus = null;
        for(int i = 0; i < processFiles.length; i++){
            currentProcessFile = processFiles[i];
            String processLine = fileReader.extractFileInfo(currentProcessFile);
            processStatus = programValidator.validateSelectedFile(processLine);
            ArrayList<String> instructions = fileReader.getCleanData(processLine);
            Process newProcess = new FactoryProcess().getProcess(instructions, currentProcessFile.getName(), i);
            if(processStatus[0].equals("0")){
                newProcess.setProcessIsCorrect();
                this.processesToExecute += 1;
                this.currentWaitingProcesses.add(newProcess);
            }
            PCB newProcessPCB = new PCB(processStatus[1], newProcess.getProcessInstructions().size());
            newProcessPCB.getProcessID().setRegisterValue(newProcess.getProcessID());
            newProcess.setPCB(newProcessPCB);
            newProcess.setProcessName(currentProcessFile.getName());
            this.loadedProcesses.add(newProcess);
        }
    }
    
    public void setCurrentProcessesInitHour(){
        this.currentProcessesInitHour = Calendar.getInstance();
    }
    
    public int getConfigurableProcessesCount(){
        int count = 0;
        if(this.currentReadyProcesses.size() >= 5) count = 5;
        else count = this.currentReadyProcesses.size();
        return count;
    }
    
    public ArrayList<Process> getConfigurableProcesses(){
        ArrayList<Process> configurableProcesses = new ArrayList<Process>();
        int count = this.getConfigurableProcessesCount();
        for(int i = 0; i < count; i++){
            configurableProcesses.add(this.currentReadyProcesses.get(i));
        }
        return configurableProcesses;
    }

    public int getProcessesToExecute() {
        return processesToExecute;
    }
    
    public void addProcessTocurrentReadyProcesses(Process process){
        this.currentReadyProcesses.add(process);
    }

    public ArrayList<Process> getCurrentReadyProcesses() {
        return currentReadyProcesses;
    }

    public ArrayList<Process> getCurrentWaitingProcesses() {
        return currentWaitingProcesses;
    }
    
    public void setReadyProcessesArrivalTime(ArrayList<Object> processesArrivalTime){
        ArrayList<Process> configurableProcesses = this.getConfigurableProcesses();
        Process firstProcess = configurableProcesses.get(0);
        firstProcess.setArrivalTime("0");
        firstProcess.setArrivalHour(this.currentProcessesInitHour.getTime().toString().split(" ")[3]);
        int x = 0;
        for(int i = 1; i < configurableProcesses.size(); i++){
            Process process = configurableProcesses.get(i);
            process.setArrivalTime(Integer.toString((int) processesArrivalTime.get(x)));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(this.currentProcessesInitHour.getTime());
            calendar.add(this.currentProcessesInitHour.SECOND, (int) processesArrivalTime.get(x));
            process.setArrivalHour(calendar.getTime().toString().split(" ")[3]);
            x++;
        }
    }
}
