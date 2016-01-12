<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="tk.weixin.core.entry.WXHandler" %>
 
<%
	//WeiXinHandler为内部类不能使用非final类型的对象
	WXHandler t = new WXHandler(request , response);
	t.valid();
%>