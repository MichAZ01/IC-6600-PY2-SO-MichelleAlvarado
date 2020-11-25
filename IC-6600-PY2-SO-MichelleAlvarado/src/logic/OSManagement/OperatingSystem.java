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
    private Kernel kernel;
    
    public OperatingSystem(){
        kernel = new Kernel();
    }
    
    public Kernel getKernel(){
        return kernel;
    }
}
