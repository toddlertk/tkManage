package tk.function.active.web;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import tk.core.db.SQL;
import tk.core.db.template.HibernateTemplateExt;
import tk.core.db.template.JdbcTemplateExt;
import tk.core.web.BasePage;
import tk.core.web.RequestEntry;
import tk.core.web.ResponseResult;
import tk.entities.active.WxActive;
import tk.utils.StringUtils;
import tk.weixin.core.msg.service.TextDuleService;

public class ActiveWeb extends BasePage{

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public ResponseResult create(RequestEntry requestEntry) throws ParseException{
		
		String act = requestEntry.getParameter("act");
		if(!StringUtils.isNullOrEmpty(act)){
			if(act.equals("list")){
				List <?> list = HibernateTemplateExt.getInstance().find(new SQL("from WxActive o order by o.activeIndex "));
				requestEntry.setAttribute("list", list);
				return null;
			}else if(act.equals("create")){
				String activeIndex = requestEntry.getParameter("activeIndex");
				String activeName = requestEntry.getParameter("activeName");
				String begTime = requestEntry.getParameter("begTime");
				String endTime = requestEntry.getParameter("endTime");
				WxActive active = HibernateTemplateExt.getInstance().get(WxActive.class, Integer.valueOf(activeIndex));
				if(active == null){
					active = new WxActive();
				}
				active.setActiveIndex(Integer.valueOf(activeIndex));
				active.setActiveName(activeName);
				active.setBegTime(new Timestamp(sdf.parse(begTime).getTime()));
				active.setEndTime(new Timestamp(sdf.parse(endTime).getTime()));
				HibernateTemplateExt.getInstance().saveOrUpdate(active);
				HibernateTemplateExt.getInstance().flush();
				requestEntry.setAttribute("result", "success");
				requestEntry.setAttribute("url", "admin/active/list/active-create.od?act=list");
				TextDuleService.getInstance().updateActive(active);
				return null;
			}else if(act.equals("pre")){
				String activeIndex = requestEntry.getParameter("activeIndex");
				if(!StringUtils.isNullOrEmpty(activeIndex)){
					WxActive active = HibernateTemplateExt.getInstance().get(WxActive.class, Integer.valueOf(activeIndex));
					requestEntry.setAttribute("active", active);
				}
				return null;
			}else if(act.equals("drop")){
				String activeIndex = requestEntry.getParameter("activeIndex");
				WxActive active = HibernateTemplateExt.getInstance().get(WxActive.class, Integer.valueOf(activeIndex));
				active.setEndTime(new Timestamp(new Date().getTime()));
				HibernateTemplateExt.getInstance().update(active);
				HibernateTemplateExt.getInstance().flush();
				TextDuleService.getInstance().updateActive(active);
			}else if(act.equals("beg")){
				String activeIndex = requestEntry.getParameter("activeIndex");
				WxActive active = HibernateTemplateExt.getInstance().get(WxActive.class, Integer.valueOf(activeIndex));
				active.setBegTime(new Timestamp(new Date().getTime()));
				HibernateTemplateExt.getInstance().update(active);
				HibernateTemplateExt.getInstance().flush();
				TextDuleService.getInstance().updateActive(active);
			}
		}
		List <?> list = HibernateTemplateExt.getInstance().find(new SQL("from WxActive"));
		requestEntry.setAttribute("list", list);
		
		return null;
	}
	
	public ResponseResult read(RequestEntry requestEntry) {
		String act = requestEntry.getParameter("act");
		if(!StringUtils.isNullOrEmpty(act)){
			if(act.equals("detail")){

				String activeIndex = requestEntry.getParameter("activeIndex");
				SQL sql = SQL.begin().sql("SELECT ACT_.ACTIVE_INDEX,ACT_.ACTIVE_NAME,COUNT(0) AS NUM ,SUM(ACTS.SCORE) AS TOTAL,"
						+ " SUM(ACTS.SCORE)/COUNT(0) AS AVG , DEP_.DEPARTMENT_NAME "
						+ " FROM wx_active ACT_ , wx_active_score ACTS ,wx_department_user DEPU , sm_department DEP_ "
						+ " WHERE ACT_.ACTIVE_INDEX=ACTS.ACTIVE_INDEX AND ACTS.OPEN_ID=DEPU.OPEN_ID "
						+ "AND DEP_.DEPARTMENT_ID=ACTS.DEPARTMENT_ID ")
						.sql(" and ACT_.ACTIVE_INDEX = ? " , activeIndex)
						.sql(" GROUP BY ACT_.ACTIVE_INDEX,ACT_.ACTIVE_NAME,DEP_.DEPARTMENT_NAME  ")
						.sql("ORDER BY ACT_.ACTIVE_INDEX ASC ").end();

				List<?> list = JdbcTemplateExt.getInstance().find(sql);
				requestEntry.setAttribute("list", list);
				return null;
			}else if(act.equals("result")){

				SQL sql = SQL.begin().sql("SELECT ACTIVE_INDEX,ACTIVE_NAME,SUM(AVG) AS AVG,SUM(NUM) AS NUM ,SUM(TOTAL) AS TOTAL  "
						+ " FROM ( "
						+ " SELECT ACT_.ACTIVE_INDEX,ACT_.ACTIVE_NAME,COUNT(0) AS NUM ,SUM(ACTS.SCORE) AS TOTAL,DEP_.DEPARTMENT_NAME,SUM(ACTS.SCORE)/COUNT(0) AS AVG "
						+ " FROM wx_active ACT_ , wx_active_score ACTS ,wx_department_user DEPU , sm_department DEP_ "
						+ " WHERE ACT_.ACTIVE_INDEX=ACTS.ACTIVE_INDEX AND ACTS.OPEN_ID=DEPU.OPEN_ID AND DEP_.DEPARTMENT_ID=ACTS.DEPARTMENT_ID "
						+ " GROUP BY ACT_.ACTIVE_INDEX,ACT_.ACTIVE_NAME,DEP_.DEPARTMENT_NAME ) AS T "
						+ " GROUP BY ACTIVE_INDEX,ACTIVE_NAME "
						+ " ORDER BY ACTIVE_INDEX ASC").end();

				List<?> list = JdbcTemplateExt.getInstance().find(sql);
				requestEntry.setAttribute("list", list);
				return null;
			}
			
			return null;
		}
		
		SQL sql = SQL.begin().sql("select w,d,a from WxActiveScore w ,DepartmentUser d ,WxActive a")
				.sql(" where d.openId=w.openId and a.activeIndex=w.activeIndex order by w.createTime desc").end();

		this.buildPage(requestEntry, sql , "1");
		
		return null;
	}
}
