package com.coagmento.parsers;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class ProjectDataParser {
	
ProjectDataSet projectData;
	
	public ProjectDataSet parseProjData(int userID, int projID)
	{
		
		try 
	    {
	        URL url = new URL("http://www.coagmento.org/mobile/projInfo.php?userID=" + userID + "&projID=" + projID);
	        
	        SAXParserFactory pFactory = SAXParserFactory.newInstance();
	        SAXParser pInstance = pFactory.newSAXParser();
	        XMLReader xReader = pInstance.getXMLReader();
	        
	        ProjectDataHandler pdHandler = new ProjectDataHandler();
	        
	        xReader.setContentHandler(pdHandler);
	        xReader.parse(new InputSource(url.openStream()));
	        
	        projectData = pdHandler.getParsedData();
	    } 
	    catch (Exception e) 
	    {
	    }
	    
	    return projectData;
	}
}
