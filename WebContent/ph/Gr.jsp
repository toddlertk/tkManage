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
        .table{
            margin-top:0;
            background: #e5e5e5;
            width:100%;
            border-collapse: inherit;
            border-spacing:1px;
            color:#666;
        }
        .table th{
            height: 40px;
            line-height: 40px;
            text-align: center;
            font-weight: normal;
        }
        .table td{
            padding:10px 0;
            text-align: center;
            line-height: 24px;
            background: #fff;
            width: 10%;
        }
        .table td span{
            color:#3b5999
        }
    </style>
</head>
<body>
<div id="subjects">
    <form id="main_form" action="#" method="get" onSubmit="try{uploadData();}finally{return false;}"
          accept-charset="utf-8" >
        <div class="form_ctrl page_head" id="1" title=""><h2>幸运星期五</h2></div>
	<c:if test="${Gs == 'WHio7GS7aA3fcD'}">
        <div class="form_ctrl page_text" id="2" title="">
            <p> 题目：广州银行信用卡中心立足本土，自2011年底不断完善信用卡产品体系及创新产品功能。2015年10月，卡中心新推出的卡产品全名是__?</p>

            <p> 提示：1、蓝色？黑色？2、小蛮腰；3、13个字 </p>
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
    </form>

		<c:if test="${!empty msg}">

			<div>
				<p>${msg}</p>
				</br>
			</div>
		</c:if>
		<c:if test="${empty msg}">
			<table class="table">
				<tbody>
					<tr>
						<td width="10%">答案</td>
						<td><span>${wxanswer.answer}</span></td>
					</tr>
					<tr>
						<td>工号</td>
						<td><span>${wxanswer.userId}</span></td>
					</tr>
				</tbody>
			</table>
		</c:if>

	</div>
</body>
</html>