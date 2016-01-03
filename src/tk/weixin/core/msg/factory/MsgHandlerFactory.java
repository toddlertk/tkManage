package tk.weixin.core.msg.factory;

import java.util.Map;

import tk.core.context.SpringBeanLoader;
import tk.weixin.core.msg.AbstractMsgHandler;

public class MsgHandlerFactory {
	
	private Map<String , AbstractMsgHandler> msgHandlerMap;

	public static MsgHandlerFactory getInstance(){
		return (MsgHandlerFactory)SpringBeanLoader.getBean("msgHandlerFactory");
	}

	public AbstractMsgHandler getMsgHandler(String key){
		return msgHandlerMap.get(key);
	}
	
	public Map<String , AbstractMsgHandler> getMsgHandlerMap() {
		return msgHandlerMap;
	}

	public void setMsgHandlerMap(Map<String , AbstractMsgHandler> msgHandlerMap) {
		this.msgHandlerMap = msgHandlerMap;
	}
}
