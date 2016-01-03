<%@ include file="/basepage/module/datalist_header.jsp" %>

    <script>
    jQuery(document).ready(function() {  
    	var result = "<c:out value="${result}"/>";
    	//var url = "<c:out value="${url}"/>";
    	var url = "<c:out value="${url}" escapeXml="false"/>";
    	if(result == "SUCCESS"){
        	alert('提交成功，正在跳转...');
    		window.location.href = "<%=basePath%>" + url;
        }else if(result == "NoMsg"){
    		window.location.href = "<%=basePath%>" + url;
        }else {
        	alert('提交失败 : ' + result);
        	window.history.go(-1);
        }
    });
    </script>

<%@ include file="/basepage/module/datalist_footer_nopage.jsp" %>
