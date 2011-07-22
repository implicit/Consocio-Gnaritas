package com.coagmento.parsers;

import java.util.LinkedList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SnippetDataHandlerOld extends DefaultHandler{
	private boolean in_title = false;
	private boolean in_url = false;
	private boolean in_date = false;
	private boolean in_time = false;
	private boolean in_content = false;
	private boolean in_note = false;
	private boolean in_snippet = false;
	private boolean notesDone = false;
	
	
	LinkedList<SnippetDataSet> snippetData = new LinkedList<SnippetDataSet>();
	
	
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
		else if (localName.equals("content")) 
		{
			this.in_content = true;
		}
		else if (localName.equals("note"))
		{
			this.in_note = true;
		}
		else if (localName.equals("snippet"))
		{
			this.in_snippet = true;
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
		else if (localName.equals("content"))
		{
			this.in_content = false;
		}
		else if (localName.equals("note"))
		{
			this.in_note = false;
		}
		else if (localName.equals("snippet")) 
		{
			this.in_snippet = false;
		}
	}
	
	@Override
	//retrieves the data within a tag and puts it into a String called data
	public void characters(char ch[], int start, int length) 
	{
		String data = new String(ch, start, length);
		
		
		if (this.in_title) 
		{
			snippetData.getLast().setTitle(data);
		} 
		else if (this.in_url) 
		{
			snippetData.getLast().setUrl(data);
		}
		else if (this.in_date) 
		{
			snippetData.getLast().setDate(data);
		}
		else if (this.in_time) 
		{
			snippetData.getLast().setTime(data);
		}	
		else if (this.in_content)
		{
			snippetData.getLast().setContent(data);
		}
		else if (this.in_note) 
		{
			snippetData.getLast().setNote(data);
			notesDone = true;
		}
		else if (this.in_snippet && snippetData.isEmpty()) 
		{
			snippetData.add(new SnippetDataSet());	
		} else if (this.in_snippet && notesDone) 
		{
			notesDone = false;
			snippetData.add(new SnippetDataSet());
		}
	}
	
	public LinkedList<SnippetDataSet> getParsedData() 
	{
		return this.snippetData;
	}
}
