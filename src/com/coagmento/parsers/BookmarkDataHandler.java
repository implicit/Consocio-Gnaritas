package com.coagmento.parsers;

import java.util.LinkedList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BookmarkDataHandler extends DefaultHandler{
	private boolean in_title = false;
	private boolean in_url = false;
	private boolean in_date = false;
	private boolean in_time = false;
	
	
	LinkedList<BookmarkDataSet> bookmarkData = new LinkedList<BookmarkDataSet>();
	
	
	public void startDocment() throws SAXException 
	{
		//startup stuff
	}
	
	
	
	@Override
	//localName is the value of the tag (e.g. login)
	//attributes is the stuff in the tag? (e.g. userID)?
	//method is called when parser gets to an opening tag
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException 
	{
		if (localName.equals("title")) 
		{
			this.in_title = true;
		} 
		else if (localName.equals("url")) 
		{
			this.in_url = true;
		}
		else if (localName.equals("date")) 
		{
			this.in_date = true;
		}
		else if (localName.equals("time")) 
		{
			this.in_time = true;
		}
	}
	
	@Override
	//method is called when parser gets to a closing tag
	public void endElement (String nameSpaceURI, String localName, String qName) throws SAXException 
	{
		if (localName.equals("title")) 
		{
			this.in_title = false;
		} 
		else if (localName.equals("url")) 
		{
			this.in_url = false;
		}
		else if (localName.equals("date")) 
		{
			this.in_date = false;
		}
		else if (localName.equals("time")) 
		{
			this.in_time = false;
		}
	}
	
	@Override
	//retrieves the data within a tag and puts it into a String called data
	public void characters(char ch[], int start, int length) 
	{
		String data = new String(ch, start, length);
		
		if (this.in_title) 
		{
			bookmarkData.add(new BookmarkDataSet());
			bookmarkData.getLast().setTitle(data);
		} 
		else if (this.in_url) 
		{
			bookmarkData.getLast().setUrl(data);
		}
		else if (this.in_date) 
		{
			bookmarkData.getLast().setDate(data);
		}
		else if (this.in_time) 
		{
			bookmarkData.getLast().setTime(data);
		}	
	}
	
	public LinkedList<BookmarkDataSet> getParsedData() 
	{
		return this.bookmarkData;
	}
}
