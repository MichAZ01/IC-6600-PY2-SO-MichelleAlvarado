/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.ProcessesManagement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import logic.computer.Computer;
import logic.custom.ColorManager;
import logic.custom.FileManager;

/**
 *
 * @author Michelle Alvarado
 */
public class ProcessesManager {
    private ArrayList<Process> loadedProcesses;
    private ArrayList<Process> newProcesses;
    private ArrayList<Process> currentWaitingProcesses;
    private ArrayList<Process> currentReadyProcesses;
    private ArrayList<Process> currentExecutingProcesses;
    private ArrayList<Process> finishedProcesses;
    private int processesToExecute;
    private Calendar currentProcessesInitHour;
    
    public ProcessesManager(){
        this.processesToExecute = 0;
        this.newProcesses = new ArrayList<>();
        this.currentWaitingProcesses = new ArrayList<>();
        this.currentReadyProcesses = new ArrayList<>();
        this.loadedProcesses = new ArrayList<>();
        this.currentExecutingProcesses = new ArrayList<>();
        this.finishedProcesses = new ArrayList<>();
    }
    
    public void loadProcesses(File[] processFiles) throws IOException{
        this.setCurrentProcessesInitHour();
        this.loadedProcesses = new ArrayList<>();
        this.newProcesses = new ArrayList<>();
        File currentProcessFile;
        FileManager fileReader = new FileManager();
        ProgramValidator programValidator = new ProgramValidator();
        String[] processStatus;
        for(int i = 0; i < processFiles.length; i++){
            currentProcessFile = processFiles[i];
            String processLine = fileReader.extractFileInfo(currentProcessFile);
            processStatus = programValidator.validateSelectedFile(processLine);
            ArrayList<String> instructions = fileReader.getCleanData(processLine);
            Process newProcess = new FactoryProcess().getProcess(instructions, currentProcessFile.getName(), i);
            if(processStatus[0].equals("0")){
                this.processesToExecute += 1;
                this.newProcesses.add(newProcess);
            }
            PCB newProcessPCB = new PCB(processStatus[1], newProcess.getProcessInstructions().size());
            newProcess.setPCB(newProcessPCB);
            newProcess.setProcessName(currentProcessFile.getName());
            newProcess.setProcessColor(ColorManager.getInstance().generateColor(i));
            this.loadedProcesses.add(newProcess);
        }
        if(this.newProcesses.size() > 0){
            Computer.getInstance().getCPU().setHasProcessesToExecute(true);
        }
    }
    
    public void setCurrentProcessesInitHour(){
        this.currentProcessesInitHour = Calendar.getInstance();
    }
    
    public int getConfigurableProcessesCount(){
        int count;
        if(this.currentReadyProcesses.size() >= 5) count = 5;
        else count = this.currentReadyProcesses.size();
        return count;
    }
    
    public ArrayList<Process> getConfigurableProcesses(){
        ArrayList<Process> configurableProcesses = new ArrayList<>();
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
    
    public void addProcessTocurrentWaitingProcesses(Process process){
        this.currentWaitingProcesses.add(process);
    }

    public ArrayList<Process> getCurrentReadyProcesses() {
        return currentReadyProcesses;
    }

    public ArrayList<Process> getCurrentWaitingProcesses() {
        return currentWaitingProcesses;
    }

    public ArrayList<Process> getLoadedProcesses() {
        return loadedProcesses;
    }

    public ArrayList<Process> getNewProcesses() {
        return newProcesses;
    }
    
    
    public void setReadyProcessesArrivalTime(ArrayList<Object> processesArrivalTime){
        ArrayList<Process> configurableProcesses = this.getConfigurableProcesses();
        Process firstProcess = configurableProcesses.get(0);
        firstProcess.setArrivalTime("1");
        firstProcess.setArrivalHour(this.currentProcessesInitHour.getTime().toString().split(" ")[3]);
        int x = 0;
        for(int i = 1; i < configurableProcesses.size(); i++){
            Process process = configurableProcesses.get(i);
            process.setArrivalTime(Integer.toString((int) processesArrivalTime.get(x)));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(this.currentProcessesInitHour.getTime());
            calendar.add(Calendar.SECOND, (int) processesArrivalTime.get(x));
            process.setArrivalHour(calendar.getTime().toString().split(" ")[3]);
            x++;
        }
    }
    
    public void setProcessArrivalTime(Process process){
        process.setArrivalTime(Integer.toString(Computer.getInstance().getCPU().getCPUCurrentTime()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.currentProcessesInitHour.getTime());
        calendar.add(Calendar.SECOND, Computer.getInstance().getCPU().getCPUCurrentTime());
        process.setArrivalHour(calendar.getTime().toString().split(" ")[3]);
    }
    
    public void setProcessInitTime(Process process){
        process.getPCB().getInitTime().setRegisterValue(Integer.toString(Computer.getInstance().getCPU().getCPUCurrentTime()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.currentProcessesInitHour.getTime());
        calendar.add(Calendar.SECOND, Computer.getInstance().getCPU().getCPUCurrentTime());
        process.setInitHour(calendar.getTime().toString().split(" ")[3]);
    }
    
    public void setProcessFinalTime(Process process){
        process.getPCB().getFinalTime().setRegisterValue(Integer.toString(Computer.getInstance().getCPU().getCPUCurrentTime() + 1));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.currentProcessesInitHour.getTime());
        calendar.add(Calendar.SECOND, (Computer.getInstance().getCPU().getCPUCurrentTime() + 1));
        process.setFinalHour(calendar.getTime().toString().split(" ")[3]);
    }
    
    public void cleanNewProcesses(ArrayList<Process> processes){
        for(Process process: processes){
            if(process.getPCB().getProcessStatus().getRegisterValue().equals("Preparado") || 
                    process.getPCB().getProcessStatus().getRegisterValue().equals("En espera")) this.newProcesses.remove(process);
        }
    }
    
    public void cleanWaitingProcesses(ArrayList<Process> processes){
        for(Process process: processes){
            if(process.getPCB().getProcessStatus().getRegisterValue().equals("Preparado")) this.currentWaitingProcesses.remove(process);
        }
    }
    
    public void cleanCurrentReadyProcesses(ArrayList<Process> processes){
        for(Process process: processes){
            this.currentReadyProcesses.remove(process);
        }
    }

    public ArrayList<Process> getCurrentExecutingProcesses() {
        return currentExecutingProcesses;
    }

    public ArrayList<Process> getFinishedProcesses() {
        return finishedProcesses;
    }
}
