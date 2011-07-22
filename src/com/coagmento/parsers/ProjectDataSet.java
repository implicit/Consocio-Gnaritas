package com.coagmento.parsers;

public class ProjectDataSet {
	private String name = "";
	private int bookmarks = 0;
	private int searches = 0;
	private int snippets = 0;
	private int notes = 0;
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public int getNumBookmarks() { return bookmarks; }
	public void setNumBookmarks(int bookmarks) { this.bookmarks = bookmarks; }

	public int getNumSearches() { return searches; }
	public void setNumSearches(int searches) { this.searches = searches; }

	public int getNumSnippets() { return snippets; }
	public void setNumSnippets(int snippets) { this.snippets = snippets; }

	public int getNumNotes() { return notes; }
	public void setNumNotes(int notes) { this.notes = notes; }
	
	@Override
	public String toString() {
		return "Name: " + this.name + " BM: " + this.bookmarks + 
		" S: " + this.searches + " SN: " + this.snippets + " N:" + this.notes;
	}
}
