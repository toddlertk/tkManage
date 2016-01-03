package tk.weixin.core.msg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Element;

public abstract class AbstractMsgHandler {
	public abstract void send(HttpServletRequest request , HttpServletResponse response);
	public abstract String recv(Element msgEle);
}
