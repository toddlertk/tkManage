package tk.weixin.core.msg.handler;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Element;

import tk.core.db.template.HibernateTemplateExt;
import tk.entities.msg.MsgEventSubscribe;
import tk.entities.msg.MsgLink;
import tk.entities.msg.MsgText;
import tk.weixin.core.msg.AbstractMsgHandler;
import tk.weixin.core.msg.MsgContrant;

public class MsgEventSubscribeHandler extends AbstractMsgHandler {

	@Override
	public void send(HttpServletRequest request, HttpServletResponse response) {
		
	}

	@Override
	public String recv(Element msgEle) {
		String fromUserName = msgEle.elementText("FromUserName");  
		String toUserName = msgEle.elementText("ToUserName");  
		String createTime = msgEle.elementTextTrim("CreateTime");
		String msgType = msgEle.elementTextTrim("MsgType");
		String ticket = msgEle.elementTextTrim("Ticket");
		String eventKey = msgEle.elementTextTrim("EventKey");
		String event = msgEle.elementTextTrim("Event");

		MsgEventSubscribe msg = new MsgEventSubscribe();
		msg.setCreateTime(new Timestamp(Long.valueOf(createTime) * 1000));
		msg.setFromUserName(fromUserName);
		msg.setToUserName(toUserName);
		msg.setMsgType(msgType);
		msg.setEvent(event);
		msg.setEventKey(eventKey);
		msg.setTicket(ticket);
		
		HibernateTemplateExt.getInstance().save(msg);

		String resultStr = null;
		return resultStr;
	}
}
