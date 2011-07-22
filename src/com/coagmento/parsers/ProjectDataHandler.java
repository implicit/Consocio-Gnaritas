package com.coagmento.parsers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ProjectDataHandler extends DefaultHandler {

		private boolean in_name = false;
		private boolean in_bookmarks = false;
		private boolean in_searches = false;
		private boolean in_snippets = false;
		private boolean in_notes = false;
		
		private ProjectDataSet projectData = new ProjectDataSet();
		
		public void startDocment() throws SAXException {
			//startup stuff. none needed yet.
		}
		
		@Override
		//localName is the value of the tag (e.g. login)
		//attributes is the stuff in the tag? (e.g. userID)?
		//method is called when parser gets to an opening tag
		public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {

			if (localName.equals("name")) {
				this.in_name = true;
			} else if (localName.equals("bookmarks")) {
				this.in_bookmarks = true;
			} else if (localName.equals("searches")) {
				this.in_searches = true;
			} else if (localName.equals("snippets")) {
				this.in_snippets = true;
			} else if (localName.equals("notes")) {
				this.in_notes = true;
			}
		}
		
		@Override
		//method is called when parser gets to a closing tag
		public void endElement (String nameSpaceURI, String localName, String qName) throws SAXException {
			
			if (localName.equals("name")) {
				this.in_name = false;
			} else if (localName.equals("bookmarks")) {
				this.in_bookmarks = false;
			} else if (localName.equals("searches")) {
				this.in_searches = false;
			} else if (localName.equals("snippets")) {
				this.in_snippets = false;
			} else if (localName.equals("notes")) {
				this.in_notes = false;
			}
		}
		
		@Override
		//retrieves the data within a tag and puts it into a String called data
		public void characters(char ch[], int start, int length) {
			
			String data = new String(ch, start, length);
			
			if (in_name) {
				projectData.setName(data);
			} else if (in_bookmarks) {
				projectData.setNumBookmarks(Integer.parseInt(data));
			} else if (in_searches) {
				projectData.setNumSearches(Integer.parseInt(data));
			} else if (in_snippets) {
				projectData.setNumSnippets(Integer.parseInt(data));
			} else if (in_notes) {
				projectData.setNumNotes(Integer.parseInt(data));
			}
			
		}
		
		public ProjectDataSet getParsedData() {
			return this.projectData;
		}
}


