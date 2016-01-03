<%@ include file="/basepage/module/datalist_header.jsp" %>
<script>
 	var TABLE_NAME = "用户注册";
 	
 	function change(){
 		var chk = document.getElementsByName("pws");

 		//alert(chk[0].checked);
 		if(chk[0].checked == true){
 			$("#chk_pws").show();
 		}else{
 			$("#chk_pws").hide();
 		}
 	}
</script>

<form name="form1" action="<%=basePath%>redirect/user-create.od?act=edit" method="post">
<h3 class="ui dividing header">注册新用户</h3>
	<div class="html">
	<div class="ui form">
		<div class="required field">
			<label>账号</label> <input placeholder="账号" name="userId" id="userId" type="text" value="${user.userId }" >
		</div>
		<div class="required field">
			<label>用户名</label> <input placeholder="用户名" name="userName" id="userName" type="text" value="${user.userName }" >
		</div>
		<div class="required field">
			<label>用户状态</label> 
			<select name="userStatus">
				<option value="0" <c:if test="${user.userStatus=='0'}">selected="selected"</c:if>>启用</option>
				<option value="1" <c:if test="${user.userStatus=='1'}">selected="selected"</c:if>>停用</option>
			</select>
		</div>
		<div class="field">
			<label>重置密码</label><input type="checkbox" value="pws" name="pws" onclick="change()" >
		</div>
		<div id="chk_pws" style="display:none">
			<div class="field">
				<label>密码</label> <input placeholder="密码" type="password" name="passkey" id="passkey">
			</div>
			<div class="field">
				<label>确认密码</label> <input placeholder="确认密码" type="password" name="confirmPasskey" id="confirmPasskey">
			</div>
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
