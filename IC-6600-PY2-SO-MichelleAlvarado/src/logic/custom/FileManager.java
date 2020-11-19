/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.custom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Michelle Alvarado
 */
public class FileManager {
    public FileManager(){
        
    }
    
    /**
     * takes a path and returns an array that contains all the lines of the respective file
     * @param selectedFile
     * @return lines: all the lines of the configuration file
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public String[] readFile(String selectedFile) throws FileNotFoundException, IOException {
        String cadena;
        String[] lines;
        
        java.io.FileReader fr = new java.io.FileReader(selectedFile);
        BufferedReader buffer = new BufferedReader(fr);
        
        int size = getFileLength(selectedFile);
        lines = new String[size];
        int i = 0;
        while((cadena = buffer.readLine())!=null) {
            lines[i] = cadena;
            i++;
        }
        buffer.close();
        
        return lines;
    }
    
    /**
     * takes a file and returns the amount of the lines that its file contains
     * @param selectedFile
     * @return size
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public int getFileLength(String selectedFile) throws FileNotFoundException, IOException {
        String cadena;
        int size = 0;
        
        java.io.FileReader file = new java.io.FileReader(selectedFile);
        BufferedReader buffer = new BufferedReader(file);
        
        while((cadena = buffer.readLine())!=null) {
            size += 1;
        }
        buffer.close();
        
        return size;
    }
    
    /**
     * return an string that contains the all lines of the file concatenated
     * @param file: an assembly file
     * @return data
     * @throws IOException 
     */
    public String extractFileInfo(File file) throws IOException {
        FileInputStream tokensFile = new FileInputStream(file);
        byte[] dataBytes = new byte[(int) file.length()];
        tokensFile.read(dataBytes);
        String data = new String(dataBytes, "UTF-8");
        return data;
    }
    
    /**
     * return the lines of a file without empty lines
     * @param data: a string that contains all the lines of a file
     * @return resultData
     */
    
    public ArrayList<String> getCleanData(String data){
        ArrayList<String> resultData = new ArrayList<String>();
        String lines[] = data.split("\\r?\\n");
        for(int i = 0; i < lines.length; i++){
            String line = lines[i];
            if(!line.trim().isEmpty()) resultData.add(line.trim());
        }
        return resultData;
    }
}
