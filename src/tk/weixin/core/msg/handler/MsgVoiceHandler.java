package tk.weixin.core.msg.handler;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Element;

import tk.core.db.template.HibernateTemplateExt;
import tk.entities.msg.MsgText;
import tk.entities.msg.MsgVoice;
import tk.weixin.core.msg.AbstractMsgHandler;
import tk.weixin.core.msg.MsgContrant;

public class MsgVoiceHandler extends AbstractMsgHandler {

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
		String format = msgEle.elementTextTrim("Format");
		String msgType = msgEle.elementTextTrim("MsgType");
		
		MsgVoice voice = new MsgVoice();
		voice.setContentType(MsgContrant.MSG_CONTENT_TYPE_RECV);
		voice.setCreateTime(new Timestamp(Long.valueOf(createTime) * 1000));
		voice.setFromUserName(fromUserName);
		voice.setToUserName(toUserName);
		voice.setMsgId(msgId);
		voice.setMsgType(msgType);
		voice.setMediaId(mediaId);
		voice.setFormat(format);
		
		HibernateTemplateExt.getInstance().save(voice);
		  
		String resultStr = null;
		return resultStr;
	}
}
