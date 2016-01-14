<%@include file="/basepage/response.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String serverPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <title>工号部门与微信进行绑定</title>
    <link href="<%=basePath%>ph/css/form.min.css" rel="stylesheet" type="text/css">
  	<script src="<%=basePath %>UI/js/jquery.min.js"></script>
	<script src="<%=basePath%>UI/js/validate.js" type="text/javascript"></script>
    <style type="text/css">
        #bottom_info {
            position: relative;
            top: -3.8em;
            margin: 0 0.5em;
            padding: .8em 0;
            text-align: center;
            background-color: #18c178;
            color: #ffffff;
            border: medium hidden;
            border-radius: 0.1em;
            box-sizing: border-box;
            display: none;
        }

        #bottom_info a {
            /*
            color: #adadad;
            */
            width: 100%;
            text-decoration: none;
            outline: none;
        }

        #bottom_jump {
            position: relative;
            top: -3em;
            margin: 0 0.5em;
            padding: .8em 0;
            text-align: center;
            background-color: #62A9E3;
            color: #ffffff;
            border: medium hidden;
            border-radius: 0.1em;
            box-sizing: border-box;
            display: none;
        }
    </style>
</head>

<script>
function submitForm(){
	if ($("#departmentId").val() == '#') {
			alert('请选择所属部门');
			return;
		}
		var rules = {
			formName : 'main_form',
			userId : {
				minLength : 2,
				maxLength : 30,
				type : "string"
			},
			userName : {
				minLength : 2,
				maxLength : 30,
				type : "string"
			}

		};
		validateForm(rules, "", "");
	}
</script>
<body>
<div id="subjects">
    <c:if test="${empty du }">
    <form id="main_form" action="<%=basePath%>ph/depart/tk2y-kd92h.od?ai=ads" method="post"
    	 accept-charset="utf-8" style="padding-bottom: 4em;">
            <input type="hidden" name="openId" value="${openId}" id="openId" >
        <div class="form_ctrl page_head" id="1" title=""><h2>微信绑定工号部门</h2></div>
        <div class="form_ctrl input_text" id="5" title="工号">
            <label class="ctrl_title">工号</label>
            <input type="text" name="userId" value="" id="userId" placeholder="请输入您的工号">
        </div>
        <div class="form_ctrl input_text" id="5" title="姓名">
            <label class="ctrl_title">姓名</label>
            <input type="text" name="userName" value="" id="userName" placeholder="请输入您的姓名">
        </div>
        <div class="form_ctrl form_select" id="9" title="性别">
            <label class="ctrl_title">部门</label>
            <select name="departmentId" num="1"><option value="#">--请选择--</option>
				<c:forEach var="p" items="${list}">
					<option value="${p.departmentId }">${p.departmentName}</option>
				</c:forEach>
            </select>
            <div></div>
        </div>
    </form>
    <div id="bottom_info" style="display: block;" onclick="javascript:submitForm()">提交答案</div>
    </c:if>
		<c:if test="${!empty du }">
			<div class="form_ctrl page_head" id="1" title="">
				<h2>微信绑定工号部门</h2>
			</div>
			<table class="table" style="text-align: center;">
				<tbody>
					<tr>
						<td width="10%">绑定结果</td>
						<td><span>绑定成功！</span></td>
					</tr>
					<tr>
						<td width="10%">工号</td>
						<td><span>${du.accountId}</span></td>
					</tr>
					<tr>
						<td width="10%">姓名</td>
						<td><span>${du.userName}</span></td>
					</tr>
					<tr>
						<td>部门</td>
						<td><span>
					<c:forEach var="p" items="${list}">
					<c:if test="${p.departmentId==du.departmentId }">${p.departmentName}</c:if>
					</c:forEach></span></td>
					</tr>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>