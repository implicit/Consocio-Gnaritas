package com.coagmento.mobile;


import java.util.LinkedList;

import com.coagmento.parsers.SnippetDataSet;
import com.coagmento.parsers.SnippetParser;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

public class ProjectSnippets extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snips);
        
        final Bundle appData = this.getIntent().getExtras();
        
        SnippetParser parser = new SnippetParser();
        LinkedList<SnippetDataSet> snippets = parser.parseSnippets(appData.getInt("userID"),appData.getInt("projID"));
        
        TableLayout snipTable = (TableLayout) findViewById(R.id.snipListTable);
        
        for (SnippetDataSet snip : snippets) {
        	Button b = new Button(this);
        	b.setText(snip.getTitle());
        	b.setTag(snip);
        	
        	b.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					 Dialog dialog = new Dialog(ProjectSnippets.this);
		             dialog.setContentView(R.layout.snippetdialog);
		             
		             SnippetDataSet data = (SnippetDataSet) v.getTag();
		             
		             TextView snipDate = (TextView) dialog.findViewById(R.id.snipDialogDate);
		             TextView snipTime = (TextView) dialog.findViewById(R.id.snipDialogTime);
		             TextView snipURL = (TextView) dialog.findViewById(R.id.snipDialogURL);
		             TextView snipContent = (TextView) dialog.findViewById(R.id.snipDialogContent);
		             TextView snipNote = (TextView) dialog.findViewById(R.id.snipDialogNotes);
		             
		             snipNote.append(data.getNote());
		             snipDate.append(data.getDate());
		             snipTime.append(data.getTime());
		             snipURL.append(data.getUrl());
		             snipURL.setTag(data.getUrl());
		             snipURL.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v2) {
							Intent snippetToBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(v2.getTag().toString()));
							startActivity(snippetToBrowser);
						}
					});
		             snipContent.append(data.getContent());
		             
		             
		             dialog.setCanceledOnTouchOutside(true);
		             dialog.setTitle("Snippet");
		             dialog.show();
				}
			});
        	
        	snipTable.addView(b);
        }
        
        //Set up button to go back to home
        Button homeButton = (Button) findViewById(R.id.snipshomeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent backToHome = new Intent(ProjectSnippets.this, Home.class);
				backToHome.putExtras(appData);
				backToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(backToHome);
			}
		});
    }

}