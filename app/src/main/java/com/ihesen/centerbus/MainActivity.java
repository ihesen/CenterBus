package com.ihesen.centerbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.ichsy.centerbus.CenterBus;
import com.ichsy.centerbus.CenterEventBus;
import com.ihesen.centerbus.bus.TestCenterParams;
import com.ihesen.centerbus.bus.TestEvent;
import com.ihesen.centerbus.bus.TestManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test_bus();
            }
        });
    }

    private void test_bus() {
        TestCenterParams params = new TestCenterParams(TestEvent.FORWORD_PAGE, this);
        CenterEventBus.getInstance().addCallBack(params, new CenterBus.CenterCallBack<TestCenterParams>() {
            @Override
            public void callBack(TestCenterParams callBack) {
                Log.d("ihesen MainActivity : ", callBack.testStr);

            }
        }).postTask(params);
    }

}
