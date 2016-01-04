<%@ include file="/basepage/module/datalist_header.jsp" %>
<script>
	$(document).ready(function() {
		$('.ui.form').form({
			fields : {
				userId : 'empty',
				userName : 'empty'
			}
		});
	});
 	function change(){
 		var chk = document.getElementsByName("pws");

 		//alert(chk[0].checked);
 		if(chk[0].checked == true){
 			$("#chk_pws").show();
 		}else{
 			$("#chk_pws").hide();
 		}
 		$('.small.modal').modal('show');
 	}
</script>

<form class="ui form" action="<%=basePath%>redirect/user-create.od?act=edit" method="post">
<p class="ui dividing header">注册新用户</p>
	<div class="two fields">
		<div class="field">
			<label>账号</label> <input placeholder="账号" name="userId" id="userId"
				type="text" value="${user.userId }">
		</div>
		<div class="field">
			<label>用户名</label> <input placeholder="用户名" name="userName"
				id="userName" type="text" value="${user.userName }">
		</div>
	</div>
	<div class="two fields">
		<div class="field">
			<label>用户状态</label> <select name="userStatus">
				<option value="0"
					<c:if test="${user.userStatus=='0'}">selected="selected"</c:if>>启用</option>
				<option value="1"
					<c:if test="${user.userStatus=='1'}">selected="selected"</c:if>>停用</option>
			</select>
		</div>
		<div class="field">
			<label>重置密码</label><input type="checkbox" value="pws" name="pws" onclick="change()">
		</div>
	</div>
	<div class="two fields">
		<div id="chk_pws" style="display: none">
			<div class="field">
				<label>密码</label> <input placeholder="密码" type="password"
					name="passkey" id="passkey">
			</div>
			<div class="field">
				<label>确认密码</label> <input placeholder="确认密码" type="password"
					name="confirmPasskey" id="confirmPasskey">
			</div>
		</div>
	</div>
	
    <div class="ui blue submit button">保存</div>
	<button type="button" onclick="javascript:window.history.go(-1);" class="ui small button">返回</button>
</form>

<div class="ui small modal">
	<div class="header">Header</div>
	<div class="content">
		<p>${result}23213213</p>
	</div>
	<div class="actions">
		<div class="ui negative button">No</div>
		<div class="ui positive right labeled icon button">Yes</div>
	</div>
</div>
</div>
