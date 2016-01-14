<%@ include file="/basepage/module/datalist_header.jsp" %>

    <script>
    jQuery(document).ready(function() {  
    	var result = "<c:out value="${result}"/>";
    	//var url = "<c:out value="${url}"/>";
    	var url = "<c:out value="${url}" escapeXml="false"/>";
    	if(result.toUpperCase() == "SUCCESS"){
    		window.location.href = "<%=basePath%>" + url;
        }else if(result == "NoMsg"){
    		window.location.href = "<%=basePath%>" + url;
        }else {
        	window.history.go(-1);
        }
    });
    </script>
<%@ include file="/basepage/module/datalist_footer_nopage.jsp" %>
