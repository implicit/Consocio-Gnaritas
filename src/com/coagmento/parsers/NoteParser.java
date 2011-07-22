package com.coagmento.parsers;


import java.net.URL;
import java.util.LinkedList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class NoteParser {
	LinkedList<NoteDataSet> noteData;
	
	public LinkedList<NoteDataSet> parseNotes(int userID, int projID)
	{
		
		try 
	    {
	        URL url = new URL("http://www.coagmento.org/mobile/getNotes.php?userID=" + userID + "&projID=" + projID);
	        
	        SAXParserFactory pFactory = SAXParserFactory.newInstance();
	        SAXParser pInstance = pFactory.newSAXParser();
	        XMLReader xReader = pInstance.getXMLReader();
	        
	        NoteDataHandler nHandler = new NoteDataHandler();
	        
	        xReader.setContentHandler(nHandler);
	        xReader.parse(new InputSource(url.openStream()));
	        
	        noteData = nHandler.getParsedData();
	    } catch (Exception e) 
	    {
	    	
	    }
	    
	    
	    return noteData;
	}
}
