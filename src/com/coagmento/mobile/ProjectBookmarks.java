package com.coagmento.mobile;


import java.util.LinkedList;

import com.coagmento.parsers.BookmarkDataSet;
import com.coagmento.parsers.BookmarkParser;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

public class ProjectBookmarks extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmarks);
        
        final Bundle appData = this.getIntent().getExtras();
        
        BookmarkParser parser = new BookmarkParser();
        LinkedList<BookmarkDataSet> bookmarks = parser.parseBookmarks(appData.getInt("userID"),appData.getInt("projID"));
        
        TableLayout bmTable = (TableLayout) findViewById(R.id.bmarkListTable);
        
        for (BookmarkDataSet bm : bookmarks) {
        	Button b = new Button(this);
        	b.setText(bm.getTitle());
        	b.setTag(bm);
        	
        	b.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					 Dialog dialog = new Dialog(ProjectBookmarks.this);
		             dialog.setContentView(R.layout.bookmarkdialog);
		             
		             BookmarkDataSet data = (BookmarkDataSet) v.getTag();
		             
		             TextView bmDate = (TextView) dialog.findViewById(R.id.bmDialogDate);
		             TextView bmTime = (TextView) dialog.findViewById(R.id.bmDialogTime);
		             TextView bmURL = (TextView) dialog.findViewById(R.id.bmDialogURL);
		             bmDate.append(data.getDate());
		             bmTime.append(data.getTime());
		             bmURL.append(data.getUrl());
		             bmURL.setTag(data.getUrl());
		             bmURL.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v2) {
							Intent bookmarkToBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(v2.getTag().toString()));
							startActivity(bookmarkToBrowser);
						}
					});
		             
		             dialog.setCanceledOnTouchOutside(true);
		             dialog.setTitle("Bookmark");
		             dialog.show();
				}
			});
        	
        	bmTable.addView(b);
        }
 
        //Set up button to go back to home
        Button homeButton = (Button) findViewById(R.id.bookmarkhomeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent backToHome = new Intent(ProjectBookmarks.this, Home.class);
				backToHome.putExtras(appData);
				backToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(backToHome);
			}
		});
        
    }

}