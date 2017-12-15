package com.android.listpopwindow;

import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import com.android.listpoplibrary.ListPopWindowManager;
import com.bumptech.glide.Glide;
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
        ListPopWindowManager.getInStance().setShowImageListener(new ListPopWindowManager.showImageListener() {
            @Override
            public void showImage(Context context, String path, ImageView imageView) {
                Glide.with(context).load(path)
                        .asBitmap()
                        .placeholder(com.android.listpoplibrary.R.drawable.default_image)
                        .error(com.android.listpoplibrary.R.drawable.default_image)
                        .into(imageView);
            }
        });
    }
}
