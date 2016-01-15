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
function submitForm(){
	if($("#departmentId").val()=='#'){
alert('请选择所属部门');
return;
}

	 	var rules = {
	 				formName:'main_form',
	 				userId:{minLength:2,maxLength:30,type:"string"},
	 				answer:{minLength:2,maxLength:25,type:"string"}
	 				 
		};
		validateForm(rules,"","");
	}
</script>
<body>
<div id="subjects">
    <form id="main_form" action="<%=basePath%>ph/Gr/tk2y-kd92h.od?ai=dt" method="post"
    	 accept-charset="utf-8" style="padding-bottom: 4em;">
            <input type="hidden" name="Gs" value="${Gs}" id="Gs" >
        <div class="form_ctrl page_head" id="1" title=""><h2>幸运星期五</h2></div>

			<c:if test="${flag == true}">
				<c:if test="${Gs == 'WHio7GS7aA3fcD'}">

					<div class="form_ctrl page_text" id="2" title="">
						<p>
							题目：广州银行信用卡中心立足本土，自2011年底不断完善信用卡产品体系及创新产品功能。2015年10月，卡中心新推出的卡产品全名是__?</p>

						<p>提示：1、蓝色？黑色？2、小蛮腰；3、13个字</p>
					</div>
				</c:if>
				<c:if test="${Gs == 'WHioS6J3I0dksD'}">
					<div class="form_ctrl page_text" id="2" title="">
						<p>2015年11月24日，广州银行荣获由广州日报报评选出的“2015年度创新信用卡银行”及“财富管理精英团队”两项大奖。过去一年里，广州银行信用卡中心陆续推出新产品和新服务，在移动互联支付上取得新的突破，满足了客户的需要，这都离不开每位同事为实现卡中心的愿景所做出的努力。
卡中心的愿景是成为中国_______________（本题2分）</p>
						<p>提示：答案是14个字</p>
					</div>
				</c:if>
				<c:if test="${Gs == 'WHiK8o6Ld4PfcD'}">

				</c:if>
			</c:if>
			<div class="form_ctrl input_text" id="3" title="答案">
            <label class="ctrl_title">答案</label>
            <input type="text" name="answer" value="" id="answer" placeholder="请输入您的答案">
        </div>
        <div class="form_ctrl input_text" id="5" title="工号">
            <label class="ctrl_title">工号</label>
            <input type="text" name="userId" value="" id="userId" placeholder="请输入您工牌上的工号">
        </div>
        <div class="form_ctrl form_select" id="9" title="性别">
            <label class="ctrl_title">部门</label>
            <select id="departmentId" name="departmentId" num="1"><option value="#">--请选择--</option>
				<c:forEach var="p" items="${list}">
					<option value="${p.departmentId }">${p.departmentName}</option>
				</c:forEach>
            </select>

            <div></div>
        </div>
    </form>
    <div id="bottom_info" style="display: block;" onclick="javascript:submitForm()">提交答案</div>
    <div id="bottom_jump">点击查看</div>
</div>
</body>
</html>