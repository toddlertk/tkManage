package tk.weixin.core.msg.handler;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Element;

import tk.core.db.template.HibernateTemplateExt;
import tk.entities.msg.MsgEventLocaltion;
import tk.entities.msg.MsgLink;
import tk.entities.msg.MsgText;
import tk.weixin.core.msg.AbstractMsgHandler;
import tk.weixin.core.msg.MsgContrant;

public class MsgEventLocationHandler extends AbstractMsgHandler {

	@Override
	public void send(HttpServletRequest request, HttpServletResponse response) {
		
	}

	@Override
	public String recv(Element msgEle) {
		String fromUserName = msgEle.elementText("FromUserName");  
		String toUserName = msgEle.elementText("ToUserName");  
		String msgId = msgEle.elementTextTrim("MsgId");
		String createTime = msgEle.elementTextTrim("CreateTime");
		String msgType = msgEle.elementTextTrim("MsgType");

		String event = msgEle.elementTextTrim("Event");
		String latitude = msgEle.elementTextTrim("Latitude");
		String longitude = msgEle.elementTextTrim("Longitude");
		String locationPrecision = msgEle.elementTextTrim("LOCATION_Precision");

		MsgEventLocaltion msg = new MsgEventLocaltion();
		msg.setCreateTime(new Timestamp(Long.valueOf(createTime) * 1000));
		msg.setFromUserName(fromUserName);
		msg.setToUserName(toUserName);
		msg.setMsgType(msgType);
		msg.setEvent(event);
		msg.setLatitude(Double.valueOf(latitude));
		msg.setLocationPrecision(Double.valueOf(locationPrecision));
		msg.setLongitude(Double.valueOf(longitude));
		
		HibernateTemplateExt.getInstance().save(msg);

		String resultStr = null;
		return resultStr;
	}
}
