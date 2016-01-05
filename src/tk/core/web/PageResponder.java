package tk.core.web;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tk.core.context.SpringBeanLoader;
import tk.core.db.SQL;
import tk.core.db.template.HibernateTemplateExt;
import tk.entities.org.User;
import tk.utils.StringUtils;

public class PageResponder {
	
	private static final String SUFFIX = "Web";
	
	public static ResponseResult invokeRequest(HttpServletRequest request, HttpServletResponse response){
		try{
			/*String page = request.getRequestURI();
			String contex = request.getContextPath();
			page = page.replaceFirst(contex+"/", "");
			String beanName = PageAssemblyParser.getBeanName(page.toUpperCase());*/
			String page = request.getRequestURI();
			String contex = request.getContextPath();
			page = page.replaceFirst(contex+"/", "");
			String beanName = request.getParameter("objectName");
			
			if(page.equals(""))
				return null;
			if(page.equals("login.jsp") || StringUtils.isNullOrEmpty(beanName))
				return null;
			
			String methodName = request.getParameter("eventName");
			methodName = methodName.substring(0,1).toLowerCase()+methodName.substring(1);
			
			BasePage basePage = (BasePage)SpringBeanLoader.getBean(beanName+SUFFIX);
			
			RequestEntry requestEntry = new RequestEntry();
			requestEntry.setRequest(request);
			requestEntry.setResponse(response);
			requestEntry.setMethodName(methodName);
			requestEntry.setWebActionBeanName(beanName);
			
			try{
				String ignoreAuthFlag = basePage.getIgnoreAuth();
							
				if(ignoreAuthFlag.toUpperCase().equals("N")){

					
					if(!beanName.equals("login") && !checkAuthenticate(requestEntry)){
						return checkAuthError(requestEntry);
					}
					//校验权限
					if(!beanName.equals("login") && !checkPrivilege(requestEntry)){
						return checkPrivilegeError(requestEntry);
					}
					basePage.beforeExcuteMethod(requestEntry);
					ResponseResult result = (ResponseResult)excute(basePage, methodName, requestEntry);
					basePage.afterExcuteMethod(requestEntry);

					if(result !=null){
						String url = result.getRedirectUrl();
						if(url != null){
							String path = requestEntry.getRequest().getContextPath();
							response.sendRedirect(path+url);
						}
					}
					return result;
				}
				
			}catch(Exception e){
				throw e;
			}
		}catch(Throwable e){
			e.printStackTrace();
			request.setAttribute("ex", e);
			e.getStackTrace();
			throw new RuntimeException(e);
		}
		return null;
	}
	
	private static Object excute(BasePage basePage, String methodName,RequestEntry requestEntry) throws Throwable{
		Method method = basePage.getClass().getMethod(methodName,RequestEntry.class);
		try{
			return method.invoke(basePage, requestEntry);
		}catch(Exception e){
			if(e instanceof InvocationTargetException){
				InvocationTargetException iex = (InvocationTargetException)e;
				throw iex.getTargetException();
			}
			else
				throw e;
		}
	}
	
	private static boolean checkAuthenticate(RequestEntry requestEntry){
		String userId = requestEntry.getUserId();
		if(StringUtils.isNullOrEmpty(userId)){
			User user = HibernateTemplateExt.getInstance().get(User.class, "guest");
			if(user != null){
				userId = user.getUserId();
				requestEntry.setSessionAttribute("userId", user.getUserId());
			}
		}
		return !StringUtils.isNullOrEmpty(userId);
	}
	
	private static ResponseResult checkKfCustomer(RequestEntry requestEntry) throws Throwable{
	
		String path = requestEntry.getRequest().getContextPath();
		String url =path+"/func/kfcustomer/edit/kfCustomer-visit.re";
		
		requestEntry.getResponse().sendRedirect(url);
		return null;
	}
	
	private static ResponseResult checkAuthError(RequestEntry requestEntry) throws Throwable{
		String path = requestEntry.getRequest().getContextPath();
		requestEntry.getResponse().sendRedirect(path+"/login.jsp");
		return null;
	}
	private static ResponseResult checkPrivilegeError(RequestEntry requestEntry) throws Throwable{
		String path = requestEntry.getRequest().getContextPath();
		requestEntry.getResponse().sendRedirect(path+"/prohibit.jsp");
		return null;
	}
	
	private static boolean checkPrivilege(RequestEntry requestEntry){
		String userId = requestEntry.getUserId();
		
		SQL sql = SQL.begin().sql("select f from Function f , RoleFunction rf , Module m ")
				.sql(" where f.functionKey=rf.id.functionKey and f.moduleKey=m.moduleKey "
						+ "and m.authKey=? and f.authKey=?",
							requestEntry.getWebActionBeanName(),
							requestEntry.getMethodName())
				.sql(" and rf.id.roleId in("
						+ "select ru.id.roleId from RoleUser ru where ru.id.userId=?)",userId)
				.end();
		List<?> functionList = HibernateTemplateExt.getInstance().find(sql);
		if(functionList.size() >0)
			return true;
		else
			return false;
	}
}
