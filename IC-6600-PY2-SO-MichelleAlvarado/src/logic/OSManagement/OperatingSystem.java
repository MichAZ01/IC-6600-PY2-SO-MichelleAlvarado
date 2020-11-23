/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.OSManagement;

/**
 *
 * @author Michelle Alvarado
 */
public class OperatingSystem {
    private static OperatingSystem operatingSystem = null;
    private static Kernel kernel;
    
    private OperatingSystem(){
        kernel = Kernel.getInstance();
    }
    
    /**
     * Singleton method
     * @return
     * @throws IOException 
     */
    public static OperatingSystem getInstance(){
        if(operatingSystem == null){
            return new OperatingSystem();
        }
        else{
            return operatingSystem;
        }
    }
    
    public Kernel getKernel(){
        return kernel;
    }
}
