/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.OSManagement;

import logic.ProcessesManagement.ProcessesManager;
import logic.custom.OSSettings.OSConfig;
import logic.custom.OSSettings.OSConfigReader;
import logic.memoryManagement.MemoryManagementAlgorithm;
import logic.schedulingAlgorithms.Scheduler;
/**
 *
 * @author Michelle Alvarado
 */
public class Kernel {
    private ProcessesManager processesManager;
    private MemoryManagementAlgorithm memoryManagementAlgorithm;
    private Scheduler myScheduler;
    private Loader programsLoader;
    
    public Kernel() {
        this.processesManager = new ProcessesManager();
        OSConfigReader.getInstance().getSOConfig();
        this.memoryManagementAlgorithm = OSConfig.getInstance().getMemoryManagementAlgorithm();
        this.myScheduler = OSConfig.getInstance().getSchedulerAlgorithm();
        this.programsLoader = new Loader();
    }
    
    public ProcessesManager getProcessesManager() {
        return processesManager;
    }

    public MemoryManagementAlgorithm getMemoryManagementAlgorithm() {
        return memoryManagementAlgorithm;
    }

    public Loader getProgramsLoader() {
        return programsLoader;
    }
    
}
