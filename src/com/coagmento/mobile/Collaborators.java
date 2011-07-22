package com.coagmento.mobile;

import java.util.LinkedList;

import com.coagmento.parsers.CollabListDataSet;
import com.coagmento.parsers.CollabListParser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

public class Collaborators extends Activity {
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collaborators);
    
        final Bundle appData = this.getIntent().getExtras();
        
        CollabListParser parser = new CollabListParser();
        LinkedList<CollabListDataSet> collabList = parser.parseCollabs(appData.getInt("userID"));
        
        TableLayout cListTable = (TableLayout) findViewById(R.id.collabListTable);
        
        for (CollabListDataSet collab : collabList) {
        	Button b = new Button(this);
        	b.setText(collab.getName());
        	
        	cListTable.addView(b);
        }
        
        //Set up button to go back to home
        Button homeButton = (Button) findViewById(R.id.collabhomeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent backToHome = new Intent(Collaborators.this, Home.class);
				backToHome.putExtras(appData);
				backToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(backToHome);
			}
		});
    }
    
    
}