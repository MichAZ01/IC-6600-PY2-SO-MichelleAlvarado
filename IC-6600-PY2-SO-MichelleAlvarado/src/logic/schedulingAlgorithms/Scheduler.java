/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.schedulingAlgorithms;

import java.util.ArrayList;
import logic.computer.Computer;
import logic.coresManagement.Core;
import logic.ProcessesManagement.Process;
/**
 *
 * @author Michelle Alvarado
 */
public abstract class Scheduler {
    Process currentExecutingProcess;
    
    public Scheduler(){
        this.currentExecutingProcess = null;
    }
    
    public void execute(){
        while(Computer.getInstance().getCPU().getHasProcessesToExecute()){
            this.assignProcessesToAvailableCores();
            this.verifyBusyCoresProcesses();
            
            if(this.currentExecutingProcess != null){
                Core coreWhereIsruning = Computer.getInstance().getCPU().getMyCoresManager().getCPUCores().get(this.currentExecutingProcess.getCoreWhereIsRuning());
                if(this.currentExecutingProcess.getInternalPC() == (this.currentExecutingProcess.getMemorySpaces().size() - 1)){
                    //ejecutar instrucción
                    if(this.currentExecutingProcess.getPCB().getProcessStatus().equals("Ejecutando")) {
                        this.currentExecutingProcess.getPCB().getProcessStatus().setRegisterValue("Finalizado");
                    }
                    coreWhereIsruning.setCPUCycleRemainingTime(0);
                } else {
                    if(coreWhereIsruning.getCPUCycleRemainingTime() > 0){
                        //ejecutar instrucción
                        coreWhereIsruning.setCPUCycleRemainingTime();
                    }
                    this.currentExecutingProcess.setInternalPC();
                }
                if(coreWhereIsruning.getCPUCycleRemainingTime() == 0){
                    if(!this.currentExecutingProcess.getPCB().getProcessStatus().getRegisterValue().equals("Ejecutando")){
                        this.freeUpProcess(coreWhereIsruning, this.currentExecutingProcess);
                    } else {
                        this.currentExecutingProcess.getPCB().getProcessStatus().setRegisterValue("Preparado");
                        Computer.getInstance().getOS().getKernel().getProcessesManager().getCurrentExecutingProcesses().add(this.currentExecutingProcess);
                    }
                    this.currentExecutingProcess = null;
                }
            }
        }
    }
    
    public void freeUpProcess(Core core, Process process){
        core.setIsAvailable();
        core.setCurrentProcess(null);
        core.setCurrentProcessRemainingBurstTime(0);
        core.setCPUCycleRemainingTime(0);
        Computer.getInstance().getOS().getKernel().getProcessesManager().getFinishedProcesses().add(process);
        Computer.getInstance().getOS().getKernel().getProcessesManager().setProcessFinalTime(process);
        //modificar estadísticas del proceso
    }
    
    public void verifyBusyCoresProcesses(){
        ArrayList<Core> busyCores = Computer.getInstance().getCPU().getMyCoresManager().getBusyCores();
        for(Core core: busyCores){
            Process process = core.getCurrentProcess();
            if(Computer.getInstance().getCPU().getCPUCurrentTime() >= Integer.parseInt(process.getArrivalTime())){
                Computer.getInstance().getOS().getKernel().getProcessesManager().getCurrentExecutingProcesses().add(process);
            }
        }
    }
    
    public void assignProcessesToAvailableCores(){
        ArrayList<Core> availableCores = Computer.getInstance().getCPU().getMyCoresManager().getAvailableCores();
        if(availableCores.size() > 0){
            ArrayList<Process> processesToExecute = this.getProcessesToExecute(availableCores.size());
            if(processesToExecute.size() > 0){
                int i = 0;
                for(Process process: processesToExecute){
                    Core core = availableCores.get(i);
                    core.setIsAvailable();
                    core.setCurrentProcess(process);
                    core.setCurrentProcessRemainingBurstTime(process.getProcessBurstTime());
                    core.setCPUCycleRemainingTime(this.getCPUCycleRemainingTime(process));
                    if(process.getArrivalTime().equals("-1")) Computer.getInstance().getOS().getKernel().getProcessesManager().setProcessArrivalTime(process);
                    process.setCoreWhereIsRuning(core.getCoreIndex());
                }
            }
        }
    }
    
    public ArrayList<Process> getProcessesToExecute(int necessaryProcesses){
        ArrayList<Process> processesToExecute = new ArrayList<>();
        
        ArrayList<Process> readyProcesses = Computer.getInstance().getOS().getKernel().getProcessesManager().getCurrentReadyProcesses();
        if(readyProcesses.size() > 0){
            if(readyProcesses.size() <= necessaryProcesses) processesToExecute.addAll(readyProcesses);
            else {
                for(int i = 0; i < necessaryProcesses; i++) processesToExecute.add(readyProcesses.get(i));
            }
            Computer.getInstance().getOS().getKernel().getProcessesManager().cleanCurrentReadyProcesses(processesToExecute);
            necessaryProcesses -= processesToExecute.size();
        }
        if(necessaryProcesses > 0){
            ArrayList<Process> processes = new ArrayList<>();
            if(Computer.getInstance().getOS().getKernel().getProcessesManager().getCurrentWaitingProcesses().size() > 0){
                Computer.getInstance().getOS().getKernel().getProgramsLoader().allocateWaitingProcessesInMemory();
            }
            else if(Computer.getInstance().getOS().getKernel().getProcessesManager().getNewProcesses().size() > 0){
                Computer.getInstance().getOS().getKernel().getProgramsLoader().allocateNewProcessesInMemory();
            }
            readyProcesses = Computer.getInstance().getOS().getKernel().getProcessesManager().getCurrentReadyProcesses();
            if(readyProcesses.size() > 0){
                if(readyProcesses.size() <= necessaryProcesses) processes.addAll(readyProcesses);
                
                else for(int i = 0; i < necessaryProcesses; i++) processes.add(readyProcesses.get(i));
            }
            if(processes.size() > 0){
                Computer.getInstance().getOS().getKernel().getProcessesManager().cleanCurrentReadyProcesses(processes);
                processesToExecute.addAll(processes);
            }
        }
        
        return processesToExecute;
    }
    
    public abstract int getCPUCycleRemainingTime(Process process);
    
    public abstract Process getNextProcess();
}
