package com.ichsy.centerbus;

/**
 * 事件跳转总线
 * author: ihesen on 2016/6/29 17:33
 * email: hesen@ichsy.com
 */
public class CenterEventBus {

    private static CenterEventBus mInstance;

    public static CenterEventBus getInstance() {
        if (mInstance == null) {
            synchronized (CenterEventBus.class) {
                if (mInstance == null) {
                    mInstance = new CenterEventBus();
                }
            }
        }
        return mInstance;
    }

    /**
     * 注册模块manager
     */
    public void register(Class<? extends CenterManager> managerClazz) {
        try {
            CenterBus.getInstance().addManager(managerClazz.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册模块managers
     */
    public void registers(Class<? extends CenterManager>[] managerClazzs) {
        for (Class<? extends CenterManager> manager : managerClazzs) {
            register(manager);
        }
    }

    /**
     * 销毁模块manager
     */
    public void unregister(Class<? extends CenterManager> managerClazz) {
        CenterBus.getInstance().delManager(managerClazz);
    }

    public void postTask(CenterParams centerParams) {
        CenterBus.getInstance().post(centerParams);
    }

    /**
     * 事件处理完成，回传给响应模块，处理结果数据
     *
     * @param muduleClazz 通知回传模块类
     * @param eventName   通知事件名称
     * @param obj         回传数据对象
     */
    public void revert(Class<? extends CenterManager> muduleClazz, String eventName, Object obj) {
        CenterBus.getInstance().revert(muduleClazz, eventName, obj);
    }

    public CenterEventBus addCallBack(CenterParams centerParams, CenterBus.CenterCallBack<? extends CenterParams> callBack) {
        CenterBus.getInstance().addCallBack(centerParams, callBack);
        return getInstance();
    }

    public void complated(CenterParams centerParams) {
        CenterBus.CenterCallBack callBack = CenterBus.getInstance().getCenterCallBack(centerParams);
        if (callBack != null) {
            callBack.callBack(centerParams);
        }
        CenterBus.getInstance().removeCallBack(centerParams);
    }
}
