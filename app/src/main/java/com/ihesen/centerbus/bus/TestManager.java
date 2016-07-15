package com.ihesen.centerbus.bus;

import android.content.Intent;

import com.ichsy.centerbus.CenterEventBus;
import com.ichsy.centerbus.CenterManager;
import com.ichsy.centerbus.CenterParams;
import com.ihesen.centerbus.TestActivity;

/**
 * author: ihesen on 2016/6/30 16:19
 * email: hesen@ichsy.com
 */
public class TestManager extends CenterManager<TestCenterParams> {

    private TestCenterParams mParams;

    @Override
    public void onEvent(TestCenterParams params) {
        this.mParams = params;
        if (params.getEventName().equals(TestEvent.FORWORD_PAGE)) {
            openTestActivity(params);
        }
    }

    @Override
    public void onRevert(String eventName, Object params) {
        if (eventName.equals(TestEvent.FORWORD_PAGE)) {
            //接收到事件处理完成的回调，通知总线事件处理完成，调用者将收到处理完成回调
            mParams.testStr = (String) params;
            CenterEventBus.getInstance().complated(mParams);
        }
    }

    private void openTestActivity(CenterParams centerParams) {
        Intent intent = new Intent(centerParams.getContext(), TestActivity.class);
        intent.putExtra("eventName", centerParams.getEventName());
        centerParams.getContext().startActivity(intent);
    }
}
