package com.coagmento.parsers;

import java.net.URL;
import java.util.LinkedList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class ProjectListParser
{
	LinkedList<ProjectListDataSet> projectList;
	
	public LinkedList<ProjectListDataSet> parseProjectList(int userID)
	{
		
		try 
	    {
	        URL url = new URL("http://www.coagmento.org/mobile/projList.php?userID=" + userID);
	        
	        SAXParserFactory pFactory = SAXParserFactory.newInstance();
	        SAXParser pInstance = pFactory.newSAXParser();
	        XMLReader xReader = pInstance.getXMLReader();
	        
	        ProjectListHandler plHandler = new ProjectListHandler();
	        
	        xReader.setContentHandler(plHandler);
	        xReader.parse(new InputSource(url.openStream()));
	        
	        projectList = plHandler.getParsedData();
	    } 
	    catch (Exception e) 
	    {
	    }
	    
	    return projectList;
	}
}