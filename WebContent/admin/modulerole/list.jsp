<%@ include file="/basepage/module/datalist_header.jsp" %>

<h3 class="ui dividing header">模块列表</h3>
<div class="ui two column relaxed grid">
	<table class="ui celled right aligned table">
		<thead>
			<th class="left aligned">角色名称</th>
			<th>&nbsp;</th>
		</thead>
		<tbody>
			<c:forEach var="role" items="${roleList}">
				<tr>
					<td class="left aligned">${role.roleName}</td>
					<td><a
						href="<%=basePath%>admin/modulerole/view/moduleRole-view.od?act=pre&roleId=<c:out value="${role.roleId}"/>">查看</a>
						&nbsp;&nbsp; <a
						href="<%=basePath%>admin/modulerole/edit/moduleRole-grant.od?act=pre&roleId=<c:out value="${role.roleId}"/>">设置权限</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</div>