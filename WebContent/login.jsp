<!DOCTYPE html>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String serverPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>
<head>
  <!-- Standard Meta -->
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

  <!-- Site Properties -->
  <title>Login  - MANAGE</title>
  <link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/reset.min.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/site.min.css">

  <link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/container.min.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/grid.min.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/header.min.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/image.min.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/menu.min.css">

  <link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/divider.min.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/segment.min.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/form.min.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/input.min.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/button.min.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/list.min.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/message.min.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/icon.min.css">

  <script src="<%=basePath %>UI/js/login.js"></script>
  <script src="<%=basePath %>UI/js/jquery.min.js"></script>
  <script src="<%=basePath %>UI/components/form.min.js"></script>
  <script src="<%=basePath %>UI/components/transition.min.js"></script>

  <style type="text/css">
    body {
      background-color: #DADADA;
    }
    body > .grid {
      height: 100%;
    }
    .image {
      margin-top: -100px;
    }
    .column {
      max-width: 450px;
    }
  </style>
  <script>
  $(document)
    .ready(function() {
      $('.ui.form')
        .form({
          fields: {
            email: {
              identifier  : '账号',
              rules: [
                {
                  type   : 'empty',
                  prompt : 'Please enter your user id'
                },
                {
                  type   : 'number',
                  prompt : 'Please enter a valid 数字'
                }
              ]
            },
            password: {
              identifier  : 'password',
              rules: [
                {
                  type   : 'empty',
                  prompt : 'Please enter your password'
                },
                {
                  type   : 'length[6]',
                  prompt : 'Your password must be at least 6 characters'
                }
              ]
            }
          }
        });
    });
  function login(){
		var userId=$("#userId").val();
		var passKey=$("#passKey").val();
		validateUser(userId,passKey,"<%=basePath%>","<%=basePath%>home.jsp");
	}
  </script>
</head>
<body>

<div class="ui middle aligned center aligned grid">
  <div class="column">
    <h2 class="ui teal image header">
      <img src="<%=basePath %>/UI/images/logo.png" class="image">
      <div class="content">
        Log-in to your account
      </div>
    </h2>
    <form class="ui large form" action="quietLogon.jsp?objectName=Login&objectEvent=QuietLogon&actionType=doLogin">
      <div class="ui stacked segment">
        <div class="field">
          <div class="ui left icon input">
            <i class="user icon"></i>
            <input type="text" name="userId" id="userId" placeholder="ID">
          </div>
        </div>
        <div class="field">
          <div class="ui left icon input">
            <i class="lock icon"></i>
            <input id="passKey" type="password" name="password" placeholder="Password">
          </div>
        </div>
        <div class="ui fluid large teal submit button" onclick="return login()">Login</div>
      </div>

      <div class="ui error message"></div>

    </form>

    <div class="ui message">
      New to us? <a href="#">Sign Up</a>
    </div>
  </div>
</div>

</body>

</html>
