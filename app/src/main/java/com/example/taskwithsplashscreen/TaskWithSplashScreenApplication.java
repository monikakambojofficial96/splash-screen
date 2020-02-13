package com.example.taskwithsplashscreen;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class TaskWithSplashScreenApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate ();


        Stetho.initializeWithDefaults(this);
    }
}
