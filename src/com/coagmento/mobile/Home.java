package com.coagmento.mobile;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends Activity {
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    
        //Get bundle that was passed from previous activity; contains userID and userName
        final Bundle appData = this.getIntent().getExtras();
        
        //Grab user name from bundle and display on textView in top bar
        TextView usernameText = (TextView) findViewById(R.id.homeUsernameText);
        usernameText.append(appData.getString("username"));
        
        
        //Set up log out button
        Button logout = (Button) findViewById(R.id.homeLogoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Retrieve preferences and replace username with blank string and userID with 0, then go back to login
				SharedPreferences prefs = getApplicationContext().getSharedPreferences("preferences", MODE_PRIVATE);
				SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putString("username", "");
                prefEdit.putInt("userID", 0);
                prefEdit.commit();
                
                //Create new intent with flag 'clear top'
                //This is so user can't use back button to go back once logged out
                Intent logout = new Intent(Home.this, Coagmento.class);
                logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logout);
			}
		});
      //Set up log out button text
        TextView logoutText = (TextView) findViewById(R.id.logoutText);
        logoutText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Retrieve preferences and replace username with blank string and userID with 0, then go back to login
				SharedPreferences prefs = getApplicationContext().getSharedPreferences("preferences", MODE_PRIVATE);
				SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putString("username", "");
                prefEdit.putInt("userID", 0);
                prefEdit.commit();
                
                //Create new intent with flag 'clear top'
                //This is so user can't use back button to go back once logged out
                Intent logout = new Intent(Home.this, Coagmento.class);
                logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logout);
			}
		});
        
        
        //Set up projects button
        Button proj = (Button) findViewById(R.id.projButton);
        proj.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Pass bundle containing user information to project list
				Intent projectList = new Intent(Home.this, Projects.class);
				projectList.putExtras(appData);
				
				startActivity(projectList);
			}
		});
        
      //Set up collaborators button
        Button collabs = (Button) findViewById(R.id.collabButton);
        collabs.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Pass bundle containing user information to collaborators
				Intent collaborators = new Intent(Home.this, Collaborators.class);
				collaborators.putExtras(appData);
				startActivity(collaborators);
			}
		});
    }
}