package com.coagmento.parsers;

import java.net.URL;
import java.util.LinkedList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class SnippetParser {
	LinkedList<SnippetDataSet> snippetData;
	
	public LinkedList<SnippetDataSet> parseSnippets(int userID, int projID)
	{
		
		try 
	    {
	        URL url = new URL("http://www.coagmento.org/mobile/getSnippets.php?userID=" + userID + "&projID=" + projID);
	        
	        SAXParserFactory pFactory = SAXParserFactory.newInstance();
	        SAXParser pInstance = pFactory.newSAXParser();
	        XMLReader xReader = pInstance.getXMLReader();
	        
	        SnippetDataHandler sHandler = new SnippetDataHandler();
	        
	        xReader.setContentHandler(sHandler);
	        xReader.parse(new InputSource(url.openStream()));
	        
	        snippetData = sHandler.getParsedData();
	    } 
	    catch (Exception e) {
	    	
	    }
	    
	    return snippetData;
	}
}
