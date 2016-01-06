package tk.function.org.department.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import tk.core.db.SQL;
import tk.core.db.template.HibernateTemplateExt;
import tk.core.web.BasePage;
import tk.core.web.RequestEntry;
import tk.core.web.ResponseResult;
import tk.entities.active.WxAnswer;
import tk.entities.org.Department;
import tk.entities.org.DepartmentUser;
import tk.utils.StringUtils;

public class DepartmentWeb extends BasePage{
	
	/**
	 * 部门读取和配置
	 * @param requestEntry
	 * @return
	 */
	public ResponseResult kd92h(RequestEntry requestEntry){
		
		String act = requestEntry.getParameter("ai");
		String cd = requestEntry.getParameter("cd");
		
		if(!StringUtils.isNullOrEmpty(act)){
			if(act.equals("ads")){
				String departmentId = requestEntry.getParameter("departmentId");
				String openId = requestEntry.getParameter("openId");
				String userId = requestEntry.getParameter("userId");
				DepartmentUser du = new DepartmentUser();
				du.setAccountId(userId);
				du.setOpenId(openId);
				du.setDepartmentId(departmentId);
				HibernateTemplateExt.getInstance().save(du);
				HibernateTemplateExt.getInstance().flush();
				requestEntry.setAttribute("result", "SUCCESS");
				requestEntry.setAttribute("url", "depart/shGd/tk2y-kd92h.od?ai=rd&duId=" + du.getDuId());
				return null;
			} else if(act.equals("dt")){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(new Date());
				String Gs = requestEntry.getParameter("Gs");
				String userId = requestEntry.getParameter("userId");
				String answer = requestEntry.getParameter("answer");
				String departId = requestEntry.getParameter("departmentId");
				String answerId = Gs;
				WxAnswer wxanswer = new WxAnswer();
				SQL sql = SQL.begin().sql("from WxAnswer o where o.userId=? and o.answerId=?" ,userId , answerId).end();
				List <?> list = HibernateTemplateExt.getInstance().find(sql);
				if(list != null && list.size() > 0){
					wxanswer = (WxAnswer)list.get(0);
				}
				wxanswer.setAnswer(answer);
				wxanswer.setUserId(userId);
				wxanswer.setDepartmentId(departId);
				wxanswer.setAnswerId(answerId);
				wxanswer.setCreateTime(new Timestamp(new Date().getTime()));
				boolean flag = false;
				if(Gs.equals("WHio7GS7aA3fcD")){
					if(date.compareTo("2016-01-08") <= 0){
						flag = true;
					}
				}else if(Gs.equals("WHioS6J3I0dksD")){
					if(date.compareTo("2016-01-15") <= 0){
						flag = true;
					}
				}else if(Gs.equals("WHiK8o6Ld4PfcD")){
					if(date.compareTo("2016-01-22") <= 0){
						flag = true;
					}
				}
				if(flag){
					HibernateTemplateExt.getInstance().saveOrUpdate(wxanswer);
					requestEntry.setAttribute("wxanswer", wxanswer);
					requestEntry.setAttribute("Gs", Gs);
				}else{
					requestEntry.setAttribute("msg", "问题已经失效，请关注官方邮件~");
				}
				return null;
			} else if(act.equals("dr")){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(new Date());
				String Gs = requestEntry.getParameter("Gs");
				boolean flag = false;
				if(Gs.equals("WHio7GS7aA3fcD")){
					if(date.compareTo("2016-01-08") <= 0){
						flag = true;
					}
				}else if(Gs.equals("WHioS6J3I0dksD")){
					if(date.compareTo("2016-01-15") <= 0){
						flag = true;
					}
				}else if(Gs.equals("WHiK8o6Ld4PfcD")){
					if(date.compareTo("2016-01-22") <= 0){
						flag = true;
					}
				}else{
					requestEntry.setAttribute("msg", "问题已经失效，请关注官方邮件~");
				}
				requestEntry.setAttribute("flag", flag);
				requestEntry.setAttribute("Gs", Gs);

			} else{
			
				String duId = requestEntry.getParameter("duId");
				DepartmentUser du = HibernateTemplateExt.getInstance().get(DepartmentUser.class, duId);
				requestEntry.setAttribute("du", du);
				Department depart = HibernateTemplateExt.getInstance().get(Department.class	, du.getDepartmentId());
				requestEntry.setAttribute("depart", depart);
				return null;
			}
		}
		List <?> list = HibernateTemplateExt.getInstance().find(new SQL("from Department"));
		requestEntry.setAttribute("list", list);
		requestEntry.setAttribute("openId", cd);
		return null;
	}
}
