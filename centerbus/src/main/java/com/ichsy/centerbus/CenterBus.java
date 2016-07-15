package com.ichsy.centerbus;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * 事件跳转总线
 * author: ihesen on 2016/6/29 14:18
 * email: hesen@ichsy.com
 */
public class CenterBus {

    private final String TAG = "CenterBus";
    private static CenterBus mInstance;
    /**
     * 存储各个module
     */
    private Map<String, CenterManager<CenterParams>> mCenterManagers = new HashMap<>();

    public static CenterBus getInstance() {
        if (mInstance == null) {
            synchronized (CenterBus.class) {
                if (mInstance == null) {
                    mInstance = new CenterBus();
                }
            }
        }
        return mInstance;
    }

    /**
     * 添加模块Manager
     */
    public synchronized void addManager(CenterManager manager) {
        if (!mCenterManagers.containsKey(manager.getClass().getName())) {
            mCenterManagers.put(manager.getClass().getName(), manager);
        }
    }

    /**
     * 删除模块
     */
    public synchronized void delManager(Class<? extends CenterManager> muduleClazz) {
        if (mCenterManagers.containsKey(muduleClazz.getName())) {
            mCenterManagers.remove(muduleClazz.getName());
        }
    }

    /**
     * 发送消息
     */
    public void post(CenterParams centerParams) {
        synchronized (this) {
            if (mCenterManagers.containsKey(centerParams.getModuleName())) {
                mCenterManagers.get(centerParams.getModuleName()).onEvent(centerParams);
            } else {
                Log.e(TAG, "not find defined center manager type! ");
            }
        }
    }

    public void revert(Class<? extends CenterManager> muduleClazz, String eventName, Object obj) {
        synchronized (this) {
            if (mCenterManagers.containsKey(muduleClazz.getName())) {
                mCenterManagers.get(muduleClazz.getName()).onRevert(eventName, obj);
            } else {
                Log.e(TAG, "not find defined center manager type! ");
            }
        }
    }

    private Map<String, CenterCallBack<? extends CenterParams>> mCenterEventCallBack = new HashMap<>();

    public interface CenterCallBack<T extends CenterParams> {
        void callBack(T callBack);
    }

    public void addCallBack(CenterParams centerParams, CenterCallBack<? extends CenterParams> callBack) {
        centerParams.setSingleSign(System.currentTimeMillis() + "");
        mCenterEventCallBack.put(centerParams.getSingleSign(), callBack);
    }

    public CenterCallBack getCenterCallBack(CenterParams centerParams) {
        if (mCenterEventCallBack.containsKey(centerParams.getSingleSign())) {
            return mCenterEventCallBack.get(centerParams.getSingleSign());
        } else {
            Log.e(TAG, "not add the eventName CenterCallBack! ");
            return null;
        }
    }

    public void removeCallBack(CenterParams centerParams) {
        if (mCenterEventCallBack.containsKey(centerParams.getEventName())) {
            mCenterEventCallBack.remove(centerParams.getEventName());
        }
    }
}
