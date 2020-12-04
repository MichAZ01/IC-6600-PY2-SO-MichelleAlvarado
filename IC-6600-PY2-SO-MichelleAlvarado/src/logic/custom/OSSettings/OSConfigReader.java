/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.custom.OSSettings;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
/**
 *
 * @author Michelle Alvarado
 */
public class OSConfigReader {
    private static OSConfigReader myOSConfigReader = null;
    private static final String XMLFILEPATH = System.getProperty("user.dir") + "\\src\\logic\\configFiles\\OSConfig.xml";
    private static File XMLFile;
    private static DocumentBuilderFactory XMLFactory;
    private static DocumentBuilder XMLBuilder;
    private static Document XMLDocument;
    private static Element rootElement;
    private static final int DYNAMICPARTITIONINDEX = 0;
    private static final int FIXEDPARTITIONINDEX = 1;
    private static final int PAGINATIONINDEX = 2;
    private static final int SEGMENTATIONINDEX = 3;
    private static final int FCFSINDEX = 0;
    private static final int SRTINDEX = 1;
    private static final int SJFINDEX = 2;
    private static final int RRINDEX = 3;
    private static final int HRRNINDEX = 4;
    
    private OSConfigReader() {
        XMLFile = new File(XMLFILEPATH);
        this.initOSConfigReader();
    }
    
    public static OSConfigReader getInstance() {
        if(myOSConfigReader == null){
            myOSConfigReader = new OSConfigReader();
        }
        return myOSConfigReader;
    }
    
    public void initOSConfigReader() {
        try {
            XMLFactory = DocumentBuilderFactory.newInstance();
            XMLBuilder = XMLFactory.newDocumentBuilder();
            XMLDocument = XMLBuilder.parse(XMLFile);
            XMLDocument.getDocumentElement().normalize();
            rootElement = XMLDocument.getDocumentElement();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(OSConfigReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(OSConfigReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OSConfigReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getMemorySizeConfig(){
        Element memoriesSizeElement = (Element) rootElement.getElementsByTagName("MemoriesSize").item(0);
        int mainMemorySize = Integer.parseInt(memoriesSizeElement.getElementsByTagName("MainMemory").item(0).getTextContent());
        int secondaryMemorySize = Integer.parseInt(memoriesSizeElement.getElementsByTagName("SecondaryMemory").item(0).getTextContent());
        OSConfig.getInstance().setMainMemorySize(mainMemorySize);
        OSConfig.getInstance().setSecondaryMemorySize(secondaryMemorySize);
    }
    
    public ArrayList<String> getConfigElementAttributes(String tagName, String tagElementName, int index){
        ArrayList<String> configElement = new ArrayList<String>();
        Element configTagElement = this.getConfigElement(tagName, index);
        String active = configTagElement.getAttribute("active");
        String configElementName = configTagElement.getElementsByTagName(tagElementName).item(0).getTextContent();
        configElement.add(active);
        configElement.add(configElementName);
        return configElement;
    }
    
    public Element getConfigElement(String tagName, int index){
        return (Element) rootElement.getElementsByTagName(tagName).item(index);
    }
    
    public ArrayList<String> getConfigElementWithSecondAttribute(String tagName, String tagElementName, String secondTagElementName, int index){
        Element configTagElement = this.getConfigElement(tagName, index);
        ArrayList<String> configElement = this.getConfigElementAttributes(tagName, tagElementName, index);
        configElement.add(configTagElement.getElementsByTagName(secondTagElementName).item(0).getTextContent());
        return configElement;
    }
    
    public void setMemoryManagementMethods(){
        ArrayList<String> dynamicPartitionAttributes = this.getConfigElementAttributes("Method", "MethodName", DYNAMICPARTITIONINDEX);
        ArrayList<String> fixedPartitionAttributes = this.getConfigElementWithSecondAttribute("Method", "MethodName", "partitionSize", FIXEDPARTITIONINDEX);
        ArrayList<String> paginationAttributes = this.getConfigElementWithSecondAttribute("Method", "MethodName", "frameSize", PAGINATIONINDEX);
        ArrayList<String> segmentationAttributes = this.getConfigElementAttributes("Method", "MethodName", SEGMENTATIONINDEX);
        ArrayList<ArrayList<String>> memoryManagementMethods = new ArrayList<>();
        memoryManagementMethods.add(dynamicPartitionAttributes);
        memoryManagementMethods.add(fixedPartitionAttributes);
        memoryManagementMethods.add(paginationAttributes);
        memoryManagementMethods.add(segmentationAttributes);
        OSConfig.getInstance().setMemoryManagementMethods(memoryManagementMethods);
    }
    
    public void setSchedulingMethods(){
        ArrayList<String> FCFSMethodAttributes = this.getConfigElementAttributes("SchedulingMethod", "SchedulingName", FCFSINDEX);
        ArrayList<String> SRTMethodAttributes = this.getConfigElementAttributes("SchedulingMethod", "SchedulingName", SRTINDEX);
        ArrayList<String> SJFMethodAttributes = this.getConfigElementAttributes("SchedulingMethod", "SchedulingName", SJFINDEX);
        ArrayList<String> RRMethodAttributes = this.getConfigElementWithSecondAttribute("SchedulingMethod", "SchedulingName", "quantum", RRINDEX);
        ArrayList<String> HRRNMethodAttributes = this.getConfigElementAttributes("SchedulingMethod", "SchedulingName", HRRNINDEX);
        ArrayList<ArrayList<String>> schedulingMethods = new ArrayList<>();
        schedulingMethods.add(FCFSMethodAttributes);
        schedulingMethods.add(SRTMethodAttributes);
        schedulingMethods.add(SJFMethodAttributes);
        schedulingMethods.add(RRMethodAttributes);
        schedulingMethods.add(HRRNMethodAttributes);
        OSConfig.getInstance().setSchedulingMethods(schedulingMethods);
    }
    
    public void getSOConfig(){
        this.getMemorySizeConfig();
        this.setMemoryManagementMethods();
        this.setSchedulingMethods();
    }
    
    public void cleanOSConfigReader(){
        myOSConfigReader = new OSConfigReader();
    }
}
