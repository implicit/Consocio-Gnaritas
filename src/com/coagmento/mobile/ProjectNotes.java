package com.coagmento.mobile;


import java.util.LinkedList;

import com.coagmento.parsers.NoteDataSet;
import com.coagmento.parsers.NoteParser;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

public class ProjectNotes extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes);
        
        final Bundle appData = this.getIntent().getExtras();
        
        NoteParser parser = new NoteParser();
        LinkedList<NoteDataSet> notes = parser.parseNotes(appData.getInt("userID"),appData.getInt("projID"));
        
        TableLayout noteTable = (TableLayout) findViewById(R.id.notesListTable);
        
        
        
         for (NoteDataSet n : notes) {
        	Button b = new Button(this);
        	b.setText(n.getText());
        	b.setTag(n);
        	
        	b.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					 Dialog dialog = new Dialog(ProjectNotes.this);
		             dialog.setContentView(R.layout.notedialog);
		             
		             NoteDataSet data = (NoteDataSet) v.getTag();
		             
		             TextView nId = (TextView) dialog.findViewById(R.id.nDialogId);
		             TextView nDate = (TextView) dialog.findViewById(R.id.nDialogDate);
		             TextView nTime = (TextView) dialog.findViewById(R.id.nDialogTime);
		             TextView nText = (TextView) dialog.findViewById(R.id.nDialogText);
		             nId.append(data.getId());
		             nDate.append(data.getDate());
		             nTime.append(data.getTime());
		             nText.append(data.getText());
		             
		             dialog.setCanceledOnTouchOutside(true);
		             dialog.setTitle("Note");
		             dialog.show();
				}
			});
        	
        	noteTable.addView(b);
        }
 
         
        
        //Set up button to go back to home
        Button homeButton = (Button) findViewById(R.id.noteshomeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent backToHome = new Intent(ProjectNotes.this, Home.class);
				backToHome.putExtras(appData);
				backToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(backToHome);
			}
		});
    }

}