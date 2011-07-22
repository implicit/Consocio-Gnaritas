package com.coagmento.parsers;

import java.net.URL;
import java.util.LinkedList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class CollabListParser
{
	LinkedList<CollabListDataSet> collabList;
	
	public LinkedList<CollabListDataSet > parseCollabs(int userID)
	{
		
		try 
	    {
			
	        URL url = new URL("http://www.coagmento.org/mobile/collabList.php?userID=" + userID);
	        
	        SAXParserFactory pFactory = SAXParserFactory.newInstance();
	        SAXParser pInstance = pFactory.newSAXParser();
	        XMLReader xReader = pInstance.getXMLReader();
	        
	        CollabListHandler clHandler = new CollabListHandler();
	        
	        xReader.setContentHandler(clHandler);
	        xReader.parse(new InputSource(url.openStream()));
	        
	        collabList = clHandler.getParsedData();
	    } 
	    catch (Exception e) 
	    {
	    }
	    
	    return collabList;
	}
}