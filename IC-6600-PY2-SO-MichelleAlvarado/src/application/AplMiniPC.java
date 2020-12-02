/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import controller.MainController;
import java.io.IOException;
import java.util.Calendar;
import javax.xml.parsers.ParserConfigurationException;
import logic.custom.OSSettings.OSConfigReader;
import org.xml.sax.SAXException;

/**
 *
 * @author Michelle Alvarado
 */
public class AplMiniPC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        new MainController().showView();
    }
}