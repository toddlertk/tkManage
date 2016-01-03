<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="tk.weixin.core.entry.WXServerIPHelper,tk.weixin.core.json.JsonHelper" %>

<%
	WXServerIPHelper helper = new WXServerIPHelper();
	String urlStr = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx1d258aa33c7ef2d7&secret=c6757b3d20626d95e1850544c5b46b79";
	String token = "";
	String s = helper.get(urlStr, token);
	JsonHelper json = new JsonHelper();
	out.println(s);
	String tt = json.getStrByJson(s, "access_token");
	out.println(tt);
	urlStr = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=";
	token = "toddler";
	s = helper.get(urlStr, tt);
	out.println(s);
	tt = json.getStrByJson(s, "ip_list");
	out.println(tt);
%>
