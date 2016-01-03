<%@ include file="/basepage/module/datalist_header.jsp" %>

<form name="form1" id="form1" action="<%=basePath%>admin/modulerole/view/moduleRole-view.od?roleId=<c:out value="${role.roleId}"/>" method="post">

<h3 class="ui dividing header">查看权限[${role.roleName}]</h3>
<div class="ui two column relaxed grid">
	<table class="ui celled left aligned table">
   	<tbody>
   	<c:forEach var="cat" items="${categoryList}">
      	<tr>
			<td colspan="3"><c:out value="${cat.moduleCategoryName}"/></td>
	  	</tr>
	  	<c:forEach var="module" items="${moduleMap[cat.moduleCategoryKey]}">
	  	<tr>
			<td style="width:60px">&nbsp;</td>
			<td style="width:200px"><c:out value="${module.moduleName }"/></td>
			<td>
			<c:forEach var="function" items="${functionMap[module.moduleKey]}">
				<input type="checkbox" disabled="disabled" name="chkFuncs" <c:if test="${!empty grantedFuncMap[function.functionKey] }">checked="checked"</c:if> value="<c:out value="${function.functionKey}"/>"/><c:out value="${function.functionName}"/>&nbsp;&nbsp;&nbsp;
			</c:forEach>			
			</td>
	  	</tr>
	  	</c:forEach>
	</c:forEach>
   	</tbody>
</table>
</form>
<div class="ui stackable equal width grid">
	<div class="column">
		<div class="spaced">
			<button type="button"
				onclick="window.location.href='<%=basePath%>admin/modulerole/list/moduleRole-view.od'"
				class="ui small button">返回</button>
		</div>
	</div>
</div>