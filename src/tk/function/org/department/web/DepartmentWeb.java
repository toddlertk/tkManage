package tk.function.org.department.web;

import java.util.List;

import tk.core.db.SQL;
import tk.core.db.template.HibernateTemplateExt;
import tk.core.web.BasePage;
import tk.core.web.RequestEntry;
import tk.core.web.ResponseResult;
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
			}else{
				String duId = requestEntry.getParameter("duId");
				DepartmentUser du = HibernateTemplateExt.getInstance().get(DepartmentUser.class, duId);
				requestEntry.setAttribute("du", du);
				Department depart = HibernateTemplateExt.getInstance().get(Department.class	, du.getDepartmentId());
				requestEntry.setAttribute("depart", depart);
			}
			return null;
		}
		List <?> list = HibernateTemplateExt.getInstance().find(new SQL("from Department"));
		requestEntry.setAttribute("list", list);
		requestEntry.setAttribute("openId", cd);
		return null;
	}
}
