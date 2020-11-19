/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.memory;

/**
 * this class contains the attributes and the accesor methods that allow to simulate the general registers of the cpu like AX, BX, CX, DX
 * @author Michelle Alvarado
 */
public class Register {
    private String registerValue;
    private Address registerAddress;
    
    public Register(){
        this.registerValue = "-";
        this.registerAddress = new Address();
    }

    public String getRegisterValue() {
        return registerValue;
    }

    public void setRegisterValue(String registerValue) {
        this.registerValue = registerValue;
    }

    public Address getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(Address registerAddress) {
        this.registerAddress = registerAddress;
    }
}
