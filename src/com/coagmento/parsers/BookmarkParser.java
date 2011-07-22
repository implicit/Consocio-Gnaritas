package com.coagmento.parsers;


import java.net.URL;
import java.util.LinkedList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class BookmarkParser {
	LinkedList<BookmarkDataSet> bookmarkData;
	
	public LinkedList<BookmarkDataSet> parseBookmarks(int userID, int projID)
	{
		
		try 
	    {
	        URL url = new URL("http://www.coagmento.org/mobile/getBookmarks.php?userID=" + userID + "&projID=" + projID);
	        
	        SAXParserFactory pFactory = SAXParserFactory.newInstance();
	        SAXParser pInstance = pFactory.newSAXParser();
	        XMLReader xReader = pInstance.getXMLReader();
	        
	        BookmarkDataHandler bHandler = new BookmarkDataHandler();
	        
	        xReader.setContentHandler(bHandler);
	        xReader.parse(new InputSource(url.openStream()));
	        
	        bookmarkData = bHandler.getParsedData();
	    } catch (Exception e) 
	    {
	    	
	    }
	    
	    
	    return bookmarkData;
	}
}
