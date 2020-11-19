/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.coresManagement;

import logic.memory.Register;

/**
 *
 * @author Michelle Alvarado
 */
public class Core {
    private Register currentProcessID;
    private Register IRRegister;
    private boolean isAvailable;
    
    public Core(){
        this.currentProcessID = new Register();
        this.IRRegister = new Register();
        this.isAvailable = true;
    }

    public Register getCurrentProcessID() {
        return currentProcessID;
    }

    public void setCurrentProcessID(Register currentProcessID) {
        this.currentProcessID = currentProcessID;
    }

    public Register getIRRegister() {
        return IRRegister;
    }

    public void setIRRegister(Register IRRegister) {
        this.IRRegister = IRRegister;
    }
    
    public void setAvailable(){
        this.isAvailable = !this.isAvailable;
    }
}
