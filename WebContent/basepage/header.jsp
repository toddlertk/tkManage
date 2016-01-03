<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String serverPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   
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
  	<link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/label.min.css">
  	<link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/table.min.css">
  	<link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/popup.min.css">
  	
  	
  	<link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/form.min.css">
  	<link rel="stylesheet" type="text/css" href="<%=basePath %>UI/components/form.min.js">

    <link rel="stylesheet/less" type="text/css" href="<%=basePath %>UI/components/dropdown.less" />
    
  	<!--- Example Libs -->
 	<!--  <script src="../assets/library/jquery.min.js"></script>
  	<script src="../assets/library/iframe-content.js"></script> -->
  	<script src="<%=basePath %>UI/js/show-examples.js"></script>
  	<script src="<%=basePath %>UI/js/jquery.min.js"></script>
  	<script src="<%=basePath %>UI/js/iframe-content.js"></script>
  	<script type="text/javascript" src="<%=basePath %>UI/components/popup.min.js"></script>

  	<!--- Component JS -->
  	<script type="text/javascript" src="<%=basePath %>UI/components/transition.min.js"></script>
  	<%-- <script type="text/javascript" src="<%=basePath %>UI/components/less.min.js"></script> --%>
  	<script type="text/javascript" src="<%=basePath %>UI/components/dropdown.min.js"></script>
  	