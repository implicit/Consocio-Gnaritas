package com.coagmento.parsers;

import java.net.URL;
import java.util.LinkedList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;


public class SearchParser {
	
		LinkedList<SearchDataSet> searchData;
		
		public LinkedList<SearchDataSet> parseSearches(int userID, int projID)
		{
			
			try 
		    {
		        URL url = new URL("http://www.coagmento.org/mobile/getSearches.php?userID=" + userID + "&projID=" + projID);
		        
		        SAXParserFactory pFactory = SAXParserFactory.newInstance();
		        SAXParser pInstance = pFactory.newSAXParser();
		        XMLReader xReader = pInstance.getXMLReader();
		        
		        SearchDataHandler sHandler = new SearchDataHandler();
		        
		        xReader.setContentHandler(sHandler);
		        xReader.parse(new InputSource(url.openStream()));
		        
		        searchData = sHandler.getParsedData();
		    } 
		    catch (Exception e) 
		    {
		    	System.out.println(e);
		    }
		    
		    return searchData;
		}
}
