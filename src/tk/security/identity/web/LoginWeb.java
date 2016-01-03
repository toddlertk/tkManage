package tk.security.identity.web;


import java.io.UnsupportedEncodingException;

import tk.core.db.template.HibernateTemplateExt;
import tk.core.web.BasePage;
import tk.core.web.RequestEntry;
import tk.core.web.ResponseResult;
import tk.entities.org.User;
import tk.security.sha.SHAHelper;
import tk.utils.StringUtils;

public class LoginWeb extends BasePage {

	public ResponseResult validate(RequestEntry requestEntry) {
		String userId = requestEntry.getParameter("userId");
		String passKey = requestEntry.getParameter("passKey");

		User user = HibernateTemplateExt.getInstance().get(User.class, userId);

		String result = null;
		if (user == null)
			result = "无此用户";
		else {
			String inputPassKey = SHAHelper.getSHACode(userId + passKey);
			if (!user.getUserPws().equals(inputPassKey))
				result = "密码错误";
			else { // 登录成功
				if(user.getUserStatus().equals("1")){
					result = "用户失效";
				}else if(user.getUserStatus().equals("0")){
					result = "SUCCESS";
					requestEntry.setSessionAttribute("userId", userId);
				}
					
			}
		}
		requestEntry.setAttribute("result", result);
		return null;
	}
	
	public ResponseResult logout(RequestEntry requestEntry) {
		String userId = requestEntry.getUserId();
		requestEntry.getRequest().getSession().invalidate();
		if (!StringUtils.isNullOrEmpty(userId)) {
			requestEntry.removeSessionAttribute("userId");
		}
		requestEntry.setAttribute("result", "NoMsg");
		requestEntry.setAttribute("url", "login.jsp");
		return null;
	}

	public ResponseResult changePsw(RequestEntry requestEntry)
			throws UnsupportedEncodingException {
		String originalPsw = requestEntry.getParameter("originalPsw");
		String newPsw = requestEntry.getParameter("newPsw");
		String confirmNewPsw = requestEntry.getParameter("confirmNewPsw");

		if (StringUtils.isNullOrEmpty(originalPsw)
				&& StringUtils.isNullOrEmpty(newPsw)
				&& StringUtils.isNullOrEmpty(confirmNewPsw))
			return null;
		
		String userId = requestEntry.getUserId();
		final User user = HibernateTemplateExt.getInstance().get(User.class, userId);
		if (user == null)
			return null;
		
		if (!newPsw.equals(confirmNewPsw)) {
			requestEntry.setAttribute("result", "两次密码不一致");
			return null;
		} else {
			if (userId.equals("guest")) {
				requestEntry.setAttribute("result", "登录超时，请重新登录");
				return null;
			} else {
				String oldPsw = SHAHelper.getSHACode(user.getUserId()
						+ originalPsw);
				if (!oldPsw.equals(user.getUserPws())) {
					requestEntry.setAttribute("result", "原密码错误");
					return null;
				} else {
					String newPassKey = SHAHelper.getSHACode(user.getUserId()
							+ newPsw);
					user.setUserPws(newPassKey);
				}
				HibernateTemplateExt.getInstance().update(user);
			}
		}
			 
		requestEntry.setAttribute("result", "SUCCESS");
		return null;
	}
}
