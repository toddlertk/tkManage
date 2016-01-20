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
import tk.weixin.core.msg.service.TextDuleService;

public class DepartmentWeb extends BasePage{

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 部门读取和配置
	 * @param requestEntry
	 * @return
	 */
	public ResponseResult kd92h(RequestEntry requestEntry){
		
		String act = requestEntry.getParameter("ai");
		String openId = requestEntry.getParameter("openId");
		
		if(!StringUtils.isNullOrEmpty(act)){
			if(act.equals("ads")){
				return updateDepartment(requestEntry);
			} else if(act.equals("dt")){
				return answerQuestion(requestEntry);
			} else if(act.equals("dr")){
				readQuestion(requestEntry);
			} else if(act.equals("rd")){
				String duId = requestEntry.getParameter("duId");
				DepartmentUser du = HibernateTemplateExt.getInstance().get(DepartmentUser.class, duId);
				requestEntry.setAttribute("du", du);
			}
		}else{
			List <?> list1 = HibernateTemplateExt.getInstance().find(new SQL("from DepartmentUser o where o.openId=?" , openId));
			if(list1.size() > 0){
				requestEntry.setAttribute("du", list1.get(0));
			}
		}
		List <?> list = HibernateTemplateExt.getInstance().find(new SQL("from Department"));
		requestEntry.setAttribute("list", list);
		requestEntry.setAttribute("openId", openId);
		return null;
	}
	
	/**
	 * 更新部门
	 * @param requestEntry
	 * @return
	 */
	private ResponseResult updateDepartment(RequestEntry requestEntry){
		String openId = requestEntry.getParameter("openId");
		String departmentId = requestEntry.getParameter("departmentId");
		String userId = requestEntry.getParameter("userId");
		String userName = requestEntry.getParameter("userName");
		DepartmentUser du = null;
		List <?> list1 = HibernateTemplateExt.getInstance().find(new SQL("from DepartmentUser o where o.openId=?" , openId));
		if(list1.size() > 0){
			du = (DepartmentUser) list1.get(0);
		}else{
			SQL sql = SQL.begin().sql("from SmDepartmentUser o where o.userId=? " , userId)
					.sql(" and departmentId=? " , departmentId).end();
			list1 = HibernateTemplateExt.getInstance().find(sql);
			if(list1 == null || list1.size() < 1){
				requestEntry.setAttribute("result", "FAIL-输入岗位与部门不匹配，请确认！");
				return null;
			}
		}
		du.setAccountId(userId);
		du.setOpenId(openId);
		du.setUserName(userName);
		du.setDepartmentId(departmentId);
		HibernateTemplateExt.getInstance().save(du);
		HibernateTemplateExt.getInstance().flush();
		TextDuleService.getInstance().updateDepart(du);
		requestEntry.setAttribute("result", "SUCCESS");
		requestEntry.setAttribute("url", "ph/depart/tk2y-kd92h.od?ai=rd&duId=" + du.getDuId());
		return null;
	}
	

	
	/**
	 * 回答问题
	 * @param requestEntry
	 * @return
	 */
	private ResponseResult answerQuestion(RequestEntry requestEntry){
		String date = sdf.format(new Date());
		String Gs = requestEntry.getParameter("Gs");
		
		boolean flag = false;
		if(Gs.equals("WHio7GS7aA3fcD")){
			if(date.compareTo("2016-01-08 20") <= 0){
				flag = true;
			}
		}else if(Gs.equals("WHioS6J3I0dksD")){
			if(date.compareTo("2016-01-15 20") <= 0){
				flag = true;
			}
		}else if(Gs.equals("WHiK8o6Ld4PfcD")){
			if(date.compareTo("2016-01-22 20") <= 0){
				flag = true;
			}
		}
		if(flag){
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
			HibernateTemplateExt.getInstance().saveOrUpdate(wxanswer);
			requestEntry.setAttribute("wxanswer", wxanswer);
			requestEntry.setAttribute("Gs", Gs);
		}else{
			requestEntry.setAttribute("msg", "问题已经失效，请关注官方邮件~");
		}
		return null;
	}
	
	/**
	 * 读取问题
	 * @param requestEntry
	 * @return
	 */
	private ResponseResult readQuestion(RequestEntry requestEntry){
		String date = sdf.format(new Date());
		String Gs = requestEntry.getParameter("Gs");
		boolean flag = false;
		if(Gs.equals("WHio7GS7aA3fcD")){
			if(date.compareTo("2016-01-08 20") <= 0){
				flag = true;
			}
		}else if(Gs.equals("WHioS6J3I0dksD")){
			if(date.compareTo("2016-01-15 22") <= 0){
				flag = true;
			}
		}else if(Gs.equals("WHiK8o6Ld4PfcD")){
			if(date.compareTo("2016-01-22 22") <= 0){
				flag = true;
			}
		}else{
			requestEntry.setAttribute("msg", "问题已经失效，请关注官方邮件~");
		}
		requestEntry.setAttribute("flag", flag);
		requestEntry.setAttribute("Gs", Gs);
		return null;
	}
}
