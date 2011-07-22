package com.coagmento.parsers;

public class ProjectListDataSet {
	protected String name = null;
	protected int projID = 0;
	
	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }
	
	public int getProjID() { return this.projID; }
	public void setProjID(int ID) { this.projID = ID; }
	
	@Override
	public String toString() {
		return "Project Name: " + this.name + " ID: " + this.projID;
	}
}
