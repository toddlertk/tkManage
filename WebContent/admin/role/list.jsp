<%@ include file="/basepage/module/datalist_header.jsp" %>
<script>
 	var TABLE_NAME = "角色列表";

 	$(document).ready(function() {  
 		var result = "<c:out value="${result}"></c:out>";
 		if(result=="success")
 			alert('操作成功');
 		else if(result != '')
 	     	 alert('"<c:out value="${result}"></c:out>"');
        //$("#category_title").text(CATEGORY_T);
       
     });
</script>
<div class="ui two column relaxed grid">
	<div class="column left">
		<label class="ui dividing header">角色列表</label>
		<a href="<%=basePath %>admin/role/add/role-create.od" class="green ui button"><span>新建角色</span></a>
	</div>
	<table class="ui celled right aligned table">
   	<thead>
   	  	<tr>
   	  		<th>角色ID</th>
   	  		<th>角色名称</th>
   	  		<th>#</th>
   	  		<th>#</th>
   	  		<th>#</th>
   	  	</tr>
	</thead>
   	<tbody>
   	<c:forEach var="role" items="${rolelist}">
      	<tr>
			<td><c:out value="${role.roleId}"></c:out></td>
			<td><c:out value="${role.roleName}"></c:out></td>
			<td>
				<a href="<%=basePath%>admin/role/rulist/role-rulist.od?act=read&roleId=<c:out value="${role.roleId}"/>">角色归属</a>
			</td>
			<td>
				<a href="<%=basePath%>admin/role/edit/role-edit.od?act=preEdit&roleId=<c:out value="${role.roleId}"/>">修改</a>
			</td>
			<td>
				<a href="<%=basePath%>admin/role/list/role-edit.od?act=del&roleId=<c:out value="${role.roleId}"/>">删除</a>
			</td>
	  	</tr>
	</c:forEach>
   	</tbody>
</table>
