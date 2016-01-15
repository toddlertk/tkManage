<%@ include file="/basepage/module/datalist_header.jsp" %>
<script>

</script>
<h3 class="ui dividing header">节目列表</h3>
<div class="ui two column relaxed grid">
	<div class="column">
		<a href="<%=basePath%>admin/active/add/active-create.od?act=pre" class="green ui button"><span>注册</span></a>
	</div>
	<table class="ui celled table">
   	<thead>
   	  	<tr>
   	  		<th>节目序号</th>
   	  		<th>节目名称</th>
   	  		<th>节目开始时间</th>
   	  		<th>节目结束时间</th>
   	  		<th>#</th>
   	  		<th>#</th>
   	  		<th>#</th>
   	  	</tr>
	</thead>
   	<tbody>
   	<c:forEach var="p" items="${list}">
      	<tr>
      		<td ><c:out value="${p.activeIndex}"></c:out></td>
			<td ><c:out value="${p.activeName}"></c:out></td>
			<td >${p.begTime}</td>
			<td >${p.endTime}</td>
			<td><a href="<%=basePath%>admin/active/list/active-create.od?act=beg&activeIndex=${p.activeIndex}">开始</a></td>
			<td><a href="<%=basePath%>admin/active/add/active-create.od?act=pre&activeIndex=${p.activeIndex}">编辑</a></td>
			<td><a href="<%=basePath%>admin/active/list/active-create.od?act=drop&activeIndex=${p.activeIndex}">结束</a></td>
	  	</tr>
	</c:forEach>
   	</tbody>
</table>
</div>
