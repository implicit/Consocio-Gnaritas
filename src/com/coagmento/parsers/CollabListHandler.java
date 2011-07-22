package com.coagmento.parsers;

import java.util.LinkedList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CollabListHandler extends DefaultHandler{
	private boolean in_collabID = false;
	private boolean in_collabName = false;
	
	LinkedList<CollabListDataSet> collabListData = new LinkedList<CollabListDataSet>();
	
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
		if (localName.equals("collabID")) 
		{
			this.in_collabID = true;
		} 
		else if (localName.equals("collabName")) 
		{
			this.in_collabName = true;
		}
	}
	
	@Override
	//method is called when parser gets to a closing tag
	public void endElement (String nameSpaceURI, String localName, String qName) throws SAXException 
	{
		if (localName.equals("collabID")) 
		{
			this.in_collabID = false;
		} else if (localName.equals("collabName")) 
		{
			this.in_collabName = false;
		}
	}
	
	@Override
	//retrieves the data within a tag and puts it into a String called data
	public void characters(char ch[], int start, int length) 
	{
		String data = new String(ch, start, length);
		
		if (this.in_collabName) 
		{
			collabListData.getLast().setName(data);
		} else if (this.in_collabID) 
		{
			collabListData.add(new CollabListDataSet());
			collabListData.getLast().setCollabID(Integer.parseInt(data));
		}	
	}
	
	public LinkedList<CollabListDataSet> getParsedData() 
	{
		return this.collabListData;
	}
}
