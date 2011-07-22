package com.coagmento.mobile;


import java.util.LinkedList;

import com.coagmento.parsers.SearchDataSet;
import com.coagmento.parsers.SearchParser;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

public class ProjectSearches extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searches);
        
        final Bundle appData = this.getIntent().getExtras();
        
        SearchParser parser = new SearchParser();
        LinkedList<SearchDataSet> searches = parser.parseSearches(appData.getInt("userID"),appData.getInt("projID"));
        
        TableLayout searchTable = (TableLayout) findViewById(R.id.searchListTable);

        
        for (SearchDataSet search : searches) {
        	Button b = new Button(this);
        	
        	b.setText(search.getQuery());
        	b.setTag(search);
        	
        	b.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					 Dialog dialog = new Dialog(ProjectSearches.this);
		             dialog.setContentView(R.layout.searchdialog);
		             
		             SearchDataSet data = (SearchDataSet) v.getTag();
		             
		             TextView searchQuery = (TextView) dialog.findViewById(R.id.searchDialogQuery);
		             TextView searchSource = (TextView) dialog.findViewById(R.id.searchDialogSource);
		             TextView searchDate = (TextView) dialog.findViewById(R.id.searchDialogDate);
		             TextView searchTime = (TextView) dialog.findViewById(R.id.searchDialogTime);
		             TextView searchURL = (TextView) dialog.findViewById(R.id.searchDialogURL);
		             searchQuery.append(data.getDate());
		             searchSource.append(data.getSource());
		             searchDate.append(data.getDate());
		             searchTime.append(data.getTime());
		             searchURL.append(data.getUrl());
		             searchURL.setTag(data.getUrl());
		             searchURL.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v2) {
							Intent searchToBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(v2.getTag().toString()));
							startActivity(searchToBrowser);
						}
					});
		             
		             dialog.setCanceledOnTouchOutside(true);
		             dialog.setTitle("Search");
		             dialog.show();
				}
			});
        	
        	searchTable.addView(b);
        }
        
        //Set up button to go back to home
        Button homeButton = (Button) findViewById(R.id.searchhomeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent backToHome = new Intent(ProjectSearches.this, Home.class);
				backToHome.putExtras(appData);
				backToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(backToHome);
			}
		});

    }
}