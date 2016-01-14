<%@ include file="/basepage/module/datalist_header.jsp" %>
<script>

	$(document).ready(function() {
		$('.ui.form').form({
			fields : {
				activeName : 'empty' ,
				activeIndex : 'empty',
				begTime :  'empty' ,
				endTime : 'empty' 
			}
		});
	});
</script>
<form class="ui form" action="<%=basePath%>redirect/active-create.od?act=create" method="post">
	<p class="ui dividing header">注册新用户</p>
	<div class="two fields">
		<div class="field">
			<label>节目名称</label> <input placeholder="节目名称" name="activeName" id="activeName" value="${ active.activeName}" type="text">
		</div>
		<div class="field">
			<label>节目序号</label> <input placeholder="节目序号" name="activeIndex" id="activeIndex" value="${ active.activeIndex}" type="text">
		</div>
	</div>

	<div class="two fields">
		<div class="field">
			<label>开始时间</label> <input placeholder="yyyy-MM-dd HH:mm:ss" type="text" name="begTime" id="begTime" value="${ active.begTime}"/>
		</div>
		<div class="field">
			<label>结束时间</label> <input placeholder="yyyy-MM-dd HH:mm:ss" type="text" name="endTime" id="endTime" value="${ active.endTime}"/>
		</div>
	</div>
	
    <div class="ui blue submit button">保存</div>
	<button type="button" onclick="javascript:window.history.go(-1);" class="ui small button">返回</button>
    <div class="ui error message"></div>
	<!-- <div class="ui stackable equal width grid">
		<div class="column">
			<div class="spaced">
				<button type="submit" class="teal ui button">保存</button>
				<button type="button" class="teal ui button" onclick="javascript:submitForm()">保存</button>
			</div>
		</div>
	</div> -->
</form>
</div>
  <script>
window.less = {
  async        : true,
  environment  : 'production',
  fileAsync    : false,
  onReady      : false,
  useFileCache : true
};
</script>