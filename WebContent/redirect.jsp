<%@ include file="/basepage/module/datalist_header.jsp" %>

    <script>
    jQuery(document).ready(function() {  
    	var result = "<c:out value="${result}"/>";
    	//var url = "<c:out value="${url}"/>";
    	var url = "<c:out value="${url}" escapeXml="false"/>";
    	if(result == "SUCCESS"){
    		var obj = $('.small.modal').modal('show');
    		alert(obj);
    		window.location.href = "<%=basePath%>" + url;
        }else if(result == "NoMsg"){
    		window.location.href = "<%=basePath%>" + url;
        }else {
    		alert($('.small.modal').modal('show'));
        	window.history.go(-1);
        }
    });
    </script>

<!-- <div class="ui small modal"> -->
<div class="ui active modal">
	<div class="header">Header</div>
	<div class="content">
		<p>${result}23213213</p>
	</div>
	<div class="actions">
		<div class="ui negative button">No</div>
		<div class="ui positive right labeled icon button">Yes</div>
	</div>
</div>
<%@ include file="/basepage/module/datalist_footer_nopage.jsp" %>
