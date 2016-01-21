<%@ include file="/basepage/response.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String serverPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>Theming - Semantic</title>

  <!--- Site CSS -->
  <link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/reset.min.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/site.min.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/grid.min.css">

  <!--- Component CSS -->
  <link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/menu.min.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/input.min.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/dropdown.min.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/icon.min.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/button.min.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/transition.min.css">

  <!--- Example Libs -->
  <link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/popup.min.css">
 <!--  <script src="../assets/library/jquery.min.js"></script>
  <script src="../assets/library/iframe-content.js"></script> -->
  <script src="<%=basePath %>UI/js/show-examples.js"></script>
  <script src="<%=basePath %>UI/js/jquery.min.js"></script>
  <script src="<%=basePath %>UI/js/iframe-content.js"></script>
  <script type="text/javascript" src="<%=basePath %>UI/components/popup.min.js"></script>

  <!--- Component JS -->
  <script type="text/javascript" src="<%=basePath %>UI/components/transition.min.js"></script>
  <script type="text/javascript" src="<%=basePath %>UI/components/dropdown.min.js"></script>

  <!--- Example CSS -->
  <style>
  body {
    padding: 1em;
  }
  .ui.menu {
    margin: 3em 0em;
  }
  .ui.menu:last-child {
    margin-bottom: 110px;
  }
  </style>

  <!--- Example Javascript -->
  <script>
  $(document)
    .ready(function() {
      $('.ui.menu .ui.dropdown').dropdown({
        on: 'hover'
      });
      $('.ui.menu a.item')
        .on('click', function() {
          $(this)
            .addClass('active')
            .siblings()
            .removeClass('active')
          ;
        })
      ;
    })
  ;
  function clickMenu(url){
	  $("#framMain",parent.document.body).attr("src",url) ;
  }
  </script>
</head>
<body>

	<div class="ui inverted menu">
		<a href="#" class="header item"> <img class="logo"
			src="<%=basePath%>/UI/images/logo.png"> TKManage
		</a>
		<c:forEach items="${categoryMap}" var="menu" varStatus="status">
			<div class="ui dropdown item">${menu.value.moduleCategoryName} <i class="dropdown icon"></i>
				<div class="menu">
					<c:forEach items="${moduleMap[menu.key]}" var="subMenu">
						<div class="item"onclick="javascript:clickMenu('<%=basePath %>${subMenu.url}')">
							${subMenu.moduleName}
						</div>
					</c:forEach>
				</div>
			</div>
		</c:forEach>
		<div class="right menu">
			<div class="item">
				<div class="ui action left icon input">
					<i class="search icon"></i> <input type="text" placeholder="Search">
					<button class="ui button">Submit</button>
				</div>
			</div>
			<a class="item" href="#">Link</a>
		</div>
	</div>
	<div class="article">
		<div class="ui main text container">
			<iframe style="border: 0px; width: 100%;" frameBorder="0"
				scrolling="hidden" hideFocus="" name="framMain" id="framMain"
				src="#"></iframe>
			<script>
				var iframe = document.getElementById("framMain")
				var _reSetIframe = function() {
					try {
						var curHeight = $(window).height();
						$("#framMain").height(curHeight - 106);
					} catch (ex) {
						iframe.height = 1000;
					}
				}
				window.setInterval("_reSetIframe()", 300);
			</script>
		</div>
	</div>
</body>
</html>