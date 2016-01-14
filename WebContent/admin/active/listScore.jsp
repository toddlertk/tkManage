<%@ include file="/basepage/module/datalist_header.jsp" %>
<script>

</script>
<h3 class="ui dividing header">投票结果列表</h3>
<div class="ui two column relaxed grid">
	<div class="column">
		<a href="<%=basePath%>admin/active/result/active-read.od?act=result" class="green ui button"><span>查看统计结果</span></a>
	</div>
	<table class="ui celled table">
   	<thead>
   	  	<tr>
   	  		<th>节目名称</th>
   	  		<th>投票人</th>
   	  		<th>投票分数</th>
   	  		<th>投票指令</th>
   	  		<th>投票时间</th>
   	  	</tr>
	</thead>
   	<tbody>
   	<c:forEach var="p" items="${list}">
      	<tr>
      		<td >${p[2].activeName}"</td>
      		<td >${p[1].userName}"</td>
      		<td >${p[0].score}"</td>
      		<td >${p[0].scoreText}"</td>
      		<td >${p[0].createTime}"</td>
	  	</tr>
	</c:forEach>
   	</tbody>
</table>
</div>
