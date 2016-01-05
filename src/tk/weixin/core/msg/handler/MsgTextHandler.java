package tk.weixin.core.msg.handler;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Element;

import tk.core.db.template.HibernateTemplateExt;
import tk.entities.msg.MsgText;
import tk.weixin.core.msg.AbstractMsgHandler;
import tk.weixin.core.msg.MsgContrant;

public class MsgTextHandler extends AbstractMsgHandler {

	@Override
	public void send(HttpServletRequest request, HttpServletResponse response) {
		
	}

	@Override
	public String recv(Element msgEle) {
		String fromUserName = msgEle.elementText("FromUserName");  
		String toUserName = msgEle.elementText("ToUserName");  
		String content = msgEle.elementTextTrim("Content");
		String msgId = msgEle.elementTextTrim("MsgId");
		String createTime = msgEle.elementTextTrim("CreateTime");
		String msgType = msgEle.elementTextTrim("MsgType");
		
		MsgText text = new MsgText();
		text.setContent(content);
		text.setContentType(MsgContrant.MSG_CONTENT_TYPE_RECV);
		text.setCreateTime(new Timestamp(Long.valueOf(createTime) * 1000));
		text.setFromUserName(fromUserName);
		text.setToUserName(toUserName);
		text.setMsgId(msgId);
		text.setMsgType(msgType);
		HibernateTemplateExt.getInstance().save(text);
		
		String time = String.valueOf(new Date().getTime());  
		String textTpl = "<xml>"+  
				"<ToUserName><![CDATA[%1$s]]></ToUserName>"+  
				"<FromUserName><![CDATA[%2$s]]></FromUserName>"+  
				"<CreateTime>%3$s</CreateTime>"+  
				"<MsgType><![CDATA[%4$s]]></MsgType>"+  
				"<Content><![CDATA[%5$s]]></Content>"+  
				"<FuncFlag>0</FuncFlag>"+  
				"</xml>";         
		String resultStr = null;
		if(null!=content&&!content.equals("")){  
			String contentStr = null;
			if(content.equals("tk")){
				contentStr = "http://120.24.63.30/TKManage/depart/sh2p/tk2y-kd92h.od?cd=" + fromUserName;  
			}else{
				contentStr = "success"; 
			}
			msgType = "text"; 
			resultStr = String.format(textTpl, fromUserName, toUserName, time, msgType, contentStr);  
		}
		return resultStr;
	}
}
