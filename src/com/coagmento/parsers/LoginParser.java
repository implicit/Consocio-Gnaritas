package com.coagmento.parsers;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class LoginParser
{
	LoginDataSet loginData;
	
	public LoginDataSet parseLogin(String username, String pw)
	{
		
		try 
	    {
	        URL url = new URL("http://www.coagmento.org/mobile/login.php?userName=" + username + "&password=" + pw);
	        
	        SAXParserFactory pFactory = SAXParserFactory.newInstance();
	        SAXParser pInstance = pFactory.newSAXParser();
	        XMLReader xReader = pInstance.getXMLReader();
	        
	        LoginHandler lHandler = new LoginHandler();
	        
	        xReader.setContentHandler(lHandler);
	        xReader.parse(new InputSource(url.openStream()));
	        
	        loginData = lHandler.getParsedData();
	    } 
	    catch (Exception e) 
	    {
	    }
	    
	    return loginData;
	}
}