package tk.function.org.user.web;

import tk.core.db.SQL;
import tk.core.db.SQLBuilder;
import tk.core.db.template.HibernateTemplateExt;
import tk.core.web.BasePage;
import tk.core.web.RequestEntry;
import tk.core.web.ResponseResult;
import tk.entities.org.User;
import tk.function.org.user.UserStatusConstraints;
import tk.security.sha.SHAHelper;
import tk.utils.StringUtils;

public class UserWeb extends BasePage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String passKey = SHAHelper.getSHACode("guest" + "123456");
		System.out.println(passKey);
		
	}

	public ResponseResult create(RequestEntry requestEntry) throws Exception {
		String act = requestEntry.getParameter("act");
		if (StringUtils.isNullOrEmpty(act))
			return null;
		else if (act.equals("list")) {
			return this.list(requestEntry);
		}else if (act.equals("new")) {
			return this.doCreate(requestEntry);
		}else if (act.equals("delete")) {
			return this.delete(requestEntry);
		}else if (act.equals("preEdit")) {
			return this.preEdit(requestEntry);
		}else if (act.equals("edit")) {
			return this.edit(requestEntry);
		}
		return null;
	}


	private ResponseResult edit(RequestEntry requestEntry) {
		String userId = requestEntry.getParameter("userId");
		String userName = requestEntry.getParameter("userName");
		String userPws = requestEntry.getParameter("passkey");
		String userConfirmPws = requestEntry.getParameter("confirmPasskey");
		String userStatus = requestEntry.getParameter("userStatus");
		String chk = requestEntry.getParameter("pws");

		User user = HibernateTemplateExt.getInstance().get(User.class,	 userId);
		if(chk != null && chk.equals("pws")){
			if(userPws != null && userPws.equals(userConfirmPws)){
				if(userPws.trim().length() < 6){
					requestEntry.setAttribute("result", "用户密码不可少于6位");
					return null;
				}
				user.setUserPws(SHAHelper.getSHACode(userId + userPws));
			}
			else{
				requestEntry.setAttribute("result", "用户密码输入有误！");
				return null;
			}
		}
		user.setUserName(userName);
		user.setUserStatus(userStatus);
		HibernateTemplateExt.getInstance().update(user);
		HibernateTemplateExt.getInstance().flush();

		requestEntry.setAttribute("result", "SUCCESS");
		requestEntry.setAttribute("url", "admin/user/list/user-create.od?act=list");
		
		return null;
	}

	private ResponseResult preEdit(RequestEntry requestEntry) {
		String userId = requestEntry.getParameter("userId");
		User user = HibernateTemplateExt.getInstance().get(User.class,	 userId);
		requestEntry.setAttribute("user", user);
		return null;
	}

	private ResponseResult list(RequestEntry requestEntry) {
		
		String searchUserName = requestEntry.getParameter("searchUserName");

		String searchUserId = requestEntry.getParameter("searchUserId");

		SQLBuilder sb = SQL
				.begin()
				.sql("SELECT u FROM User u ");
		if (!StringUtils.isNullOrEmpty(searchUserName)) {
			// searchUserName = URLDecoder.decode(searchUserName,"UTF-8");
			sb.sql("WHERE u.userName like '%" + searchUserName + "%'");
		}else if (!StringUtils.isNullOrEmpty(searchUserId)){
			sb.sql("WHERE  u.userId=?", searchUserId);
		}
		if (!StringUtils.isNullOrEmpty(searchUserName) 
				&& !StringUtils.isNullOrEmpty(searchUserId)){
			sb.sql("and u.userId=?", searchUserId);
		}

		SQL sql = sb.end();

		this.buildPage(requestEntry, sql , "1");

		return null;
	}
	
	private ResponseResult delete(RequestEntry requestEntry) {
		
		String userId = requestEntry.getParameter("userId");
		
		User user = HibernateTemplateExt.getInstance().get(User.class,	 userId);
		
		user.setUserStatus(tk.function.org.user.UserStatusConstraints.USER_STATUS_INVALID);
		HibernateTemplateExt.getInstance().update(user);
		HibernateTemplateExt.getInstance().flush();
		
		return this.list(requestEntry);
	}

	private ResponseResult doCreate(RequestEntry requestEntry) {
		
		String userId = requestEntry.getParameter("userId");
		String userName = requestEntry.getParameter("userName");
		String userPws = requestEntry.getParameter("passkey");
		String userConfirmPws = requestEntry.getParameter("confirmPasskey");
		
		User user = HibernateTemplateExt.getInstance().get(User.class,	 userId);
		if(user != null){
			requestEntry.setAttribute("result", "用户账号已存在，请更换！");
		}else{
			if(userPws == null || !userPws.equals(userConfirmPws)){
				requestEntry.setAttribute("result", "用户密码输入有误！");
				return null;
			}
			if(userPws.trim().length() < 6){
				requestEntry.setAttribute("result", "用户密码不可少于6位");
				return null;
			}
			user = new User();
			user.setUserId(userId);
			user.setUserName(userName);
			user.setUserPws(SHAHelper.getSHACode(userId + userPws));
			user.setUserStatus(UserStatusConstraints.USER_STATUS_ACTIVED);
			HibernateTemplateExt.getInstance().save(user);
			HibernateTemplateExt.getInstance().flush();
			requestEntry.setAttribute("result", "SUCCESS");
		}
		requestEntry.setAttribute("url", "admin/user/list/user-create.od?act=list");
		return null;
	}
	


}
