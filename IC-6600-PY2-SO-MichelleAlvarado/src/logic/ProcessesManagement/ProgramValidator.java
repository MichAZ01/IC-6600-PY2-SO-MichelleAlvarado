/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.ProcessesManagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import logic.custom.FileManager;
/**
 *
 * @author Michelle Alvarado
 */
public final class ProgramValidator {
    public static final String REGEXFILEPATH = System.getProperty("user.dir") + "\\src\\logic\\configFiles\\InstructionsRegexConfigFile.txt";
    String[] regexInstructionsArray;

    public ProgramValidator() throws IOException {
        this.loadRegexInstructions();
    }

    public void loadRegexInstructions() throws IOException {
        FileManager fileReader = new FileManager();
        String regexConfigFile = REGEXFILEPATH;
        String[] fileLines = fileReader.readFile(regexConfigFile);
        String[] regexInstructions = new String[fileLines.length];
        for (int i = 0; i < fileLines.length; i++) {
            regexInstructions[i] = fileLines[i].split(":")[1];
        }
        this.regexInstructionsArray = regexInstructions;
    }

    /**
     * validate (depend of the operation) if a instruction is correct or not
     *
     * @param line
     * @return
     */
    public Boolean validateSingleLine(String line) {
        Boolean correctFormat = false;
        for (String regexInstruction : this.regexInstructionsArray) {
            if (Pattern.matches(regexInstruction, line)) {
                correctFormat = true;
                break;
            }
        }

        return correctFormat;
    }

    /**
     * the loop for validate each program instruction
     *
     * @param data
     * @return
     * @throws IOException
     */
    public String validateLineFormat(String data) throws IOException {
        ArrayList<String> lines = new FileManager().getCleanData(data);
        String linesWithError = "";
        for (int i = 0; i < lines.size(); i++) {
            if (!validateSingleLine(lines.get(i))) {
                int lineIndex = i + 1;
                linesWithError += lineIndex + " ";
            }
        }
        return linesWithError;
    }

    /**
     * validates that the .asm selected file is not empty and has the correct
     * format
     *
     * @param data
     * @return
     * @throws IOException
     */
    public String[] validateSelectedFile(String data) throws IOException {
        String[] result = new String[2];
        Boolean fileIsEmpty = data.isEmpty();

        if (fileIsEmpty) {
            result[0] = "1";
            result[1] = "Error: archivo vacío";
            return result;
        } else {
            String linesWithError = this.validateLineFormat(data);
            if (linesWithError.equals("")) {
                result[0] = "0";
                result[1] = "Nuevo";
            }
            else{
                result[0] = "1";
                if(linesWithError.split(" ").length > 1){
                    result[1] = "Error en líneas: " + linesWithError;
                }
                else result[1] = "Error en línea: " + linesWithError;
            }
        }

        return result;
    }
    
}
