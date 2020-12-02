/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.ProcessesManagement;

import java.util.ArrayList;
import logic.ProcessesManagement.memoryProcesses.DynamicPartitionedProcess;
import logic.custom.OSSettings.OSConfig;

/**
 *
 * @author Michelle Alvarado
 */
public class FactoryProcess {
    
    public FactoryProcess(){
        
    }
    
    public Process getProcess(ArrayList<String> instructions, String name, int processIDIndex){
        Process newProcess = null;
       String memoryManagementMethod = OSConfig.getInstance().getActiveMethod(OSConfig.getInstance().getMemoryManagementMethods()).get(1);
       switch(memoryManagementMethod){
           case "DynamicPartition":
               newProcess = new DynamicPartitionedProcess(instructions, name, processIDIndex);
               break;
           default:
               break;
       }
       return newProcess;
    }
}
