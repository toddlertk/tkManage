<%@ include file="/basepage/module/datalist_header.jsp" %>
<script>
 	function submitForm(){
	 	var rules = {
	 				  formName:'form1'
		};
		validateForm(rules,"","");
	}
 	function searchUser(activeId){
 		var url = "<%=basePath%>admin/role/rulist/role-rulist.od?";
 		var userName = $("#searchUserName").val();
 		var searchRoleName = $("#searchRoleName").val();
 		url = url + "&searchUserName="+encodeURI(userName) + "&searchRoleName=" + searchRoleName + "&roleId=${role.roleId}";
 		window.location.href = url;
 	}
 	$(document).ready(function() {  
 		var result = "<c:out value="${result}"></c:out>";
 		if(result=="success")
 			alert('操作成功');
 		else if(result != '')
 	     	 alert('"<c:out value="${result}"></c:out>"');
        //$("#category_title").text(CATEGORY_T);
       
     });
</script>
<div>
	<h3 class="ui dividing header">用户角色列表</h3>
	<div class="ui two column relaxed grid">
		<div class="column left">
			<div class="ui labeled input">
				<a class="ui label">用户名 </a> <input type="text"
					name="searchUserName" id="searchUserName" value="${searchUserName}"
					placeholder="用户">
			</div>
			<div class="ui labeled input">
				<a class="ui label">角色名 </a> <input type="text" name="searchRoleName"
					id="searchRoleName" value="${searchUserId}" placeholder="角色名">
			</div>
			<a href="javascript:searchUser('${role.roleId}')" class="blue ui button"><span>查询</span></a>
			<c:if test="${!empty role}">
			<a href="<%=basePath %>admin/role/userList/role-allot.od?act=preAdd&roleId=${role.roleId}"
				class="green ui button"><span>新建</span></a>
			</c:if>
		</div>

		<table class="ui celled right aligned table">
			<thead>
				<tr>
					<th>角色名</th>
					<th>用户名</th>
					<th>#</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="p" items="${pageData}">
					<tr>
						<td><c:out value="${p[0].roleName}"></c:out></td>
						<td><c:out value="${p[1].userName}"></c:out></td>
						<td><a
							href="<%=basePath%>admin/role/rulist/role-allot.od?act=del&roleId=<c:out value="${p[0].roleId}"></c:out>&userId=<c:out value="${p[1].userId}"></c:out>">取消关联</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<%@ include file="/basepage/pager.jsp" %>
