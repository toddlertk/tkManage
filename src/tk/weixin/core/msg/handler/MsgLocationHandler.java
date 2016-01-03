package tk.weixin.core.msg.handler;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Element;

import tk.core.db.template.HibernateTemplateExt;
import tk.entities.msg.MsgLocation;
import tk.entities.msg.MsgText;
import tk.entities.msg.MsgVideo;
import tk.weixin.core.msg.AbstractMsgHandler;
import tk.weixin.core.msg.MsgContrant;

public class MsgLocationHandler extends AbstractMsgHandler {

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
		
		String label = msgEle.elementTextTrim("Label");
		String scale = msgEle.elementTextTrim("Scale");
		String locationY = msgEle.elementTextTrim("Location_Y");
		String locationX = msgEle.elementTextTrim("Location_X");

		MsgLocation msg = new MsgLocation();
		msg.setCreateTime(new Timestamp(Long.valueOf(createTime) * 1000));
		msg.setFromUserName(fromUserName);
		msg.setToUserName(toUserName);
		msg.setMsgId(msgId);
		msg.setMsgType(msgType);
		msg.setLabel(label);
		msg.setLocationX(Double.valueOf(locationX));
		msg.setLocationY(Double.valueOf(locationY));
		msg.setScale(Integer.valueOf(scale));
		
		HibernateTemplateExt.getInstance().save(msg);
		 
		String resultStr = null;
		return resultStr;
	}
}
