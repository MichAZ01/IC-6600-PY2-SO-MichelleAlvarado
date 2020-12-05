/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.schedulingAlgorithms;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import logic.computer.Computer;
import logic.coresManagement.Core;
import logic.ProcessesManagement.Process;
import logic.memory.Register;

/**
 *
 * @author Michelle Alvarado
 */
public abstract class Scheduler {

    private Process currentExecutingProcess;
    private IExecutionObserver myExecutionObserver;

    public Scheduler() {
        this.currentExecutingProcess = null;
    }

    public void execute() throws InterruptedException {
        while (Computer.getInstance().getCPU().getHasProcessesToExecute()) {
            this.assignProcessesToAvailableCores();
            this.verifyBusyCoresProcesses();

            if (this.currentExecutingProcess != null) {
                Core coreWhereIsruning = Computer.getInstance().getCPU().getMyCoresManager().getCPUCores().get(this.currentExecutingProcess.getCoreWhereIsRuning());
                if (coreWhereIsruning.getCPUCycleRemainingTime() > 0) {
                    coreWhereIsruning.setIRRegister(this.currentExecutingProcess.getMemorySpaces().get(this.currentExecutingProcess.getInternalPC()));
                    //ejecutar instrucción
                    coreWhereIsruning.setCPUCycleRemainingTime();
                }
                    
                    
                if (this.currentExecutingProcess.getInternalPC() == (this.currentExecutingProcess.getMemorySpaces().size() - 1)) {
                    coreWhereIsruning.setIRRegister(this.currentExecutingProcess.getMemorySpaces().get(this.currentExecutingProcess.getInternalPC()));
                    //ejecutar instrucción
                    if (this.currentExecutingProcess.getPCB().getProcessStatus().getRegisterValue().equals("Ejecutando")) {
                        this.currentExecutingProcess.getPCB().getProcessStatus().setRegisterValue("Finalizado");
                        this.currentExecutingProcess.setIsFinalized(true);
                        this.freeUpProcess(coreWhereIsruning, this.currentExecutingProcess);
                        
                    }
                    coreWhereIsruning.setCPUCycleRemainingTime(0);
                } else {
                    this.currentExecutingProcess.setInternalPC();
                    int i = this.currentExecutingProcess.getInternalPC();
                    this.currentExecutingProcess.getPCB().getPC().setRegisterValue(
                        this.currentExecutingProcess.getMemorySpaces().get(i).getRegisterAddress().getMemoryAddress());
                }
                if (coreWhereIsruning.getCPUCycleRemainingTime() == 0 && this.currentExecutingProcess != null) {
                    if (!this.currentExecutingProcess.getPCB().getProcessStatus().getRegisterValue().equals("Ejecutando")) {
                        Computer.getInstance().getOS().getKernel().getProcessesManager().getCurrentExecutingProcesses().remove(this.currentExecutingProcess);
                        this.freeUpProcess(coreWhereIsruning, this.currentExecutingProcess);
                    } else {
                        this.currentExecutingProcess.getPCB().getProcessStatus().setRegisterValue("Preparado");
                        Computer.getInstance().getOS().getKernel().getProcessesManager().getCurrentExecutingProcesses().add(this.currentExecutingProcess);
                    }
                    this.currentExecutingProcess = null;
                }
            }

            if (this.currentExecutingProcess == null) {
                this.currentExecutingProcess = this.getNextProcess();
                if (this.currentExecutingProcess != null) {
                    this.currentExecutingProcess.getPCB().getProcessStatus().setRegisterValue("Ejecutando");
                    Computer.getInstance().getOS().getKernel().getProcessesManager().setProcessInitTime(this.currentExecutingProcess);
                    Core coreWhereIsruning = Computer.getInstance().getCPU().getMyCoresManager().getCPUCores().get(this.currentExecutingProcess.getCoreWhereIsRuning());
                    coreWhereIsruning.setIRRegister(this.currentExecutingProcess.getMemorySpaces().get(this.currentExecutingProcess.getInternalPC()));
                }
            }

            if (!this.verifyCPUHasProcessesToExecute()) {
                Computer.getInstance().getCPU().setHasProcessesToExecute(false);
            }
            if (this.myExecutionObserver != null) {
                this.myExecutionObserver.update(this.currentExecutingProcess);
            }

            Computer.getInstance().getCPU().setCPUCurrentTime();
            sleep(1000);
        }
    }

    public void freeUpProcess(Core core, Process process) {
        core.setIsAvailable(true);
        core.setCurrentProcess(null);
        core.setCurrentProcessRemainingBurstTime(0);
        core.setCPUCycleRemainingTime(0);
        core.setIRRegister(new Register());
        Computer.getInstance().getOS().getKernel().getProcessesManager().getFinishedProcesses().add(process);
        Computer.getInstance().getOS().getKernel().getProcessesManager().getCurrentExecutingProcesses().remove(process);
        Computer.getInstance().getOS().getKernel().getProcessesManager().setProcessFinalTime(process);
        //modificar estadísticas del proceso
        Computer.getInstance().getOS().getKernel().getMemoryManagementAlgorithm().freeUpProcessMemory(process);

    }

    public void verifyBusyCoresProcesses() {
        ArrayList<Core> busyCores = Computer.getInstance().getCPU().getMyCoresManager().getBusyCores();
        for (Core core : busyCores) {
            Process process = core.getCurrentProcess();
            if (Computer.getInstance().getCPU().getCPUCurrentTime() >= Integer.parseInt(process.getArrivalTime())) {
                if (!Computer.getInstance().getOS().getKernel().getProcessesManager().getCurrentExecutingProcesses().contains(process)){
                    Computer.getInstance().getOS().getKernel().getProcessesManager().getCurrentExecutingProcesses().add(process);    
                }
            }
        }
    }

    public void assignProcessesToAvailableCores() {
        ArrayList<Core> availableCores = Computer.getInstance().getCPU().getMyCoresManager().getAvailableCores();
        if (availableCores.size() > 0) {
            ArrayList<Process> processesToExecute = this.getProcessesToExecute(availableCores.size());
            if (processesToExecute.size() > 0) {
                int i = 0;
                for (Process process : processesToExecute) {
                    Core core = availableCores.get(i);
                    core.setIsAvailable(false);
                    core.setCurrentProcess(process);
                    core.setCurrentProcessRemainingBurstTime(process.getProcessBurstTime());
                    core.setCPUCycleRemainingTime(this.getCPUCycleRemainingTime(process));
                    if (process.getArrivalTime().equals("-1")) {
                        Computer.getInstance().getOS().getKernel().getProcessesManager().setProcessArrivalTime(process);
                    }
                    process.setCoreWhereIsRuning(core.getCoreIndex());
                    i++;
                }
            }
        }
    }

    public ArrayList<Process> getProcessesToExecute(int necessaryProcesses) {
        ArrayList<Process> processesToExecute = new ArrayList<>();
        
        if (Computer.getInstance().getOS().getKernel().getProcessesManager().getCurrentWaitingProcesses().size() > 0) {
            Computer.getInstance().getOS().getKernel().getProgramsLoader().allocateWaitingProcessesInMemory();
        } else if (Computer.getInstance().getOS().getKernel().getProcessesManager().getNewProcesses().size() > 0) {
            Computer.getInstance().getOS().getKernel().getProgramsLoader().allocateNewProcessesInMemory();
        }
        
        ArrayList<Process> readyProcesses = Computer.getInstance().getOS().getKernel().getProcessesManager().getCurrentReadyProcesses();
        if (readyProcesses.size() > 0) {
            if (readyProcesses.size() <= necessaryProcesses) {
                processesToExecute.addAll(readyProcesses);
            } else {
                for (int i = 0; i < necessaryProcesses; i++) {
                    processesToExecute.add(readyProcesses.get(i));
                }
            }
        }
        if (processesToExecute.size() > 0) {
            Computer.getInstance().getOS().getKernel().getProcessesManager().cleanCurrentReadyProcesses(processesToExecute);
        }

        return processesToExecute;
    }

    public boolean verifyCPUHasProcessesToExecute() {
        boolean hasProcessesToExecute = true;
        if (Computer.getInstance().getCPU().getMyCoresManager().getBusyCores().isEmpty()
                && Computer.getInstance().getOS().getKernel().getProcessesManager().getCurrentReadyProcesses().isEmpty()
                && Computer.getInstance().getOS().getKernel().getProcessesManager().getCurrentExecutingProcesses().isEmpty()
                && Computer.getInstance().getOS().getKernel().getProcessesManager().getCurrentWaitingProcesses().isEmpty()
                && Computer.getInstance().getOS().getKernel().getProcessesManager().getNewProcesses().isEmpty()
                && this.currentExecutingProcess == null) {
            hasProcessesToExecute = false;
        }
        return hasProcessesToExecute;
    }

    public void setMyExecutionObserver(IExecutionObserver myExecutionObserver) {
        this.myExecutionObserver = myExecutionObserver;
    }

    public abstract int getCPUCycleRemainingTime(Process process);

    public abstract Process getNextProcess();

    public interface IExecutionObserver {

        public void update(Process process);

    }
}
