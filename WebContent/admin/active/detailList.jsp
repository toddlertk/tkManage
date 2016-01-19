<%@ include file="/basepage/module/datalist_header.jsp" %>
<script>

</script>
<h3 class="ui dividing header">投票结果列表</h3>
<div class="ui two column relaxed grid">
	<div class="column">
	<button type="button" onclick="javascript:window.history.go(-1);" class="ui small button">返回</button>
	</div>
	<table class="ui celled table">
   	<thead>
   	  	<tr>
   	  		<th>节目序号</th>
   	  		<th>节目名称</th>
   	  		<th>部门</th>
   	  		<th>总票数</th>
   	  		<th>总得分</th>
   	  		<th>平均分</th>
   	  	</tr>
	</thead>
   	<tbody>
   	<c:forEach var="p" items="${list}">
      	<tr>
      		<td >${p['ACTIVE_INDEX']}</td>
      		<td >${p['ACTIVE_NAME']}</td>
      		<td >${p['DEPARTMENT_NAME']}</td>
      		<td >${p['NUM']}</td>
      		<td >${p['TOTAL']}</td>
      		<td >${p['AVG']}</td>
	  	</tr>
	</c:forEach>
   	</tbody>
</table>
</div>
