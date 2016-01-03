package tk.core.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class UrlRewriter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		String redirectUrl = this.getRealUrl((HttpServletRequest)request);
		//chain.doFilter(request, response);
		request.getRequestDispatcher(redirectUrl).forward(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	private String getRealUrl(HttpServletRequest httpRequest){
		String queryStr = httpRequest.getQueryString();
		String contextPath = httpRequest.getContextPath();
		String requestUrl = httpRequest.getRequestURI();
		requestUrl = requestUrl.substring(contextPath.length());
		///ts/index/app-view.re
		String virtualUrl = requestUrl.substring(requestUrl.lastIndexOf("/")+1,requestUrl.lastIndexOf("."));
		String[] invokeObjs = virtualUrl.split("-");
		String url = requestUrl.substring(0,requestUrl.lastIndexOf("/"))+".jsp";
		url = url + "?objectName="+invokeObjs[0]+"&eventName="+invokeObjs[1];
		if(queryStr != null)
			url = url +"&"+ queryStr;
		return url;
	}

}
