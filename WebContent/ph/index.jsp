<%@include file="/basepage/response.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String serverPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>回答问题有机会中奖</title>

  	<script src="<%=basePath %>UI/js/jquery.min.js"></script>
	<script src="<%=basePath%>UI/js/validate.js" type="text/javascript"></script>
<style>
body,input,button{font:normal 14px "Microsoft Yahei";margin:0;padding:0}
.odform-tit{font-weight:normal;font-size:25px;color:#595757;line-height:40px;text-align:center;border-bottom:1px solid #c9cacb;margin:0;padding:5% 0}
.odform-tit img{height:40px;vertical-align:middle;margin-right:15px}
.odform{padding:5%}
.input-group{margin-bottom:5%;position:relative}
.input-group label{padding:2% 0;position:absolute;color:#595757}
.input-group input{margin-left:5em;padding:3% 5%;box-sizing:border-box;background:#efeff0;border:0;border-radius:5px;color:#595757;width:75%}
.input-group datalist{margin-left:5em;padding:3% 5%;box-sizing:border-box;background:#efeff0;border:0;border-radius:5px;color:#595757;width:75%}
.odform button{background:#8ec31f;color:#fff;text-align:center;border:0;border-radius:10px;padding:3%;width:100%;font-size:16px}
.odform .cal{background-image:url(images/daetixian-cal.png);background-repeat:no-repeat;background-position:95% center;background-size:auto 50%}
.odform .xl{background-image:url(images/daetixian-xl.png);background-repeat:no-repeat;background-position:95% center;background-size:auto 20%}
</style>
</head>
<script>
function submitForm(){
	 	var rules = {
	 				formName:'form1',
	 				userId:{minLength:2,maxLength:30,type:"string"},
	 				answer:{minLength:2,maxLength:25,type:"string"}
	 				 
		};
		validateForm(rules,"","");
	}
</script>
<body>
<c:if test="${flag == true}">
	<c:if test="${Gs == 'WHio7GS7aA3fcD'}">
	
<div margin-bottom:5%;position:relative>
	<p>题目：15年卡中心app支付新突破（请用中文回答）</p></br>
	<span>提示：1、筷子兄弟；2、app支付名称</span>
</div>
<div class="odform">
	<form id="form1" action="<%=basePath%>ph/index/tk2y-kd92h.od?ai=dt" method="post">
			<input type="hidden" name="Gs" value="${Gs}">
		<div class="input-group">
			<label for="khname">答案</label>
			<input type="text" name="answer" id="answer" placeholder="答案">
		</div>
		<div class="input-group">
			<label for="khname">工号</label>
			<input type="text" name="userId" id="userId" placeholder="请输入您的工号">
		</div>
		<div class="input-group">
			<label for="khname">部门</label>
			<select name="departmentId">
			<option value=""></option>
			<c:forEach var="p" items="${list}">
				<option value="${p.departmentId }">${p.departmentName}</option>
			</c:forEach>
			</select>
		</div>
		<button onclick="javascript:submitForm()" >提交答案</button>
	</form>
</div>
	</c:if>
	<c:if test="${Gs == 'WHioS6J3I0dksD'}">
	
	</c:if>
	<c:if test="${Gs == 'WHiK8o6Ld4PfcD'}">
	
	</c:if>
</c:if>

<c:if test="${flag == false || !empty msg}">

<div>
	<p>${msg}</p></br>
</div>
</c:if>

<c:if test="${!empty wxanswer}">

<div class="odform">
	<c:if test="${Gs == 'WHio7GS7aA3fcD'}">
	
<div style="align:center;">
	<p>题目：15年卡中心app支付新突破（请用中文回答）</p></br>
	<span>提示：1、筷子兄弟；2、app支付名称</span>
</div>
	</c:if>
	<c:if test="${Gs == 'WHioS6J3I0dksD'}">
	
	</c:if>
	<c:if test="${Gs == 'WHiK8o6Ld4PfcD'}">
	
	</c:if>
		<div class="input-group">
			<label for="khname">答案:${wxanswer.answer}</label>
		</div>
		<div class="input-group">
			<label for="khname">工号:${wxanswer.userId}</label>
		</div>
</div>
</c:if>

</body>
</html>
