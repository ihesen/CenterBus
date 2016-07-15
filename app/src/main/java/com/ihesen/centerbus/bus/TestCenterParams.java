package com.ihesen.centerbus.bus;

import android.content.Context;

import com.ichsy.centerbus.CenterParams;

/**
 * author: ihesen on 2016/6/30 16:19
 * email: hesen@ichsy.com
 */
public class TestCenterParams extends CenterParams<TestManager> {

    public String testStr;

    public TestCenterParams(String eventName, Context context) {
        super(eventName, context);
    }

    @Override
    public Class<TestManager> getManagerClass() {
        return TestManager.class;
    }

}
