package com.coagmento.parsers;

import java.util.LinkedList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class NoteDataHandler extends DefaultHandler{
	private boolean in_id = false;
	private boolean in_text = false;
	private boolean in_date = false;
	private boolean in_time = false;
	
	
	LinkedList<NoteDataSet> noteData = new LinkedList<NoteDataSet>();
	
	
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
		if (localName.equals("id")) 
		{
			this.in_id = true;
		} 
		else if (localName.equals("text")) 
		{
			this.in_text = true;
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
		if (localName.equals("id")) 
		{
			this.in_id = false;
		} 
		else if (localName.equals("text")) 
		{
			this.in_text = false;
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
		
		if (this.in_id) 
		{
			noteData.add(new NoteDataSet());
			noteData.getLast().setId(data);
		} 
		else if (this.in_text) 
		{
			noteData.getLast().setText(data);
		}
		else if (this.in_date) 
		{
			noteData.getLast().setDate(data);
		}
		else if (this.in_time) 
		{
			noteData.getLast().setTime(data);
		}	
	}
	
	public LinkedList<NoteDataSet> getParsedData() 
	{
		return this.noteData;
	}
}
