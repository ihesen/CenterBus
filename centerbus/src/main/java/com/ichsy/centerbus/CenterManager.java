package com.ichsy.centerbus;

/**
 * 模块manager
 * author: ihesen on 2016/6/29 14:29
 * email: hesen@ichsy.com
 */
public abstract class CenterManager<P extends CenterParams> {

    /**
     * 处理发送事件
     */
    public abstract void onEvent(P params);

    /**
     * 处理接收回传事件
     */
    public abstract void onRevert(String eventName, Object params);
}
