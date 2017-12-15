package com.android.listpopwindow;

import android.app.Application;

import com.android.listpoplibrary.ListPopWindowManager;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by static on 2017/12/13/013.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        ListPopWindowManager.getInStance().setPopWindowColor(R.color.gary);
        ListPopWindowManager.getInStance().setTextColor(R.color.white);
    }
}
