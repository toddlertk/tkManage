package tk.weixin.core.msg.handler;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Element;

import tk.core.db.template.HibernateTemplateExt;
import tk.entities.msg.MsgText;
import tk.entities.msg.MsgVideo;
import tk.entities.msg.MsgVoice;
import tk.weixin.core.msg.AbstractMsgHandler;
import tk.weixin.core.msg.MsgContrant;

public class MsgVideoHandler extends AbstractMsgHandler {

	@Override
	public void send(HttpServletRequest request, HttpServletResponse response) {
		
	}

	@Override
	public String recv(Element msgEle) {
		String fromUserName = msgEle.elementText("FromUserName");  
		String toUserName = msgEle.elementText("ToUserName");  
		String msgId = msgEle.elementTextTrim("MsgId");
		String createTime = msgEle.elementTextTrim("CreateTime");
		String mediaId = msgEle.elementTextTrim("MediaId");
		String thumbMediaId = msgEle.elementTextTrim("ThumbMediaId");
		String title = msgEle.elementTextTrim("Title");
		String description = msgEle.elementTextTrim("Description");
		String msgType = msgEle.elementTextTrim("MsgType");

		MsgVideo msg = new MsgVideo();
		msg.setCreateTime(new Timestamp(Long.valueOf(createTime) * 1000));
		msg.setFromUserName(fromUserName);
		msg.setToUserName(toUserName);
		msg.setMsgId(msgId);
		msg.setMsgType(msgType);
		msg.setMediaId(mediaId);
		msg.setDescription(description);
		msg.setThumbMediaId(thumbMediaId);
		msg.setTitle(title);
		
		HibernateTemplateExt.getInstance().save(msg);
		  
		String resultStr = null;
		return resultStr;
	}
}
