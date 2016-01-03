<%@ include file="/basepage/module/datalist_header.jsp" %>
<script>
 	function searchUser(roleId){
 		var url = "<%=basePath%>admin/role/userList/role-allot.od?act=preAdd&roleId=" + roleId;
 		var userName = $("#searchUserName").val();
 		var userId = $("#searchUserId").val();
 		url = url + "&searchUserName="+encodeURI(userName) + "&searchUserId=" + userId;
 		window.location.href = url;
 	}
</script>
<form action="<%=basePath%>admin/role/rulist/role-allot.od?act=add&roleId=${roleId}" method="post">
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
			<a href="javascript:searchUser('${roleId}')" class="blue ui button"><span>查询</span></a>
		</div>
		<table class="ui celled right aligned table">
			<thead>
				<tr>
					<th>#</th>
					<th>账号</th>
					<th>用户名</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="p" items="${pageData}">
					<tr>
						<td><input type="checkbox" name="userId"
							value="<c:out value="${p.userId}"/>"></td>
						<td><c:out value="${p.userId}"></c:out></td>
						<td><c:out value="${p.userName}"></c:out></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="ui stackable equal width grid">
			<div class="column">
				<div class="spaced">
					<button type="submit" class="teal ui button">保存</button>
					<!-- <button type="button" class="teal ui button" onclick="javascript:submitForm()">保存</button> -->
					<button type="button" onclick="javascript:window.history.go(-1);"
						class="ui small button">返回</button>
				</div>
			</div>
		</div>
	</div>
</form>
<%@ include file="/basepage/pager.jsp" %>
