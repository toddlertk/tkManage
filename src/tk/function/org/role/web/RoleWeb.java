package tk.function.org.role.web;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import tk.core.db.SQL;
import tk.core.db.SQLBuilder;
import tk.core.db.template.HibernateRunable;
import tk.core.db.template.HibernateTemplateExt;
import tk.core.web.BasePage;
import tk.core.web.RequestEntry;
import tk.core.web.ResponseResult;
import tk.entities.org.Role;
import tk.entities.org.RoleUser;
import tk.entities.org.RoleUserId;
import tk.utils.StringUtils;

public class RoleWeb extends BasePage{
	public ResponseResult list(RequestEntry requestEntry) throws Exception {
		String act = requestEntry.getParameter("act");
		if (StringUtils.isNullOrEmpty(act))
			return null;
		else if (act.equals("list")) {
			return this.rolelist(requestEntry);
		} else if (act.equals("read")) {
			return this.read(requestEntry);
		}
		return null;
	}
	
	public ResponseResult create(RequestEntry requestEntry) throws Exception {
		String act = requestEntry.getParameter("act");
		if (StringUtils.isNullOrEmpty(act))
			return null;
		else if (act.equals("add")) {
			return this.add(requestEntry);
		} 
		return null;
	}

	public ResponseResult rulist(RequestEntry requestEntry) throws Exception {
		String act = requestEntry.getParameter("act");
		if (!StringUtils.isNullOrEmpty(act)){
			if (act.equals("add")) {
				return this.add(requestEntry);
			}else if (act.equals("read")) {
				return this.readList(requestEntry);
			}
			return null;
		}

		String roleId = requestEntry.getParameter("roleId");
		if(!StringUtils.isNullOrEmpty(roleId)){
			Role role = HibernateTemplateExt.getInstance().get(Role.class, roleId);
			requestEntry.setAttribute("role", role);
		}
		String roleName = requestEntry.getParameter("searchRoleName");
		String userName = requestEntry.getParameter("searchUserName");
		
		SQLBuilder sqlbuilder = SQL.begin().sql("select r,u from Role r, User u , RoleUser ru "
				+ "where r.roleId=ru.id.roleId and u.userId=ru.id.userId");
		if(!StringUtils.isNullOrEmpty(roleName)){
			sqlbuilder.sql(" and r.roleName like '%" + roleName + "%'");
		}
		if(!StringUtils.isNullOrEmpty(userName)){
			sqlbuilder.sql(" and u.userName like '%" + userName + "%'");
		}
		
		this.buildPage(requestEntry, sqlbuilder.end() , "1");
		
		requestEntry.setAttribute("searchRoleName", roleName);
		requestEntry.setAttribute("searchUserName", userName);
		
		return null;
	}
	
	public ResponseResult edit(RequestEntry requestEntry) throws Exception {
		String act = requestEntry.getParameter("act");
		if (StringUtils.isNullOrEmpty(act))
			return null;
		else if (act.equals("del")) {
			return this.del(requestEntry);
		} else if (act.equals("preEdit")) {
			return this.preEdit(requestEntry);
		}  else if (act.equals("edit")) {
			return this.modify(requestEntry);
		} 
		return null;
	}
	
	public ResponseResult allot(RequestEntry requestEntry){
		String act = requestEntry.getParameter("act");
		if (StringUtils.isNullOrEmpty(act))
			return null;
		else if (act.equals("preAdd")) {
			return this.preAdd(requestEntry);
		} else if (act.equals("add")) {
			return this.addAllot(requestEntry);
		}  else if (act.equals("del")) {
			return this.delAllot(requestEntry);
		}  
		return null;
		
	}
	
	private ResponseResult delAllot(RequestEntry requestEntry) {

		String roleId = requestEntry.getParameter("roleId");
		String userId = requestEntry.getParameter("userId");
		RoleUserId id = new RoleUserId(roleId, userId);
		RoleUser ru = HibernateTemplateExt.getInstance().get(RoleUser.class, id);
		HibernateTemplateExt.getInstance().delete(ru);
		HibernateTemplateExt.getInstance().flush();
		this.readList(requestEntry);
		return null;
	}

	private ResponseResult addAllot(RequestEntry requestEntry) {
		final String roleId = requestEntry.getParameter("roleId");
		final String []userId = requestEntry.getParameterValues("userId");
		if(userId == null || userId.length < 1){
			requestEntry.setAttribute("result", "未选中用户！");
			return null;
		}
		
		HibernateTemplateExt.getInstance().runInTransaction(new HibernateRunable() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				for(String s : userId){
					RoleUserId id = new RoleUserId(roleId, s);
					RoleUser pu = new RoleUser();
					pu.setId(id);
					HibernateTemplateExt.getInstance().save(pu);
				}
				return null;
			}
		});
		/*requestEntry.setAttribute("url", "admin/role/rulist/role-rulist.od?act=read&roleId=" + roleId);
		requestEntry.setAttribute("result", "SUCCESS");*/
		return this.readList(requestEntry);
	}

	private ResponseResult preAdd(RequestEntry requestEntry) {

		String roleId = requestEntry.getParameter("roleId");
		String searchUserName = requestEntry.getParameter("searchUserName");

		String searchUserId = requestEntry.getParameter("searchUserId");

		SQLBuilder sb = SQL
				.begin()
				.sql("SELECT u FROM User u where");
		sb.sql("  u.userId not in (select o.id.userId from RoleUser o where o.id.roleId=?)" , roleId);
		
		if (!StringUtils.isNullOrEmpty(searchUserName)) {
			// searchUserName = URLDecoder.decode(searchUserName,"UTF-8");
			sb.sql(" and u.userName like '%" + searchUserName + "%'");
		}
		
		if (!StringUtils.isNullOrEmpty(searchUserId)){
			sb.sql(" and u.userId=?", searchUserId);
		}
		
		
		SQL sql = sb.end();

		this.buildPage(requestEntry, sql , "1");
		requestEntry.setAttribute("roleId", roleId);

		return null;
	}

	private ResponseResult readList(RequestEntry requestEntry) {
		String roleId = requestEntry.getParameter("roleId");
		
		SQL sql = SQL.begin().sql("select r,u from Role r, User u , RoleUser ru "
				+ "where r.roleId=ru.id.roleId and u.userId=ru.id.userId and r.roleId=?" , roleId).end();

		this.buildPage(requestEntry, sql , "1");
		
		Role role = HibernateTemplateExt.getInstance().get(Role.class, roleId);
		requestEntry.setAttribute("role", role);
		
		return null;
	}
	
	private ResponseResult preEdit(RequestEntry requestEntry) throws Exception {

		String roleId = requestEntry.getParameter("roleId");
		
		Role role = HibernateTemplateExt.getInstance().get(Role.class, roleId);
		requestEntry.setAttribute("role", role);
		
		return null;
	}
	
	private ResponseResult del(RequestEntry requestEntry) {
		String roleId = requestEntry.getParameter("roleId");
		
		Role role = HibernateTemplateExt.getInstance().get(Role.class, roleId);
		try{
			HibernateTemplateExt.getInstance().delete(role);
			HibernateTemplateExt.getInstance().flush();
			requestEntry.setAttribute("result", "success");
			
		}catch(Exception e){
			requestEntry.setAttribute("result", "删除异常" + e.getMessage());
			e.printStackTrace();
		}
		/*HibernateTemplateExt.getInstance().runInTransaction(new HibernateRunable() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException {

				SQL sql = SQL.begin().sql("select o from RoleUser o where o.id.roleId=?" , roleId).end();
				List<?> list = HibernateTemplateExt.getInstance().find(sql);
				
				for(Object o : list){
					RoleUser pu = (RoleUser)o;
					HibernateTemplateExt.getInstance().delete(pu);
				}
				
				Role role = HibernateTemplateExt.getInstance().get(Role.class, roleId);
				HibernateTemplateExt.getInstance().delete(role);
				return null;
			}
		});*/
		return this.rolelist(requestEntry);
	}
	
	private ResponseResult modify(RequestEntry requestEntry) {
		String roleId = requestEntry.getParameter("roleId");
		String roleName = requestEntry.getParameter("roleName");
		
		Role role = HibernateTemplateExt.getInstance().get(Role.class, roleId);
		role.setRoleName(roleName);
		HibernateTemplateExt.getInstance().update(role);
		HibernateTemplateExt.getInstance().flush();
		
		return this.rolelist(requestEntry);
	}
	private ResponseResult rolelist(RequestEntry requestEntry) {
		SQL sql = new SQL("select o from Role o");
		List<?> rolelist = HibernateTemplateExt.getInstance().find(sql);
		requestEntry.setAttribute("rolelist", rolelist);
		return null;
	}
	
	private ResponseResult read(RequestEntry requestEntry) {
		String id = requestEntry.getParameter("roleId");
		
		Role role = HibernateTemplateExt.getInstance().get(Role.class, id);
		requestEntry.setAttribute("role", role);
		return null;
	}
	
	private ResponseResult add(RequestEntry requestEntry) throws Exception{
		String roleId = requestEntry.getParameter("roleId");
		String roleName = requestEntry.getParameter("roleName");

		Role role = new Role();
		role.setRoleId(roleId);
		role.setRoleName(roleName);
		
		HibernateTemplateExt.getInstance().save(role);
		HibernateTemplateExt.getInstance().flush();

		return this.rolelist(requestEntry);
	}
	
}