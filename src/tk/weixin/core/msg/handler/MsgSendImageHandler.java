package tk.weixin.core.msg.handler;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Element;

import tk.core.db.template.HibernateTemplateExt;
import tk.entities.msg.MsgEventClick;
import tk.entities.msg.MsgText;
import tk.weixin.core.msg.AbstractMsgHandler;
import tk.weixin.core.msg.MsgContrant;

public class MsgSendImageHandler extends AbstractMsgHandler {

	@Override
	public void send(HttpServletRequest request, HttpServletResponse response) {
		
	}

	@Override
	public String recv(Element msgEle) {
		return null;
	}
}
