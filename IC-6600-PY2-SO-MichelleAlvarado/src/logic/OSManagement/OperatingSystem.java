/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.OSManagement;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import logic.custom.OSSettings.OSConfig;
import org.xml.sax.SAXException;

/**
 *
 * @author Michelle Alvarado
 */
public class OperatingSystem {

    private Kernel kernel;

    public OperatingSystem() {
        kernel = new Kernel();
    }

    public Kernel getKernel() {
        return kernel;
    }
}
