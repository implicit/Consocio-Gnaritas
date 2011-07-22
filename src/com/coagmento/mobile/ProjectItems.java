package com.coagmento.mobile;


import com.coagmento.parsers.ProjectDataParser;
import com.coagmento.parsers.ProjectDataSet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProjectItems extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.projectitems);
        
        final Bundle appData = this.getIntent().getExtras();
        
        ProjectDataParser parser = new ProjectDataParser();
        ProjectDataSet projectDataList = parser.parseProjData(appData.getInt("userID"),appData.getInt("projID"));

    	TextView bookmarks = (TextView) findViewById(R.id.numBookmarks);
        TextView notes = (TextView) findViewById(R.id.numNotes);
        TextView snippets = (TextView) findViewById(R.id.numSnippets);
        TextView searches = (TextView) findViewById(R.id.numSearches);

        bookmarks.append(" (" + projectDataList.getNumBookmarks() + ")");
        notes.append(" (" + projectDataList.getNumNotes() + ")");
        snippets.append(" (" + projectDataList.getNumSnippets() + ")");
        searches.append(" (" + projectDataList.getNumSearches() + ")");
    	        
        Button bookmarksButton = (Button) findViewById(R.id.bookmarks);
        bookmarksButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent toBookmarks = new Intent(ProjectItems.this,ProjectBookmarks.class);
				toBookmarks.putExtras(appData);
				startActivity(toBookmarks);
			}
		});
        
        Button notesButton = (Button) findViewById(R.id.notes);
        notesButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent toNotes= new Intent(ProjectItems.this,ProjectNotes.class);
				toNotes.putExtras(appData);
				startActivity(toNotes);
			}
		});
        
        Button snippetsButton = (Button) findViewById(R.id.snippets);
        snippetsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent toSnippets = new Intent(ProjectItems.this,ProjectSnippets.class);
				toSnippets.putExtras(appData);
				startActivity(toSnippets);
			}
		});
        
        Button searchesButton = (Button) findViewById(R.id.searches);
        searchesButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent toSearches = new Intent(ProjectItems.this,ProjectSearches.class);
				toSearches.putExtras(appData);
				startActivity(toSearches);
			}
		});
        
        Button chatButton = (Button) findViewById(R.id.chat);
        chatButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent toChat = new Intent(ProjectItems.this, ProjectChat.class);
				toChat.putExtras(appData);
				startActivity(toChat);
			}
		});
        
       
        
        
        Button homeButton = (Button) findViewById(R.id.projitemshomeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent backToHome = new Intent(ProjectItems.this, Home.class);
				backToHome.putExtras(appData);
				backToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(backToHome);
			}
		});
		
        
        Button refreshButton = (Button) findViewById(R.id.projitemsRefreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast toast = Toast.makeText(getApplicationContext(), "Not implemented yet", Toast.LENGTH_SHORT);
        		toast.show();
			}
		});
 
    }

}