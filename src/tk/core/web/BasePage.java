package tk.core.web;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartRequest;

import tk.core.db.SQL;
import tk.core.web.page.HqlPager;
import tk.core.web.page.PageConstraints;
import tk.core.web.page.Pager;

public abstract class BasePage {

	private String ignoreAuth = "N";
	public Object onPageLoadError(Throwable t){
		/*logger.error(t);
		t.printStackTrace();
		if(!t.getClass().equals(FVException.class)){
			throw ExceptionCreator.create(t);
		}else
			throw (FVException)t;*/
		//this.setRequestAttribute("exception", t);
		//return new PageLoadResult("/basepage/error.jsp");
		throw new RuntimeException(t);
	}
	
	public void beforeExcuteMethod(RequestEntry requestEntry) throws Exception{};
	public void afterExcuteMethod(RequestEntry requestEntry){};
	public void beforeGenerateEntity(RequestEntry requestEntry){};
	
	public void beforeSaveEntity(RequestEntry requestEntry){};
	
	public ResponseResult finish(RequestEntry requestEntry){
		ResponseResult result = new ResponseResult();
		/*String redirectUrl = requestEntry.getParameter("FV_REDIRECT");
		if(!StringUtil.isNullOrEmpty(redirectUrl))
			result.setRedirectUrl(redirectUrl); */
		return result;
	}
	/*public void buildPage(RequestEntry requestEntry,SQL sql){
		String strSize = requestEntry.getParameter("size");
		if(strSize == null){
			this.buildPage(requestEntry, sql, Pager.DEFAULT_PAGESIZE);
			return;
		}
		int size = Integer.valueOf(strSize);
		this.buildPage(requestEntry, sql, size);
	}*/
	/**
	 * 鐢ㄤ簬閽堝SqlServer鍒嗛〉澶勭悊锛岄渶瑕佸璁炬爣璇嗕綅
	 * @param requestEntry
	 * @param sql
	 * @param sflag "1" : mysql "2":sqlserver
	 */
	public void buildPage(RequestEntry requestEntry,SQL sql , String sflag){
		String strSize = requestEntry.getParameter("size");
		if(strSize == null){
			this.buildPage(requestEntry, sql, Pager.DEFAULT_PAGESIZE , sflag);
			return;
		}
		int size = Integer.valueOf(strSize);
		this.buildPage(requestEntry, sql, size , sflag);
	}
	
	
	public void buildPage(RequestEntry requestEntry,SQL sql, int pageSize, String sflag){
		Pager pager = null;
		if(sflag != null && sflag.equals(PageConstraints.SQLSERVER_FALG)){
			pager = new HqlPager(requestEntry ,"hibernateTemplateForServer" );
			//pager.setHibernateTemplate((HibernateTemplateExt)SpringBeanLoader.getBean("hibernateTemplateForServer"));
		}else{
			pager = new HqlPager(requestEntry);
		}
		pager.setSqlObj(sql);
		pager.setPageSize(pageSize);
		//requestEntry.setSessionAttribute("PAGE_SQL", sql);
		requestEntry.setAttribute("pageCount", pager.getCount());
		requestEntry.setAttribute("pageData", pager.getPagerData());
		requestEntry.setAttribute("pager", pager);
		//pager.prepareQuery(queryMap,searchableFields);
	}

	public String getIgnoreAuth() {
		return ignoreAuth;
	}

	public void setIgnoreAuth(String ignoreAuth) {
		this.ignoreAuth = ignoreAuth;
	}
}
