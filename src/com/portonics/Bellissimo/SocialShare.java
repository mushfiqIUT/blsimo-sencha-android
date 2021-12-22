package com.portonics.Bellissimo;

import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthAdapter.Provider;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SocialShare extends Activity {

	SocialAuthAdapter adapter;
	private Provider[] providers = new Provider[] {Provider.FACEBOOK, Provider.TWITTER, Provider.LINKEDIN};
	
	int shareType= -1;
	Activity activity = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_social_share);
		
		Bundle b = getIntent().getExtras();
		String val = b.getString("urllink");
	    shareType = b.getInt("type");
		
		// Welcome Message
        TextView textview = (TextView)findViewById(R.id.text);
        //textview.setText("Welcome to SocialAuth Demo. We are sharing text SocialAuth Android by Share Bar.");
        textview.setText(val);
        
      //Create Your Own Share Button
        Button share = (Button)findViewById(R.id.sharebutton);
        share.setText("Share");
        share.setTextColor(Color.WHITE);
        share.setBackgroundResource(R.drawable.button_gradient);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to do
            	 SocialSharing(shareType);
            }
        });
        
       // LinearLayout bar = (LinearLayout)findViewById(R.id.linearbar);
       // bar.setBackgroundResource(R.drawable.bar_gradient);
        
        // Add Bar to library
        adapter = new SocialAuthAdapter(new ResponseListener());
        
        // Add providers
        adapter.addProvider(Provider.FACEBOOK, R.drawable.facebook);
        adapter.addProvider(Provider.TWITTER, R.drawable.twitter);
        adapter.addProvider(Provider.LINKEDIN, R.drawable.linkedin);
       // adapter.addProvider(Provider.MYSPACE, R.drawable.myspace);
       //adapter.enable(bar);
        				
        // Set Size of Dialog
        adapter.setDialogSize(30,60);
	}

	/**
	 * Listens Response from Library
	 * 
	 */
	
	public final class ResponseListener implements DialogListener 
    {
		@Override
		public void onComplete(Bundle values) {
     	    
			// Variable to receive message status
	
			Log.d("ShareBar" , "Authentication Successful");
			
			// Get name of provider after authentication
			String providerName = values.getString(SocialAuthAdapter.PROVIDER);
			Log.d("ShareBar", "Provider Name = " + providerName);
			 
			TextView textview = (TextView)findViewById(R.id.text);
			// Please avoid sending duplicate message. Social Media Providers block duplicate messages.
			adapter.updateStatus(""+textview.getText());	
			
			//Toast.makeText(SocialShare.this, "Message posted on " + providerName, Toast.LENGTH_SHORT).show();	
			
			finish();
         }

         @Override
		public void onError(SocialAuthError error) {
        	error.printStackTrace();
        	Log.d("ShareBar" , error.getMessage());
        	
        	//Toast.makeText(SocialShare.this, "Message posted error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
        	
        	finish();
         }

         @Override
		public void onCancel() {
         	Log.d("ShareBar" , "Authentication Cancelled");
         	
         	//Toast.makeText(SocialShare.this, "Authentication Cancelled", Toast.LENGTH_SHORT).show();
         	finish();
         }

		@Override
		public void onBack() {
			// TODO Auto-generated method stub
			
		}

     }
	
	public void SocialSharing(int position) {
		// This method will enable the selected provider
		adapter.authorize(this, providers[position]);
	}

}

