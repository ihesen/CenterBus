package com.ihesen.centerbus;

import android.app.Application;

import com.ichsy.centerbus.CenterEventBus;
import com.ihesen.centerbus.bus.TestManager;

/**
 * author: ihesen on 2016/7/13 16:05
 * email: hesen@ichsy.com
 */
public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CenterEventBus.getInstance().register(TestManager.class);
    }
}
