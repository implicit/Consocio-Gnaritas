package com.coagmento.mobile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.coagmento.parsers.*;;

public class Coagmento extends Activity {
	
	//Declare preferences variable
	SharedPreferences prefs;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //Get sharedPrefs that are stored in file "preferences"
        prefs = getApplicationContext().getSharedPreferences("preferences", MODE_PRIVATE);
        
        //Extract int from "userID" field in prefs. If no data, userID = 0
        int userID = prefs.getInt("userID", 0);
        
        //If user ID is 0, load login screen
        if (userID == 0) {
        	setContentView(R.layout.login);
        	
        	//Set the 'register' text view to open browser and go to register site
        	TextView register = (TextView) findViewById(R.id.registerClick);
        	register.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.coagmento.org/index.php?page=signup")));
				}
			});
        	
        	//Set the login button to do login actions when clicked
        	Button login = (Button) findViewById(R.id.LoginButton);
        	login.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					//Pull strings from user name and password box and trim them
					EditText usernameBox = (EditText) findViewById(R.id.usernameInput);
		        	EditText passwordBox = (EditText) findViewById(R.id.passwordInput);
		        	String username = usernameBox.getText().toString().trim();
		        	String password = passwordBox.getText().toString().trim();
		        	
		        	//Execute login process using worker thread
		        	new Authenticate().execute(username, password);
				}	
			});
        	
        } else {
        	//User ID is already stored
        	//Get data from prefs and bundle it for home activity
        	Bundle appData = new Bundle();
        	appData.putString("username", prefs.getString("username", "defUser"));
        	appData.putInt("userID", prefs.getInt("userID", 0));
        	Intent home = new Intent(Coagmento.this, Home.class);
        	home.putExtras(appData);
        	
        	startActivity(home);
        }
    }
    
    
    
    
    /////////////// Async Task for Authentication process
    private class Authenticate extends AsyncTask<String, String, LoginDataSet> {

    	//Define progress dialog
    	ProgressDialog authSpinner = ProgressDialog.show(Coagmento.this, "", "Logging in...", true);
    	
		@Override
		protected void onPreExecute() {
			//Display progress dialog
			authSpinner.show();
			super.onPreExecute();
		}


		@Override
		protected LoginDataSet doInBackground(String... loginInfo) {
			//Get login data and authenticate with server
			LoginParser parser = new LoginParser();    	
        	LoginDataSet loginData = parser.parseLogin(loginInfo[0], loginInfo[1]);
        	
        	loginData.setLoginName(loginInfo[0]);
        	loginData.setPassword(loginInfo[1]);
			return loginData;
		}

		
		@Override
		protected void onPostExecute(LoginDataSet loginData) {
			
			prefs = getApplicationContext().getSharedPreferences("preferences", MODE_PRIVATE);
            SharedPreferences.Editor prefEdit = prefs.edit();
			
			//If server returns 0 or 176 for userID then login is invalid; display toast notification
			if (loginData.getUserID() == 0 || loginData.getUserID() == 176) {
        		Toast toast = Toast.makeText(getApplicationContext(), "Invalid username and/or password!", Toast.LENGTH_LONG);
        		toast.show();
        		authSpinner.dismiss();
        	} else {
        		//If the remember login box is checked, commit the userID to shared preferences
        		CheckBox rememberLogin = (CheckBox) findViewById(R.id.RememberLogin);
	        	if (rememberLogin.isChecked()) {
	                prefEdit.putString("username", loginData.getName());
	                prefEdit.putInt("userID", loginData.getUserID());
	                prefEdit.commit();
	        	}
	        	
	        	//Create bundle containing user information
	        	Bundle userData = new Bundle();
	        	userData.putString("username", loginData.getName());
	        	userData.putInt("userID", loginData.getUserID());
	        	
	        	prefEdit.putString("password", loginData.getPassword());
	        	prefEdit.putString("loginName", loginData.getLoginName());
	        	prefEdit.commit();
	        	
	        	Intent home = new Intent(Coagmento.this, Home.class);
	        	home.putExtras(userData);
	        	
	        	//Close progress dialog and save password to prefs
	        	authSpinner.dismiss();
	        	
	        	//Move to next screen
	        	startActivity(home);

        	}

		}
    }
}