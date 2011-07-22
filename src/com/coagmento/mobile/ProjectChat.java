package com.coagmento.mobile;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
//import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;



public class ProjectChat extends Activity
{
	WebView cWebView;

	public void onCreate(Bundle savedInstanceState)
{
	super.onCreate(savedInstanceState);
	setContentView(R.layout.chat);

	SharedPreferences prefs = getApplicationContext().getSharedPreferences("preferences", MODE_PRIVATE);
	
	final Bundle appData = this.getIntent().getExtras();
	
	CookieSyncManager.createInstance(this); 
    CookieManager cookieManager = CookieManager.getInstance();
    cookieManager.removeAllCookie();
	
	cWebView = (WebView) findViewById(R.id.chatView);
	cWebView.getSettings().setJavaScriptEnabled(true);
	cWebView.loadUrl("http://www.coagmento.org/CSpace/getChat.php?userName=" + prefs.getString("loginName", "defUser") + "&password=" + prefs.getString("password", "") + "&projectID=" + appData.getInt("projID"));
	
	String test1 = prefs.getString("loginName", "");
	String test2 = prefs.getString("password", "");
	int test3 = appData.getInt("projID");
	System.out.println();

	cWebView.setWebViewClient(new ChatWebViewClient());
	
	//Set up button to go back to home
    Button homeButton = (Button) findViewById(R.id.chathomeButton);
    homeButton.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent backToHome = new Intent(ProjectChat.this, Home.class);
			backToHome.putExtras(appData);
			backToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(backToHome);
		}
	});
    
    //Set up button to go back to project items
    Button backButton = (Button) findViewById(R.id.chatBackButton);
    backButton.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			Intent backToProjectItems = new Intent(ProjectChat.this, ProjectItems.class);
			backToProjectItems.putExtras(appData);
			backToProjectItems.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(backToProjectItems);
		}
	});

}


private class ChatWebViewClient extends WebViewClient
{
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url)
	{
		view.loadUrl(url);
		return true;
	}

}

public boolean onKeyDown(int keyCode, KeyEvent event)
{
	if ((keyCode == KeyEvent.KEYCODE_BACK) && cWebView.canGoBack())
	{
		cWebView.goBack();
		return true;
	}
	return super.onKeyDown(keyCode, event);
}


}