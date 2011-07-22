package com.coagmento.parsers;

import java.util.LinkedList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ProjectListHandler extends DefaultHandler {
	private boolean in_projID = false;
	private boolean in_projName = false;

	LinkedList<ProjectListDataSet> projectList = new LinkedList<ProjectListDataSet>();
	
	public void startDocment() throws SAXException {
		//startup stuff. none needed.
	}
	
	@Override
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
		if (localName.equals("projID")) {
			this.in_projID = true;
		} else if (localName.equals("projName")) {
			this.in_projName = true;
		}
	}
	
	@Override
	public void endElement (String nameSpaceURI, String localName, String qName) throws SAXException {
		if (localName.equals("projID")) {
			this.in_projID = false;
		} else if (localName.equals("projName")) {
			this.in_projName = false;
		}
	}
	
	@Override
	public void characters(char ch[], int start, int length) {
		
		String data = new String(ch, start, length);
		
		if (this.in_projID) {
			projectList.add(new ProjectListDataSet());
			projectList.getLast().setProjID(Integer.parseInt(data));
		} else if (this.in_projName) {
			projectList.getLast().setName(data);
		}
	}
	
	public LinkedList<ProjectListDataSet> getParsedData() {
		return this.projectList;
	}

}
