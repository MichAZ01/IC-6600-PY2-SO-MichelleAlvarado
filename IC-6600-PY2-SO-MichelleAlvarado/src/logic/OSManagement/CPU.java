/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.OSManagement;

import java.util.ArrayList;
import logic.ProcessesManagement.ProcessesManager;
import logic.coresManagement.Core;
import logic.coresManagement.CoresManager;
import logic.memory.MemoryManager;

/**
 *
 * @author Michelle Alvarado
 */
public class CPU {
    private int CPUCurrentTime;
    private ArrayList<Core> CPUCores;
    
    public CPU(){
        this.CPUCurrentTime = 0;
        this.CPUCores = new CoresManager().createCores(5);
    }
}
