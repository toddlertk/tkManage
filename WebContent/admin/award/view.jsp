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
    <title>幸运星期五</title>
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

</script>
<body>
	<div id="subjects">
		<form id="main_form" action=""
			method="post" accept-charset="utf-8" style="padding-bottom: 4em;">
			<div class="form_ctrl page_head" id="1" title="">
				<h2>幸运星期五</h2>
			</div>
			<div class="form_ctrl page_text" id="2" title="">
				<p>
					题目：广州银行信用卡中心立足本土，自2011年底不断完善信用卡产品体系及创新产品功能。2015年10月，卡中心新推出的卡产品全名是__?</p>

				<p>提示：1、蓝色？黑色？2、小蛮腰；3、13个字</p>
			</div>

			<div class="form_ctrl input_text" id="3" title="答案">
				<label class="ctrl_title">答案</label> <input type="text"
					name="answer" value="" id="answer" placeholder="请输入您的答案">
			</div>
			<div class="form_ctrl input_text" id="5" title="工号">
				<label class="ctrl_title">工号</label> <input type="text"
					name="userId" value="" id="userId" placeholder="请输入您的工号">
			</div>
			<div class="form_ctrl form_select" id="9" title="性别">
				<label class="ctrl_title">部门</label> <select id="departmentId"
					name="departmentId" num="1"><option value="#">--请选择--</option>
					<c:forEach var="p" items="${list}">
						<option value="${p.departmentId }">${p.departmentName}</option>
					</c:forEach>
				</select>
				<div></div>
			</div>
		</form>
		<div id="bottom_info" style="display: block;"
			onclick="javascript:submitForm()">提交答案</div>
		<div id="bottom_jump">点击查看</div>
	</div>
</body>
</html>