/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.custom.OSSettings;

import java.util.ArrayList;
import logic.memoryManagement.DynamicPartition;
import logic.memoryManagement.IMemoryManagementAlgorithm;
import logic.schedulingAlgorithms.FCFS;
import logic.schedulingAlgorithms.Scheduler;

/**
 *
 * @author Michelle Alvarado
 */
public class OSConfig {
    private static OSConfig myOSConfig = null;
    private int mainMemorySize;
    private int secondaryMemorySize;
    private ArrayList<ArrayList<String>> memoryManagementMethods;
    private ArrayList<ArrayList<String>> schedulingMethods;
    
    private OSConfig(){
        this.memoryManagementMethods = new ArrayList<ArrayList<String>>();
        this.schedulingMethods = new ArrayList<ArrayList<String>>();
    }
    
    public static OSConfig getInstance(){
        if(myOSConfig == null){
            myOSConfig = new OSConfig();
        }
        return myOSConfig;
    }

    public int getMainMemorySize() {
        return mainMemorySize;
    }

    public void setMainMemorySize(int mainMemorySize) {
        this.mainMemorySize = mainMemorySize;
    }

    public int getSecondaryMemorySize() {
        return secondaryMemorySize;
    }

    public void setSecondaryMemorySize(int secondaryMemorySize) {
        this.secondaryMemorySize = secondaryMemorySize;
    }

    public ArrayList<ArrayList<String>> getMemoryManagementMethods() {
        return memoryManagementMethods;
    }

    public void setMemoryManagementMethods(ArrayList<ArrayList<String>> memoryManagementMethods) {
        this.memoryManagementMethods = memoryManagementMethods;
    }

    public ArrayList<ArrayList<String>> getSchedulingMethods() {
        return schedulingMethods;
    }

    public void setSchedulingMethods(ArrayList<ArrayList<String>> schedulingMethods) {
        this.schedulingMethods = schedulingMethods;
    }
    
    public IMemoryManagementAlgorithm getMemoryManagementAlgorithm(){
        IMemoryManagementAlgorithm memoryManagementAlgorithm = null;
        ArrayList<String> activeMethod = this.getActiveMethod(this.memoryManagementMethods);
        String memoryManagementMethodName = activeMethod.get(1);
        switch(memoryManagementMethodName){
            case "DynamicPartition":
                memoryManagementAlgorithm = new DynamicPartition();
                break;
            default:
                break;
        }
        return memoryManagementAlgorithm;
    }
    
    public ArrayList<String> getActiveMethod(ArrayList<ArrayList<String>> methodsList){
        ArrayList<String> activeMethod = methodsList.get(0);
        for (ArrayList<String> method : methodsList) {
            if(method.get(0).equals("1")){
                activeMethod = method;
                break;
            }
        }
        
        return activeMethod;
    }
    
    public Scheduler getSchedulerAlgorithm(){
        Scheduler myScheduler = null;
        ArrayList<String> activeMethod = this.getActiveMethod(this.schedulingMethods);
        String schedulingMethod = activeMethod.get(1);
        switch(schedulingMethod){
            case "FCFS":
                myScheduler = new FCFS();
                break;
            default:
                break;
        }
        return myScheduler;
    }
    
    public void cleanOSConfig(){
        myOSConfig = new OSConfig();
    }
}
