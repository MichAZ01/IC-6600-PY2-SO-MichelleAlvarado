/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.OSManagement;

import logic.ProcessesManagement.ProcessesManager;
/**
 *
 * @author Michelle Alvarado
 */
public class Kernel {
    private ProcessesManager processesManager;
    
    public Kernel(){
        this.processesManager = new ProcessesManager();
    }
    
    public ProcessesManager getProcessesManager() {
        return processesManager;
    }
}
