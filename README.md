# CenterBus
总线 事件统一处理

CenterBus 事件跳转总线介绍

使用步骤：
1、 定义模块 manager
首页定义好各个模块的Manager，所有模块的事件跳转可统一通过该manager进行处理
定义的模块manager需要继承总线CenterManager,并实现onEvent()方法

example:
String eventName = "open_event";
public class ProductManager extends CenterManager<ProductParams>{

    @Override
    public void onEvent(Params centerParams) {
        if(centerParams.getEventName().equals(eventName)){
            //逻辑处理,用于接收总线消息事件
            ..
        }
   	}

   	@Override
    public void onRevert(String eventName, Object params) {
        if(centerParams.getEventName().equals(eventName)){
            //用于接收某个Event完成后的回传，需要在Event完成后调用 CenterEventBus.getInstance().revert(CenterManager.class, eventName, object);
        }
    }

}
注意：eventName:主要用于标记事件


2、定义事件参数
事件跳转需要业务参数，自定义各个模块所需参数，继承Params类：
example:
public class ProductParams extends Params<ProductManager>{

	public ProductParams(String keyFunction) {
		super(keyFunction);
	}
	//业务所需参数
	..

	@Override
    public Class<ProductManager> getManagerClass() {
        return ProductManager.class;
    }
}


3、初始化操作
在应用Application 中进行初始化各个模块

example:
CenterEventBus.getInstance().register(ProductManager.class);


4、事件跳转总线使用
String eventName = "open_product";
ProductParams centerParams = new ProductParams(eventName, context);
CenterEventBus.getInstance().postTask(centerParams);
发送此消息后，在ProductManager的onEvent()方法中就会接受到，统一处理
如，需要事件处理完成后，后续有某些操作可以添加一个CallBack
CenterEventBus.getInstance().addCallBack(CenterBus.CenterCallBack).postTask(centerParams);
此时在事件完成后则需要调用CenterEventBus.getInstance().complated(CenterParams);之后便会受到完成事件后的回调
