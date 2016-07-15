package com.ihesen.centerbus;

import android.app.Activity;
import android.os.Bundle;

import com.ichsy.centerbus.CenterEventBus;
import com.ihesen.centerbus.bus.TestManager;

/**
 * author: ihesen on 2016/6/30 16:13
 * email: hesen@ichsy.com
 */
public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        String str = "能拿到么？";
        String eventName = getIntent().getStringExtra("eventName");
        //处理完成之后,回传给TestManager
        CenterEventBus.getInstance().revert(TestManager.class, eventName, str);
    }
}
