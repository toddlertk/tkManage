package tk.weixin.core.msg.handler;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Element;

import tk.core.db.template.HibernateTemplateExt;
import tk.entities.msg.MsgImage;
import tk.entities.msg.MsgText;
import tk.weixin.core.msg.AbstractMsgHandler;
import tk.weixin.core.msg.MsgContrant;

public class MsgImageHandler extends AbstractMsgHandler {

	@Override
	public void send(HttpServletRequest request, HttpServletResponse response) {
		
	}

	@Override
	public String recv(Element msgEle) {
		String fromUserName = msgEle.elementText("FromUserName");  
		String toUserName = msgEle.elementText("ToUserName");  
		String msgId = msgEle.elementTextTrim("MsgId");
		String picUrl = msgEle.elementTextTrim("PicUrl");
		String mediaId = msgEle.elementTextTrim("MediaId");
		String createTime = msgEle.elementTextTrim("CreateTime");
		String msgType = msgEle.elementTextTrim("MsgType");
		
		MsgImage image = new MsgImage();
		image.setContentType(MsgContrant.MSG_CONTENT_TYPE_RECV);
		image.setCreateTime(new Timestamp(Long.valueOf(createTime) * 1000));
		image.setFromUserName(fromUserName);
		image.setToUserName(toUserName);
		image.setMsgId(msgId);
		image.setMsgType(msgType);
		image.setPicUrl(picUrl);
		image.setMediaId(mediaId);
		
		HibernateTemplateExt.getInstance().save(image);
		return null;
	}
}
