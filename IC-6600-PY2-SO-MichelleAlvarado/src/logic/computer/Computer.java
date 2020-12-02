/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.computer;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import logic.OSManagement.CPU;
import logic.OSManagement.OperatingSystem;
import logic.ProcessesManagement.ProcessesManager;
import logic.memory.MemoryManager;
import org.xml.sax.SAXException;

/**
 *
 * @author Michelle Alvarado
 */
public class Computer {
    private static Computer myComputer = null;
    private MemoryManager memoryManager;
    private CPU myCPU;
    private OperatingSystem myOS;
    
    private Computer(){
        myOS = new OperatingSystem();
        this.memoryManager = new MemoryManager();
        myCPU = new CPU();
    }
    
    public static Computer getInstance() {
        if(myComputer == null){
            myComputer = new Computer();
        }
        return myComputer;
    }
    
    public CPU getCPU(){
        return myCPU;
    }
    
    public OperatingSystem getOS(){
        return myOS;
    }

    public MemoryManager getMemoryManager() {
        return memoryManager;
    }
   
}
