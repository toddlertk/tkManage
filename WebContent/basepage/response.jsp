<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" import="java.util.*,tk.core.web.*" pageEncoding="utf-8"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%
	try{
		ResponseResult result = PageResponder.invokeRequest(request,response);	
	}catch(Exception ex){
		throw new RuntimeException(ex);
	}
%>