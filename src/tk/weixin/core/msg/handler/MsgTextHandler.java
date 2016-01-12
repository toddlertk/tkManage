package tk.weixin.core.msg.handler;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

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
		String contentStr = null;
		if(null!=content&&!content.equals("")){  
			if(content.equals("tk")){
				String url = "<a href=\"http://120.24.63.30/TKManage/ph/depart/tk2y-kd92h.od?openId=" + fromUserName + "\">";
				contentStr = "请" + url + "点击此处</a>进入微信与部门工号绑定，想要参与抽奖吗，想要神秘大奖吗？那就赶紧来动动手指"
						+ url + "进来绑定</a>吧~";  
			}else{
				contentStr = "success"; 
			}
			msgType = "text"; 
			resultStr = String.format(textTpl, fromUserName, toUserName, time, msgType, contentStr);  
		}
		text.setContent(contentStr);
		text.setTableMsgId(UUID.randomUUID().toString().replace("-", ""));
		text.setContentType(MsgContrant.MSG_CONTENT_TYPE_SEND);
		HibernateTemplateExt.getInstance().save(text);
		return resultStr;
	}
}
