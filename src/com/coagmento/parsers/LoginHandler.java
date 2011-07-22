package com.coagmento.parsers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LoginHandler extends DefaultHandler{
	private boolean in_userID = false;
	private boolean in_name = false;
	
	private LoginDataSet loginData = new LoginDataSet();
	
	public void startDocment() throws SAXException 
	{
		//startup stuff. none needed yet.
	}
	
	@Override
	//localName is the value of the tag (e.g. login)
	//attributes is the stuff in the tag? (e.g. userID)?
	//method is called when parser gets to an opening tag
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException 
	{
		if (localName.equals("userID")) 
		{
			this.in_userID = true;
		} 
		else if (localName.equals("name")) 
		{
			this.in_name = true;
		}
	}
	
	@Override
	//method is called when parser gets to a closing tag
	public void endElement (String nameSpaceURI, String localName, String qName) throws SAXException 
	{
		if (localName.equals("userID")) 
		{
			this.in_userID = false;
		} else if (localName.equals("name")) 
		{
			this.in_name = false;
		}
	}
	
	@Override
	//retrieves the data within a tag and puts it into a String called data
	public void characters(char ch[], int start, int length) 
	{
		String data = new String(ch, start, length);
		
		if (this.in_name) 
		{
			loginData.setName(data);
		} else if (this.in_userID) 
		{
			loginData.setUserID(Integer.parseInt(data));
		}	
	}
	
	public LoginDataSet getParsedData() 
	{
		return this.loginData;
	}
}
