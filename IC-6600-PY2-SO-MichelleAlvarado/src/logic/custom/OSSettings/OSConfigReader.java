/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.custom.OSSettings;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 *
 * @author Michelle Alvarado
 */
public class OSConfigReader {
    private static final String XMLFILEPATH = System.getProperty("user.dir") + "\\src\\logic\\configFiles\\OSConfig.xml";
    private Document XMLDocument;
    private NodeList memoryManagementMethods;
    public OSConfigReader(){
        
    }
    
    public void getXMLDocument() throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        this.XMLDocument = documentBuilder.parse(XMLFILEPATH);
    }
    
    public NodeList getChildsByTagName(String tagName){
        Node tagNode = this.XMLDocument.getElementsByTagName(tagName).item(0);
        NodeList childNodes = tagNode.getChildNodes();
        return childNodes;
    }
    
    public ArrayList<String> getDynamicMethod(int nodeIndex){
        Node dynamicMethodNode = this.memoryManagementMethods.item(nodeIndex);
        String active = dynamicMethodNode.getAttributes().getNamedItem("active").getTextContent();
        String methodName = dynamicMethodNode.getChildNodes().item(0).getNodeValue();
        ArrayList<String> dynamicMethod = new ArrayList<String>();
        dynamicMethod.add(methodName);
        dynamicMethod.add(active);
        return dynamicMethod;
    }
}
