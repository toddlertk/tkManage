package tk.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestEntry {
	private HttpServletRequest request; 
	private HttpServletResponse response; 
	private String methodName;
	private String webActionBeanName;
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getWebActionBeanName() {
		return webActionBeanName;
	}
	public void setWebActionBeanName(String webActionBeanName) {
		this.webActionBeanName = webActionBeanName;
	}
	
	public String getParameter(String key){
		request.setAttribute(key, request.getParameter(key));
		return request.getParameter(key);
	}		
	
	public String[] getParameterValues(String key){
		return request.getParameterValues(key);
	}
	
	public void setAttribute(String key,Object value){
		this.request.setAttribute(key, value);
	}
	
	public void setSessionAttribute(String key,Object value){
		this.request.getSession().setAttribute(key, value);
	}
	public void removeSessionAttribute(String key){
		this.request.getSession().removeAttribute(key);
	}


	public String getUserId(){
		String userId = (String)this.request.getSession().getAttribute("userId");	
		return userId;
	}
}
