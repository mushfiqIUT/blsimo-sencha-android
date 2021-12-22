/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.portonics.Bellissimo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import org.apache.cordova.*;

import com.portonics.Bellissimo.SocialShare;

public class Bellissimo extends DroidGap
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	Log.e("whole init method called", "What the hell is that");
        super.onCreate(savedInstanceState);
        super.init(); // Calling this is necessary to make this work
        appView.addJavascriptInterface(this, "MainActivity");
        
        super.setIntegerProperty("loadUrlTimeoutValue", 70000);
        super.loadUrl("file:///android_asset/www/index.html");
        
        if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {     
            //Toast.makeText(this, "Large screen",Toast.LENGTH_LONG).show();

        }
        else if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {     
            //Toast.makeText(this, "Normal sized screen" , Toast.LENGTH_LONG).show();
            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } 
        else if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {     
            //Toast.makeText(this, "Small sized screen" , Toast.LENGTH_LONG).show();
            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        else {
            //Toast.makeText(this, "Screen size is neither large, normal or small" , Toast.LENGTH_LONG).show();
        }
        //super.onCreate(savedInstanceState);
        //super.loadUrl("file:///android_asset/www/index.html");
    }
    
    @JavascriptInterface
    public void DroidGapSocialShare(String value, int shareType) {
        Log.e("Custom Function Called", value);
        
        Intent mIntent = new Intent(this,SocialShare.class);
        mIntent.putExtra("urllink", value); 
        mIntent.putExtra("type", shareType); 
        
		mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		this.startActivity(mIntent);
		
    }
}

