<%@ include file="/basepage/module/datalist_header.jsp" %>
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
	});
</script>
<form class="ui form" action="<%=basePath%>redirect/user-create.od?act=new" method="post">
	<p class="ui dividing header">注册新用户</p>
	<div class="two fields">
		<div class="field">
			<label>账号</label> <input placeholder="账号" name="userId" id="userId" type="text">
		</div>
		<div class="field">
			<label>用户名</label> <input placeholder="用户名" name="userName" id="userName" type="text">
		</div>
	</div>

	<div class="two fields">
		<div class="field">
			<label>密码</label> <input placeholder="密码" type="password"
				name="passkey" id="passkey">
		</div>
		<div class="field">
			<label>确认密码</label> <input placeholder="确认密码" type="password"
				name="confirmPasskey" id="confirmPasskey">
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