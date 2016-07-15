package com.ichsy.centerbus;

import android.content.Context;

/**
 * manager发送消息所需参数
 * author: ihesen on 2016/6/29 16:38
 * email: hesen@ichsy.com
 */
public abstract class CenterParams<M extends CenterManager> {

    /**
     * 事件名称
     */
    private String mEventName;
    private Context mContext;
    /**
     * 唯一标识，主要用于标识事件回调使用
     */
    private String singleSign;

    public CenterParams(String eventName, Context context) {
        this.mContext = context;
        this.mEventName = eventName;
    }

    public String getEventName() {
        return mEventName;
    }

    public Context getContext() {
        return mContext;
    }

    /**
     * 返回自定义的模块名称(用的的module类的包类路径)
     */
    public String getModuleName() {
        return getManagerClass().getName();
    }

    /**
     * 返回自定义的模块名称
     */
    public abstract Class<M> getManagerClass();

    public String getSingleSign() {
        return singleSign;
    }

    public void setSingleSign(String singleSign) {
        this.singleSign = mEventName + singleSign;
    }
}
