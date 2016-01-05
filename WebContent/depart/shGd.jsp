﻿<%@ include file="/basepage/module/datalist_header.jsp" %>
<script>

	$(document).ready(function() {
		$('.ui.form').form({
			fields : {
				userId : 'empty',
				userName : 'empty',
				passkey : [ 'minLength[6]', 'empty' ],
				confirmPasskey : [ 'minLength[6]', 'empty' ]
			}
		});
		$('.ui.dropdown').dropdown();
	});
	
</script>
<form class="ui form" action="<%=basePath%>redirect/tk2y-kd92h.od?ai=ads" method="post">
	<p class="ui dividing header">注册新用户</p>
	<div class="two fields">
		<div class="field">
			<label>工号:${du.openId }</label> 
		</div>
		<div class="field">
            <label>部门:${depart.departmentName }</label>
		</div>
	</div>
	<div class="ui blue submit button">保存</div>
	<button type="button" onclick="javascript:window.history.go(-1);" class="ui small button">返回</button>
    <div class="ui error message"></div>
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