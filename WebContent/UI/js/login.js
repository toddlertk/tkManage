function validateUser(userId, password, serverPath,redirectUrl){
	var url = serverPath + "ajaxpage/login-validate.od?userId="
		+ userId +"&passKey="+password+"&r=" + Math.random();
	$.get(url, function(result) {
		result = result.replace(/\r\n/g, "");
		if (result == "SUCCESS") {
			window.location.href = redirectUrl;
		}else{
			alert("登录失败！"+result);
		}
	});
}
