package com.example.bicired;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class BiciredApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        AppEventsLogger.activateApp(this);
        }
    }

