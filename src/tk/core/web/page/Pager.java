package tk.core.web.page;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import tk.core.db.SQL;
import tk.core.db.template.HibernateTemplateExt;
import tk.core.web.RequestEntry;
import tk.utils.StringUtils;

public abstract class Pager {
	public static final int DEFAULT_PAGESIZE = 15;
	protected int currentPage;
	protected int pageSize;
	protected int count;
	protected List dataList = null;
	protected String url = null;
	//protected JdbcTemplateExt jdbcTemplate = null;
	protected HibernateTemplateExt hibernateTemplate = null;
	
	protected SQL sqlObj;
	
	public Pager(RequestEntry requestEntry){
		String page = requestEntry.getParameter("page");
		String size = requestEntry.getParameter("size");

		if(StringUtils.isNullOrEmpty(page))
			this.currentPage = 1;
		else{
			try{
				this.currentPage = Integer.parseInt(page);
				if(this.currentPage <1)
					this.currentPage = 1;
			}catch(Exception e){
				this.currentPage = 1;
			}
		}
		
		if(StringUtils.isNullOrEmpty(size))
			this.pageSize = DEFAULT_PAGESIZE;
		else{
			try{
				this.pageSize = Integer.parseInt(size);
				if(this.pageSize < 1)
					this.pageSize = DEFAULT_PAGESIZE;
			}catch(Exception e){
				this.pageSize = DEFAULT_PAGESIZE;
			}
		}
		
		HttpServletRequest request = requestEntry.getRequest();
		String baseUrl = request.getRequestURI();
		String query = request.getQueryString();
		if(!StringUtils.isNullOrEmpty(query)){
			String[] queryArr = query.split("&");
			query = "";
			for(int i=0;i<queryArr.length;i++){
				if(queryArr[i].indexOf("=")>-1){
					String[] items = queryArr[i].split("=");
					if("PAGE".equals(items[0].toUpperCase()) || "SIZE".equals(items[0].toUpperCase()))
						continue;
				}
				query += queryArr[i] + "&";
			}
		}
		this.url = baseUrl + "?" + query;
	}

	public void prepareQuery(Map queryMap,Map searchableFields){
		if(this.sqlObj == null || StringUtils.isNullOrEmpty(this.sqlObj.getText()))
			return;
		String hqlText = this.sqlObj.getText();
		hqlText = hqlText.replaceAll("\r\n", " ");
		hqlText = hqlText.replaceAll(" {2,}", " ");
		this.sqlObj.setText(hqlText);
		
		if(queryMap ==null || queryMap.size() == 0)
			return;
		
		String sel = hqlText;
		String orderBy = "";
		String upperSel = hqlText.toUpperCase();
		int orderByIndex = upperSel.lastIndexOf("ORDER BY");
		if(orderByIndex > 0){
			orderBy = sel.substring(orderByIndex);
			sel = sel.substring(0,orderByIndex);
		}
		
		upperSel = sel.toUpperCase();
		int whereIndex = upperSel.lastIndexOf("WHERE");
		boolean hasWhere = false;
		if(whereIndex > 0){
			String where = sel.substring(whereIndex);
			if(where.split("\\)").length != where.split("\\(").length)
				hasWhere = true;
		}
		List params = this.sqlObj.getParams();
		if(params == null){
			params = new ArrayList();
			this.sqlObj.setParams(params);
		}
		if(!hasWhere)
			sel += " where ";
		String query = "";
		//if(this instanceof HqlPager){
			for(Iterator it=queryMap.keySet().iterator();it.hasNext();){
				String key = (String)it.next();
				if(StringUtils.isNullOrEmpty((String)queryMap.get(key)))
					continue;
				// FIXME
				query += " and "+key+" like ? ";
				params.add("%"+(String)queryMap.get(key)+"%");
			}
		//}
		if(!hasWhere)
			query = query.substring(4);
		sel += query;
		sel += orderBy;
		this.sqlObj.setText(sel);
	}
	public static void main(String[] args){
		String hqlText = "asdf    d fe   asdfe";
		System.out.println(hqlText);
		hqlText = hqlText.replaceAll(" {2,}", " ");
		System.out.println(hqlText);
	}
	public int getCurrentPage(){
		return this.currentPage;
	}
	public int getPageSize(){
		return this.pageSize;
	}
	public void setPageSize(int pageSize){
		this.pageSize = pageSize;
	}
	
	public int getCount(){
		return this.count;
	}
	public abstract List getPagerData();

	public SQL getSqlObj() {
		return sqlObj;
	}

	public void setSqlObj(SQL sqlObj) {
		this.sqlObj = sqlObj;
	}
	public int getPages(){
		int pages = this.count / this.pageSize;
		int remainder = this.count % this.pageSize;
		if(remainder > 0)
			pages = pages + 1;
		return pages;
	}
	public String getUrl(){
		return null;
	}

/*	public JdbcTemplateExt getJdbcTemplate() {
		if(jdbcTemplate == null)
			return JdbcTemplateExt.getInstance();
		else
			return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplateExt jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}*/

	public HibernateTemplateExt getHibernateTemplate() {
		if(hibernateTemplate == null)
			return HibernateTemplateExt.getInstance();
		else
			return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplateExt hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}
