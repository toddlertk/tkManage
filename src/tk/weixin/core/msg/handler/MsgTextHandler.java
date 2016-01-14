package tk.weixin.core.msg.handler;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Element;

import tk.core.db.template.HibernateTemplateExt;
import tk.entities.msg.MsgText;
import tk.weixin.core.msg.AbstractMsgHandler;
import tk.weixin.core.msg.MsgContrant;
import tk.weixin.core.msg.service.TextDuleService;

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
		
		String resultStr = TextDuleService.getInstance().duleContent(content, fromUserName, toUserName, text);
		
		return resultStr;
	}
}
