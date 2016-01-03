<%@ include file="/basepage/module/datalist_header.jsp" %>

<form name="form1" id="form1" action="<%=basePath%>redirect/user-create.od?act=new" method="post">
	<h3 class="ui dividing header">注册新用户</h3>
	<div class="html">
	<div class="ui form">
		<div class="required field">
			<label>账号</label> <input placeholder="账号" name="userId" id="userId" type="text">
		</div>
		<div class="required field">
			<label>用户名</label> <input placeholder="用户名" name="userName" id="userName" type="text">
		</div>
		<div class="required field">
			<label>密码</label> <input placeholder="密码" type="password" name="passkey" id="passkey">
		</div>
		<div class="required field">
			<label>确认密码</label> <input placeholder="确认密码" type="password" name="confirmPasskey" id="confirmPasskey">
		</div>
	</div>
	</div>
	<div class="ui stackable equal width grid">
		<div class="column">
			<div class="spaced">
				<button type="submit" class="teal ui button">保存</button>
				<!-- <button type="button" class="teal ui button" onclick="javascript:submitForm()">保存</button> -->
				<button type="button" onclick="javascript:window.history.go(-1);" class="ui small button">返回</button>
			</div>
		</div>
	</div>
</form>
