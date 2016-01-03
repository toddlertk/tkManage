<%@ include file="/basepage/module/datalist_header.jsp" %>

<script>
 	var TABLE_NAME = "新建角色";
 	function submitForm(){
 		var roleId = document.getElementById("roleId");
 		var reg = roleId.value.replace(/\s/g,'');
 		var rules = {
 				formName:'form1',
 				//reg:{minLength:10,maxLength:10,type:"string"},
 				roleId:{minLength:10,maxLength:10,type:"string"}
	};
	validateForm(rules,"","");
}
 	
</script>

<form name="form1" id="form1" action="<%=basePath%>admin/role/list/role-create.od?act=add" method="post">
<h3 class="ui dividing header">注册新用户</h3>
	<div class="html">
	<div class="ui form">
		<div class="required field">
			<label>角色ID</label> <input placeholder="角色ID" name="roleId" id="roleId" type="text">
		</div>
		<div class="required field">
			<label>角色名称</label> <input placeholder="角色名称" name="roleName" id="roleName" type="text">
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
