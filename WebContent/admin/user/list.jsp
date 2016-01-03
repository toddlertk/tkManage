<%@ include file="/basepage/module/datalist_header.jsp" %>
<script>
 	function submitForm(){
	 	var rules = {
	 				  formName:'form1'
		};
		validateForm(rules,"","");
	}
 	function searchUser(){
 		var url = "<%=basePath%>admin/user/list/user-create.od?act=list";
 		var userName = $("#searchUserName").val();
 		var userId = $("#searchUserId").val();
 		url = url + "&searchUserName="+encodeURI(userName) + "&searchUserId=" + userId;
 		window.location.href = url;
 	}
 	$(document).ready(function() {  
 		var result = "<c:out value="${result}"></c:out>";
 		if(result=="success")
 			alert('操作成功');
 		else if(result != ''){
 			alert('"<c:out value="${result}"></c:out>"');
 			window.history.go(-1);
 		}
        //$("#category_title").text(CATEGORY_T);
       
     });
</script>
<div>
<h3 class="ui dividing header">用户列表</h3>
<div class="ui two column relaxed grid">
	<div class="column left">
		<div class="ui labeled input">
			<a class="ui label">用户 </a> <input type="text" name="searchUserName"
				id="searchUserName" value="${searchUserName}" placeholder="用户">
		</div>
		<div class="ui labeled input">
			<a class="ui label">账号 </a> <input type="text" name="searchUserId"
				id="searchUserId" value="${searchUserId}" placeholder="账号">
		</div>
		<a href="javascript:searchUser()" class="blue ui button"><span>查询</span></a>
		<a href="<%=basePath%>admin/user/add/user-create.od?act=preAdd"
			class="green ui button"><span>注册</span></a>
	</div>
	<table class="ui celled right aligned table">
   	<thead>
   	  	<tr>
   	  		<th>账号</th>
   	  		<th>用户名</th>
   	  		<th>状态</th>
   	  		<th>#</th>
   	  		<th>#</th>
   	  	</tr>
	</thead>
   	<tbody>
   	<c:forEach var="p" items="${pageData}">
      	<tr>
      		<td ><c:out value="${p.userId}"></c:out></td>
			<td ><c:out value="${p.userName}"></c:out></td>
			<td ><c:if test="${p.userStatus=='0'}" >可用</c:if><c:if test="${p.userStatus=='1'}" >不可用</c:if></td>
			<td><a href="<%=basePath%>admin/user/edit/user-create.od?act=preEdit&userId=<c:out value="${p.userId}"></c:out>">编辑</a></td>
			<td><a href="<%=basePath%>admin/user/list/user-create.od?act=delete&userId=<c:out value="${p.userId}"></c:out>">停用</a></td>
	  	</tr>
	</c:forEach>
   	</tbody>
</table>
</div>
</div>
<%@ include file="/basepage/pager.jsp"%>
