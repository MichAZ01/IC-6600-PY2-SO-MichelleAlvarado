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
    private static Kernel myKernel = null;
    private ProcessesManager processesManager;
    
    private Kernel(){
        this.processesManager = new ProcessesManager();
    }
    
    public static Kernel getInstance(){
        if(myKernel == null){
            return new Kernel();
        }
        else{
            return myKernel;
        }
    }
    
    public ProcessesManager getProcessesManager() {
        return processesManager;
    }
}
